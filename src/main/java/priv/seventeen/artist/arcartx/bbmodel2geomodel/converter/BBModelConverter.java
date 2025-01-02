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
package priv.seventeen.artist.arcartx.bbmodel2geomodel.converter;

import priv.seventeen.artist.arcartx.bbmodel2geomodel.converter.builder.JsonAnimationBuilder;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.converter.builder.JsonGeometryBuilder;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.converter.builder.JsonMcMetaBuilder;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.converter.wrapped.WrappedBBModel;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.geo.GeoModel;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.BlockBenchModelReader;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.model.BlockBenchModel;

import java.awt.image.BufferedImage;
import java.io.InputStream;



/**
 * @program: BBModel2GeoModel
 * @description: bb模型转换器
 * @author: 17Artist
 * @create: 2025-01-01 08:38
 **/
public class BBModelConverter {

    private final static BlockBenchModelReader blockBenchModelReader = new BlockBenchModelReader();

    public static GeoModel parse(InputStream inputStream) {
        try {
            BlockBenchModel blockBenchModel = blockBenchModelReader.read(inputStream);
            WrappedBBModel wrappedBBModel = new WrappedBBModel(blockBenchModel);

            String modelSrc = JsonGeometryBuilder.buildFromModel(wrappedBBModel);
            String animationSrc = JsonAnimationBuilder.convertAnimation(blockBenchModel);

            BufferedImage texture = wrappedBBModel.getTexture();
            String textureMeta = null;
            if(texture != null){
                textureMeta = JsonMcMetaBuilder.createAnimationMeta(wrappedBBModel.getTextureFrameTime());
            }
            BufferedImage glowTexture = wrappedBBModel.getGlowTexture();
            String glowTextureMeta = null;
            if(glowTexture != null){
                glowTextureMeta = JsonMcMetaBuilder.createAnimationMeta(wrappedBBModel.getGlowTextureFrameTime());
            }

            return new GeoModel(texture, textureMeta, glowTexture, glowTextureMeta, modelSrc, animationSrc, blockBenchModel.display());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
