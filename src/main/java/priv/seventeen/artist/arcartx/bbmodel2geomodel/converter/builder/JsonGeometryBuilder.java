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
import priv.seventeen.artist.arcartx.bbmodel2geomodel.converter.wrapped.WrappedBBModel;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.converter.wrapped.WrappedOutliner;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.element.Resolution;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.utils.VisibleBoundsCalculator;

/**
 * @program: BBModel2GeoModel
 * @description: 模型构建
 * @author: 17Artist
 * @create: 2025-01-01 11:53
 **/
public abstract class JsonGeometryBuilder {

    public static String buildFromModel(WrappedBBModel target) {
        JsonObject root =  new JsonObject();
        addFormatVersion(root);
        addGeometries(root, target);
        return root.toString();
    }


    private static void addFormatVersion(JsonObject root) {
        root.addProperty("format_version", "1.12.0");
    }

    private static void addGeometries(JsonObject root, WrappedBBModel target) {
        JsonArray geometries = new JsonArray();

        JsonObject geometry = new JsonObject();

        addDescription(geometry, target);
        addBones(geometry,target);

        geometries.add(geometry);

        root.add("minecraft:geometry", geometries);
    }

    private static void addDescription(JsonObject geometry,WrappedBBModel target) {
        JsonObject description = new JsonObject();
        description.addProperty("identifier", target.getHandler().identifier());
        Resolution resolution = target.getHandler().resolution();
        description.addProperty("texture_width", resolution.width());
        description.addProperty("texture_height", resolution.height());
        if(!target.getOutliners().isEmpty()){
            float[] visibleBox = VisibleBoundsCalculator.calculateVisibleBounds(target.getHandler());
            description.addProperty("visible_bounds_width", visibleBox[0]);
            description.addProperty("visible_bounds_height", visibleBox[1]);
            JsonArray visibleBoundsOffset = new JsonArray();
            visibleBoundsOffset.add(0);
            visibleBoundsOffset.add(visibleBox[2]);
            visibleBoundsOffset.add(0);
            description.add("visible_bounds_offset", visibleBoundsOffset);
        }
        geometry.add("description", description);
    }

    private static void addBones(JsonObject geometry, WrappedBBModel target) {
        JsonArray bones = new JsonArray();
        for (WrappedOutliner outliner : target.getOutliners()) {
            JsonBoneBuilder.recursivelyCreateBones(bones, outliner);
        }
        geometry.add("bones", bones);
    }

}
