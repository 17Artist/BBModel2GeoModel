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
package priv.seventeen.artist.arcartx.bbmodel2geomodel.converter.wrapped;

import lombok.Getter;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.element.BlockBenchElement;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.model.BlockBenchModel;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.outliner.Outliner;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.texture.BlockBenchTexture;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * @program: BBModel2GeoModel
 * @description: BBModel包装
 * @author: 17Artist
 * @create: 2025-01-01 08:54
 **/
@Getter
public class WrappedBBModel {

    private final BlockBenchModel handler;

    // 大纲节点
    private final List<WrappedOutliner> outliners = new ArrayList<>();

    private BufferedImage texture;

    private int textureFrameTime;

    private BufferedImage glowTexture;

    private int glowTextureFrameTime;

    public WrappedBBModel(BlockBenchModel model){
        this.handler = model;
        // 元素字典
        Map<UUID, BlockBenchElement> elementDict = new HashMap<>();
        for(BlockBenchElement element : model.elements()){
            if(!element.type().equalsIgnoreCase("cube")) continue;
            elementDict.put(element.uniqueId(), element);
        }
        // 不在大纲的元素
        List<Outliner> noOutlinerElements = new ArrayList<>();
        // 创建顶层大纲
        for(Outliner outliner : model.outliner()){
            if(outliner.name() == null){
                noOutlinerElements.add(outliner);
            } else {
                outliners.add(new WrappedOutliner(outliner,elementDict,null));
            }
        }
        outliners.add(new WrappedOutliner(Outliner.create(noOutlinerElements),elementDict,null));

        // 贴图
        for(BlockBenchTexture blockBenchTexture : handler.textures()){
            if(blockBenchTexture.name().endsWith("glow")){
                glowTexture = blockBenchTexture.image();
                glowTextureFrameTime = blockBenchTexture.frameTime();
            } else {
                texture = blockBenchTexture.image();
                textureFrameTime = blockBenchTexture.frameTime();
            }
        }
    }

}
