/*
Copyright 2024 17Artist

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package priv.seventeen.artist.arcartx.bbmodel2geomodel.converter.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.converter.wrapped.WrappedOutliner;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.element.BlockBenchElement;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.element.uv.BlockBenchUv;

import java.util.Map;

/**
 * @program: BBModel2GeoModel
 * @description: 骨骼生成
 * @author: 17Artist
 * @create: 2025-01-01 09:18
 **/
public abstract class JsonBoneBuilder {

    public static void recursivelyCreateBones(JsonArray bones, WrappedOutliner target) {
       bones.add(buildBone(target));
        for(WrappedOutliner child : target.getChildren()) {
            recursivelyCreateBones(bones, child);
       }
    }

    private static JsonObject buildBone(WrappedOutliner target) {
        JsonObject bone = new JsonObject();
        // 基础属性
        bone.addProperty("name", target.getHandler().name() );
        if(target.getParent() != null){
            bone.addProperty("parent", target.getParent().getHandler().name());
        }
        // 变换属性
        bone.add("pivot", createVector(-target.getHandler().origin()[0], target.getHandler().origin()[1], target.getHandler().origin()[2]));
        if(target.getHandler().rotation() != null){
            bone.add("rotation", createVector(-target.getHandler().rotation()[0], -target.getHandler().rotation()[1], target.getHandler().rotation()[2]));
        }
        // 立方体列表
        addCubesList(bone,target);
        return bone;
    }

    // 添加立方体列表
    private static void addCubesList(JsonObject bone, WrappedOutliner target) {
        JsonArray cubes = new JsonArray();
        for(BlockBenchElement element : target.getElements()){
            cubes.add(createCube(element));
        }
        bone.add("cubes", cubes);
    }

    // 创建单个立方体
    private static JsonObject createCube(BlockBenchElement element) {
        JsonObject cube = new JsonObject();

        // 添加立方体基本属性
        addCubeBasicProperties(cube, element);

        // 添加UV信息
        addCubeUVs(cube, element);

        return cube;
    }

    // 添加立方体基本属性
    private static void addCubeBasicProperties(JsonObject cube, BlockBenchElement element) {
        // 计算origin
        float maxX = Math.max(element.from()[0], element.to()[0]);
        float minY = Math.min(element.from()[1], element.to()[1]);
        float minZ = Math.min(element.from()[2], element.to()[2]);
        cube.add("origin", createVector(-maxX, minY, minZ));

        // 计算size
        float[] size = calculateSize(element.from(), element.to());
        cube.add("size", createVector(size[0], size[1], size[2]));

        // 添加pivot
        cube.add("pivot", createVector(-element.origin()[0], element.origin()[1], element.origin()[2]));

        // 可选的rotation
        if(element.rotation() != null && element.rotation().length == 3){
            cube.add("rotation", createVector(-element.rotation()[0], -element.rotation()[1], element.rotation()[2]));
        }
    }


    // 添加UV信息
    private static void addCubeUVs(JsonObject cube, BlockBenchElement element) {
        if(element.boxUv()){
            if(element.uv_offset() == null){
                cube.add("uv", createVector(0, 0));
            } else {
                cube.add("uv", createVector(element.uv_offset()[0], element.uv_offset()[1]));
            }
        } else {
            JsonObject uv = new JsonObject();
            if(element.faces() != null){
                for(Map.Entry<String, BlockBenchUv> entry : element.facesMap().entrySet()){
                    BlockBenchUv uvFace = entry.getValue();
                    if(uvFace.hasTexture()){
                        JsonObject uvDirection = new JsonObject();
                        uv.add(entry.getKey(), uvDirection);
                        float[] uvBounds = calculateUVBounds(uvFace.uv());
                        float[] uv_ = new float[]{uvBounds[0], uvBounds[1]};
                        float[] uv_size = new float[]{uvBounds[2], uvBounds[3]};
                        if(entry.getKey().equals("up") || entry.getKey().equals("down")){
                            uv_[0] += uv_size[0];
                            uv_[1] += uv_size[1];
                            uv_size[0] *= -1;
                            uv_size[1] *= -1;
                        }
                        uvDirection.add("uv", createVector(uv_[0], uv_[1]));
                        uvDirection.add("uv_size", createVector(uv_size[0], uv_size[1]));
                    }
                }
            }
            cube.add("uv", uv);
        }
    }

    // 创建向量数组
    private static JsonArray createVector(float... values) {
        JsonArray array = new JsonArray();
        for (float value : values) {
            array.add(value);
        }
        return array;
    }

    // 计算尺寸
    private static float[] calculateSize(float[] from, float[] to) {
        float[] size = new float[3];
        for (int i = 0; i < 3; i++) {
            size[i] = Math.abs(from[i] - to[i]);
        }
        return size;
    }

    // 计算UV边界
    private static float[] calculateUVBounds(float[] uv) {
        float minU = Math.min(uv[0], uv[2]);
        float minV = Math.min(uv[1], uv[3]);
        float sizeU = Math.abs(uv[0] - uv[2]);
        float sizeV = Math.abs(uv[1] - uv[3]);
        return new float[]{minU, minV, sizeU, sizeV};
    }

}
