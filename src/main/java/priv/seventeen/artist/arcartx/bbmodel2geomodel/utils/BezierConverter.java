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

import java.util.HashMap;
import java.util.Map;

/**
 * @program: BBModel2GeoModel
 * @description: 贝塞尔缓动格式读取
 * @author: 17Artist
 * @create: 2025-01-01 20:39
 **/
public class BezierConverter {


    public Map<String, float[]> convertBezierKeyframe(
            float startTime, float endTime,
            float[] startPos, float[] endPos,
            float[] bezierLeftTime, float[] bezierLeftValue,
            float[] bezierRightTime, float[] bezierRightValue
    ) {
        Map<String, float[]> result = new HashMap<>();

        // 生成24帧每秒的插值点
        float duration = endTime - startTime;
        int frames = (int)(duration * 24);

        for(int i = 0; i <= frames; i++) {
            float t = i / (float)frames;
            float currentTime = startTime + t * duration;

            // 使用贝塞尔控制点计算当前位置
            float[] position = new float[3];
            for(int axis = 0; axis < 3; axis++) {
                float timeInfluence = evalBezier(t,
                        bezierLeftTime[axis],
                        bezierRightTime[axis]);

                float valueInfluence = evalBezier(t,
                        bezierLeftValue[axis],
                        bezierRightValue[axis]);

                position[axis] = startPos[axis] +
                        (endPos[axis] - startPos[axis]) * timeInfluence +
                        valueInfluence;
            }

            // 格式化时间到4位小数
            String timeKey = String.format("%.4f", currentTime);
            result.put(timeKey, position);
        }

        return result;
    }

    private float evalBezier(float t, float leftControl, float rightControl) {
        float t2 = t * t;
        float t3 = t2 * t;
        float mt = 1 - t;
        float mt2 = mt * mt;
        float mt3 = mt2 * mt;

        return mt3 + 3 * mt2 * t * (1 + leftControl) +
                3 * mt * t2 * (1 + rightControl) + t3;
    }
}