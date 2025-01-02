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

import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.animation.animators.BlockBenchAnimator;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.animation.keyframe.BlockBenchKeyFrame;

import java.util.ArrayList;
import java.util.List;

/**
* @program: BBModel2GeoModel
*
* @description: 动画包装
*
* @author: 17Artist
*
* @create: 2025-01-02 07:02
**/
public class WrappedAnimator {

    private BlockBenchAnimator handler;

    private final List<BlockBenchKeyFrame> rotation = new ArrayList<>();

    private final List<BlockBenchKeyFrame> position = new ArrayList<>();

    private final List<BlockBenchKeyFrame> scale = new ArrayList<>();

    public WrappedAnimator(BlockBenchAnimator animator){
        this.handler = animator;
        for(BlockBenchKeyFrame keyFrame : animator.keyFrames()){
            switch (keyFrame.type()){
                case "rotation" -> rotation.add(keyFrame);
                case "position" -> position.add(keyFrame);
                case "scale" -> scale.add(keyFrame);
            }
        }
    }


}
