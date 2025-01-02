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
package priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.Itemtransform;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * @program: BBModel2GeoModel
 * @description: 展示
 * @author: 17Artist
 * @create: 2025-01-02 11:16
 **/
@Getter
public class ItemTransform {

    //"thirdperson_righthand": {
    //			"rotation": [75, 45, 0],
    //			"translation": [0, 2.5, 0],
    //			"scale": [0.375, 0.375, 0.375]
    //		}
    @SerializedName("rotation")
    private float[] rotation;
    @SerializedName("translation")
    private float[] translation;
    @SerializedName("scale")
    private float[] scale;

    public ItemTransform(float[] rotation, float[] translation, float[] scale) {
        this.rotation = rotation;
        this.translation = translation;
        this.scale = scale;
    }

}