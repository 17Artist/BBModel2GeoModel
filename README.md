# BBModel2GeoModel

## 简介
该项目用于解析`bbmodel`格式的bb工程文件并输出`geo`格式的模型、动作和贴图。

## 特性
- 支持bbmodel文件解析
- 支持导出geo格式模型
- 支持导出动画文件
- 支持导出贴图资源

## 示例
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

## 说明
关于bbmodel的读取，代码来自[BlockBenchModelReader](https://github.com/RochBlondiaux/BlockBenchModelReader)并进行了修改。

## 许可证
该项目根据Apache License 2.0开源，你可以根据许可证的规定使用、修改和分发该项目。