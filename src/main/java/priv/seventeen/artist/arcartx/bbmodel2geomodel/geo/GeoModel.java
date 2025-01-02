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
package priv.seventeen.artist.arcartx.bbmodel2geomodel.geo;

import org.jetbrains.annotations.Nullable;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.Itemtransform.ItemTransform;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * @program: BBModel2GeoModel
 * @description: Geo模型
 * @author: 17Artist
 * @create: 2025-01-01 08:36
 */
public record GeoModel(BufferedImage texture, @Nullable String textureMeta, @Nullable BufferedImage glowTexture,
                       @Nullable String glowTextureMeta, String modelSrc, @Nullable String animationSrc,
                       @Nullable Map<String, ItemTransform> display) {


}
