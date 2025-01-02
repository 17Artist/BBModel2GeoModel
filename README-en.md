# BBModel2GeoModel

## Introduction
This project is designed to parse `bbmodel` format project files and output `geo` format models, animations, and textures.

## Features
- Support for parsing bbmodel files
- Ability to export to geo format models
- Capability to export animation files
- Support for exporting texture resources

## Example
```java
File input = new File("");
FileInputStream fis = new FileInputStream(input);
GeoModel model = BBModelConverter.parse(fis);

if(model != null){
    System.out.println("模型加载成功");
    System.out.println("model: " + model.modelSrc());
    System.out.println("animation: " + model.animationSrc());
} else {
    System.out.println("模型加载失败");
}
```

## Description
The code for reading bbmodel is derived from [BlockBenchModelReader](https://github.com/RochBlondiaux/BlockBenchModelReader) with modifications.

## License
This project is licensed under the Apache License 2.0. You can use, modify, and distribute this project according to the terms of the license.