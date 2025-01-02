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
package priv.seventeen.artist.arcartx.bbmodel2geomodel.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @program: BBModel2GeoModel
 * @description: 工具
 * @author: 17Artist
 * @create: 2025-01-01 08:08
 **/
public class JsonUtils {


    // 将 JsonArray 转换为 Stream
    public static Stream<JsonElement> streamOf(JsonArray array) {
        return StreamSupport.stream(array.spliterator(), false);
    }

    // 专门用于处理 children 的方法
    public static <T> List<T> deserializeList(JsonArray array, Function<JsonElement, T> deserializer) {
        return streamOf(array)
                .map(deserializer)
                .collect(Collectors.toList());
    }

    // 获取 JsonObject 中的 JsonArray，如果不存在返回空数组
    public static JsonArray getArray(JsonObject obj, String key) {
        return obj.has(key) ? obj.get(key).getAsJsonArray() : new JsonArray();
    }

}
