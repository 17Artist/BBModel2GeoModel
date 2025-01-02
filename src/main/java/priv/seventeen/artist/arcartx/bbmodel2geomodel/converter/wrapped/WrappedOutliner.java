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

import lombok.Getter;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.element.BlockBenchElement;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.outliner.Outliner;

import java.util.*;

/**
* @program: BBModel2GeoModel
* @description: Outliner包装
* @author: 17Artist
* @create: 2025-01-01 08:53
**/
@Getter
public class WrappedOutliner {

    private final Outliner handler;
    // 父级
    private final WrappedOutliner parent;
    // 子节点
    private final List<WrappedOutliner> children = new ArrayList<>();
    // 元素
    private final List<BlockBenchElement> elements = new ArrayList<>();

    protected WrappedOutliner(Outliner handler, Map<UUID,BlockBenchElement> elementDict, WrappedOutliner parent){
        this.handler = handler;
        this.parent = parent;
        for(Outliner child : handler.children()){
            if(child.name() == null){
                BlockBenchElement element = elementDict.get(child.uniqueId());
                if(element != null){
                    elements.add(element);
                }
            } else {
                children.add( new WrappedOutliner(child, elementDict,this));
            }
        }
    }

}
