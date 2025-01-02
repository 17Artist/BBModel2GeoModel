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

import com.google.gson.JsonObject;

/**
 * @program: BBModel2GeoModel
 * @description: 贴图元数据
 * @author: 17Artist
 * @create: 2025-01-02 08:13
 **/
public abstract class JsonMcMetaBuilder {

    public static String createAnimationMeta(int frametime){
        // 生成json结构
        JsonObject root = new JsonObject();
        JsonObject animation = new JsonObject();
        animation.addProperty("frametime",frametime);
        root.add("animation",animation);

        return root.toString();
    }


}
