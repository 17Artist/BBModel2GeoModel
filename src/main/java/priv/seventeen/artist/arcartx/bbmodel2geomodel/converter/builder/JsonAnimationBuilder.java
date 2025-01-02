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
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.animation.BlockBenchAnimation;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.animation.animators.BlockBenchAnimator;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.animation.keyframe.BlockBenchKeyFrame;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.animation.keyframe.DataPoint;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.model.BlockBenchModel;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.utils.BezierConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: BBModel2GeoModel
 * @description: 动画Json
 * @author: 17Artist
 * @create: 2025-01-01 21:13
 **/
public abstract class JsonAnimationBuilder {

    private static final String ROTATION = "rotation";
    private static final String POSITION = "position";
    private static final String SCALE = "scale";

    public static String convertAnimation(BlockBenchModel blockbenchModel) {
        JsonObject animations = new JsonObject();
        for (BlockBenchAnimation animation : blockbenchModel.animations()) {
            animations.add(animation.name(), buildAnimation(animation));
        }

        JsonObject root = new JsonObject();
        root.addProperty("format_version", "1.8.0");
        root.add("animations", animations);
        return root.toString();
    }

    // 构建单个动画
    private static JsonObject buildAnimation(BlockBenchAnimation animation) {
        JsonObject animationJson = new JsonObject();
        JsonObject animatorJson = new JsonObject();

        setLoopType(animationJson, animation.loop());
        animationJson.addProperty("animation_length", animation.length());

        // 处理所有骨骼动画
        animation.animators().values().forEach(animator -> animatorJson.add(animator.name(), processAnimator(animator)));

        animationJson.add("bones", animatorJson);

        return animationJson;
    }

    // 设置循环类型
    private static void setLoopType(JsonObject animation, String loopType) {
        switch (loopType) {
            case "loop" -> animation.addProperty("loop", true);
            case "hold" -> animation.addProperty("loop", "hold_on_last_frame");
            default -> animation.addProperty("loop", false);
        }
    }

    // 处理单个动画器
    private static JsonObject processAnimator(BlockBenchAnimator animator) {
        JsonObject bone = new JsonObject();
        Map<String, List<BlockBenchKeyFrame>> channelFrames = sortKeyFramesByChannel(animator);

        // 处理每种变换类型
        processTransformChannel(bone, channelFrames.get(ROTATION), ROTATION);
        processTransformChannel(bone, channelFrames.get(POSITION), POSITION);
        processTransformChannel(bone, channelFrames.get(SCALE), SCALE);

        return bone;
    }

    // 按通道分类关键帧
    private static Map<String, List<BlockBenchKeyFrame>> sortKeyFramesByChannel(BlockBenchAnimator animator) {
        Map<String, List<BlockBenchKeyFrame>> channelFrames = new HashMap<>();
        channelFrames.put(ROTATION, new ArrayList<>());
        channelFrames.put(POSITION, new ArrayList<>());
        channelFrames.put(SCALE, new ArrayList<>());

        if(animator.keyFrames() != null)
            animator.keyFrames().forEach(frame ->
            {
                switch (frame.channel()) {
                    case ROTATION -> channelFrames.get(ROTATION).add(frame);
                    case POSITION -> channelFrames.get(POSITION).add(frame);
                    case SCALE -> channelFrames.get(SCALE).add(frame);
                }
            });

        return channelFrames;
    }

    // 处理变换通道
    private static void processTransformChannel(JsonObject bone, List<BlockBenchKeyFrame> frames, String channel) {
        if (frames == null || frames.isEmpty()) return;

        JsonObject channelObj = new JsonObject();
        for (int i = 0; i < frames.size(); i++) {
            BlockBenchKeyFrame current = frames.get(i);
            BlockBenchKeyFrame last = i > 0 ? frames.get(i - 1) : null;
            BlockBenchKeyFrame next = i < frames.size() - 1 ? frames.get(i + 1) : null;

            processKeyFrame(channelObj, current, last, next);
        }

        if (channelObj.size() > 0) {
            bone.add(channel, channelObj);
        }
    }

    // 处理单个关键帧
    private static void processKeyFrame(JsonObject target, BlockBenchKeyFrame current,
                                        BlockBenchKeyFrame last, BlockBenchKeyFrame next) {
        switch (current.interpolation()) {
            case "linear" -> handleLinearFrame(target, current);
            case "step" -> handleStepFrame(target, current, last);
            case "catmullrom" -> handleCatmullromFrame(target, current);
            case "bezier" -> handleBezierFrame(target, current, next);
        }
    }


    private static void handleLinearFrame(JsonObject target, BlockBenchKeyFrame frame) {
        // 线性曲线 允许两个DataPoint
        String timeKey = String.valueOf(frame.time());
        if(frame.dataPoints().size() == 1){
            DataPoint dataPoints =frame.dataPoints().get(0);
            target.add(timeKey, createVector3Array(dataPoints));
        } else if(frame.dataPoints().size() >= 2){
            // 如果有两个或者两个以上 解析前两个 一个是pre 一个是post
            DataPoint pre = frame.dataPoints().get(0);
            DataPoint post = frame.dataPoints().get(1);

            JsonObject keyFrame = new JsonObject();
            keyFrame.add("pre", createVector3Array(pre));
            keyFrame.add("post", createVector3Array(post));
            target.add(timeKey, keyFrame);
        }
    }

    private static void handleStepFrame(JsonObject target, BlockBenchKeyFrame frame, BlockBenchKeyFrame last) {
        // 原有的step实现
        // 步进 可以有1-2个节点 如果只有一个 以上一帧终点作为pre
        String timeKey = String.valueOf(frame.time());
        if (frame.dataPoints().size() == 1) {
            if (last != null) {
                DataPoint pre = last.dataPoints().get(last.dataPoints().size() - 1);
                DataPoint post = frame.dataPoints().get(0);
                JsonObject keyFrame = new JsonObject();
                keyFrame.add("pre", createVector3Array(pre));
                keyFrame.add("post", createVector3Array(post));
                target.add(timeKey, keyFrame);
            } else {
                DataPoint dataPoints = frame.dataPoints().get(0);
                target.add(timeKey, createVector3Array(dataPoints));
            }
        } else if(frame.dataPoints().size() >= 2){
            // 如果有两个或者两个以上 解析前两个 一个是pre 一个是post
            DataPoint pre = frame.dataPoints().get(0);
            DataPoint post = frame.dataPoints().get(1);
            JsonObject keyFrame = new JsonObject();
            keyFrame.add("pre", createVector3Array(pre));
            keyFrame.add("post", createVector3Array(post));
            target.add(timeKey, keyFrame);
        }
    }

    private static void handleCatmullromFrame(JsonObject target, BlockBenchKeyFrame frame) {
        // 平滑模式仅允许有一个post
        DataPoint post = frame.dataPoints().get(0);
        JsonObject keyFrame = new JsonObject();
        keyFrame.add("post", createVector3Array(post));
        keyFrame.addProperty("lerp_mode", "catmullrom");
        target.add(String.valueOf(frame.time()), keyFrame);
    }


    private static void handleBezierFrame(JsonObject target, BlockBenchKeyFrame current, BlockBenchKeyFrame next) {
        // 贝塞尔
        if (next != null){
            float[] start = new float[]{current.dataPoints().get(0).xAsFloat(), current.dataPoints().get(0).yAsFloat(), current.dataPoints().get(0).zAsFloat()};
            float[] end = new float[]{next.dataPoints().get(0).xAsFloat(), next.dataPoints().get(0).yAsFloat(), next.dataPoints().get(0).zAsFloat()};
            BezierConverter bezierConverter = new BezierConverter();
            Map<String,float[]> bezierKeyframe = bezierConverter.convertBezierKeyframe(current.time(), next.time(), start, end, current.bezierLeftTime(), current.bezierLeftValue(), next.bezierRightTime(), next.bezierRightValue());
            for (Map.Entry<String, float[]> entry : bezierKeyframe.entrySet()) {
                JsonArray keyFrame = new JsonArray();
                keyFrame.add(entry.getValue()[0]);
                keyFrame.add(entry.getValue()[1]);
                keyFrame.add(entry.getValue()[2]);
                target.add(entry.getKey(), keyFrame);
            }
        } else {
            // 如果是最后一个帧
            DataPoint dataPoint = current.dataPoints().get(0);
            target.add(String.valueOf(current.time()), createVector3Array(dataPoint));
        }
    }

    // 创建包含xyz的JsonArray
    private static JsonArray createVector3Array(DataPoint point) {
        JsonArray array = new JsonArray();
        array.add(point.xAsFloat());
        array.add(point.yAsFloat());
        array.add(point.zAsFloat());
        return array;
    }
}
