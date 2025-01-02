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

import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.element.BlockBenchElement;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.model.BlockBenchModel;

/**
 * @program: BBModel2GeoModel
 * @description: 体积运算
 * @author: 17Artist
 * @create: 2025-01-01 13:25
 **/
public class VisibleBoundsCalculator {
    public static class VisibleBox {
        public Vector3f max = new Vector3f(0, 0, 0);
        public Vector3f min = new Vector3f(0, 0, 0);
    }

    public static class Vector3f {
        public float x, y, z;

        public Vector3f(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static float[] calculateVisibleBounds(BlockBenchModel data) {
        VisibleBox visibleBox = new VisibleBox();

        // 计算所有元素的边界
        for (BlockBenchElement element : data.elements()) {
            if(!element.type().equalsIgnoreCase("cube")) continue;;
            if (element.from() == null || element.to() == null) continue;

            // X轴
            visibleBox.max.x = Math.max(visibleBox.max.x,
                    Math.max(element.from()[0], element.to()[0]));
            visibleBox.min.x = Math.min(visibleBox.min.x,
                    Math.min(element.from()[0], element.to()[0]));

            // Y轴
            visibleBox.max.y = Math.max(visibleBox.max.y,
                    Math.max(element.from()[1], element.to()[1]));
            visibleBox.min.y = Math.min(visibleBox.min.y,
                    Math.min(element.from()[1], element.to()[1]));

            // Z轴
            visibleBox.max.z = Math.max(visibleBox.max.z,
                    Math.max(element.from()[2], element.to()[2]));
            visibleBox.min.z = Math.min(visibleBox.min.z,
                    Math.min(element.from()[2], element.to()[2]));
        }

        // 偏移计算
        visibleBox.max.x += 8;
        visibleBox.min.x += 8;
        visibleBox.max.y += 8;
        visibleBox.min.y += 8;
        visibleBox.max.z += 8;
        visibleBox.min.z += 8;

        // 计算宽度
        float radius = Math.max(
                Math.max(visibleBox.max.x, visibleBox.max.z),
                Math.max(-visibleBox.min.x, -visibleBox.min.z)
        );

        if (Float.isInfinite(radius)) {
            radius = 0;
        }

        float width = (float) Math.ceil((radius * 2) / 16f);
        // 假设data.visible_box[0]是默认宽度
        width = Math.max(width, data.visibleBox()[0]);

        // 计算高度
        float yMin = (float) Math.floor(visibleBox.min.y / 16f);
        float yMax = (float) Math.ceil(visibleBox.max.y / 16f);

        if (Float.isInfinite(yMin)) yMin = 0;
        if (Float.isInfinite(yMax)) yMax = 0;

        // 假设data.visible_box[1]是默认高度，data.visible_box[2]是默认偏移
        yMin = Math.min(yMin, data.visibleBox()[1] - data.visibleBox()[2] / 2);
        yMax = Math.max(yMax, data.visibleBox()[1] + data.visibleBox()[2] / 2);

        // 返回[宽度, 高度, 偏移]
        return new float[]{
                width,
                yMax - yMin,
                (yMax + yMin) / 2
        };
    }
}
