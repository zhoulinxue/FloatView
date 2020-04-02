# FloatView
## [特别感谢](https://github.com/yhaolpz/FloatWindow.git)
## 效果图
![悬浮按钮图](https://github.com/zhoulinxue/FloatView/blob/master/slide.gif)
集成：
===
```
allprojects {
    repositories {      
        jcenter()
    }
}
```
Androidx
```
	dependencies {
	        implementation 'org.zhx.common:floatView:0.1.0'
	}
```
注意：appcompat （v7包）适配 方式一：
```
	dependencies {
	       implementation 'org.zhx.common:floatView:0.2.0'
	}
```
方式二或在gradle.properties 添加：
```
android.useAndroidX=true
# Automatically convert third-party libraries to use AndroidX
android.enableJetifier=true
```


原著特性：
===

1.支持拖动，提供自动贴边等动画

2.内部自动进行权限申请操作

3.可自由指定要显示悬浮窗的界面

4.应用退到后台时，悬浮窗会自动隐藏

5.除小米外，4.4~7.0 无需权限申请

6.位置及宽高可设置百分比值，轻松适配各分辨率

7.支持权限申请结果、位置等状态监听

8.链式调用，简洁清爽
