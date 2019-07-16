# FloatingView
[![](https://jitpack.io/v/hzw1199/FloatingView.svg)](https://jitpack.io/#hzw1199/FloatingView)

![](/media/viewgroup.webm)
![](/media/system.webm)
![](/media/activity.webm)

## Features

* 支持```OverlaySystem``` 、 ```OverlayActivity``` 和 ```OverlayViewGroup``` 三种模式
* ```OverlaySystem```模式下可在其他APP上显示，自动申请权限
* ```OverlayActivity```模式下只在指定的Activity上显示
* ```OverlayViewGroup```模式下只在指定的ViewGroup上显示
* 可随着手指拖动
* 可指定9个初始位置
* 可指定初始paddings

## Usage

### Step 1

在project的build.gradle中加入以下语句:  

```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

在module的build.gradle中加入以下语句:  

```
dependencies {
    compile 'com.github.hzw1199:FloatingView:1.0.0'
}
```

### Step 2

创建配置并显示

* ```OverlaySystem```模式

onCreate:

```java
FloatingViewConfig config = new FloatingViewConfig.Builder()
        .setPaddingLeft(paddingLeft)
        .setPaddingTop(paddingTop)
        .setPaddingRight(paddingRight)
        .setPaddingBottom(paddingBottom)
        .setGravity(gravity)
        .build();
floatingView = new FloatingView(OverlaySystemActivity.this, R.layout.view_floating, config);
floatingView.showOverlaySystem();
```

onDestroy:

```
if (floatingView != null) {
    floatingView.hide();
}
```

* ```OverlayActivity```模式

onAttachedToWindow:

```java
FloatingViewConfig config = new FloatingViewConfig.Builder()
        .setPaddingLeft(paddingLeft)
        .setPaddingTop(paddingTop)
        .setPaddingRight(paddingRight)
        .setPaddingBottom(paddingBottom)
        .setGravity(gravity)
        .build();
floatingView = new FloatingView(OverlaySystemActivity.this, R.layout.view_floating, config);
floatingView.showOverlayActivity();
```

onDetachedFromWindow:

```
if (floatingView != null) {
    floatingView.hide();
}
```

* ```OverlayViewGroup ```模式

onCreate:

```java
lyViewGroup.post(new Runnable() {
    @Override
    public void run() {
		FloatingViewConfig config = new FloatingViewConfig.Builder()
		        .setPaddingLeft(paddingLeft)
		        .setPaddingTop(paddingTop)
		        .setPaddingRight(paddingRight)
		        .setPaddingBottom(paddingBottom)
		        .setGravity(gravity)
		        .setDisplayWidth(lyViewGroup.getWidth())
		        .setDisplayHeight(lyViewGroup.getHeight())
		        .build();
		floatingView = new FloatingView(OverlaySystemActivity.this, R.layout.view_floating, config);
		floatingView.showOverlayViewGroup(lyViewGroup);
    }
});
```

onDestroy:

```
if (floatingView != null) {
    floatingView.hide();
}
```

```lyViewGroup```是用来放置FloatingView的ViewGroup

### Step 3

点击事件

```java
floatingView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        
    }
});
```

## Tip

* 使用前请查看demo
* 若对你有帮助请加星
* [Blog](https://blog.zongheng.pro)

## License

```
The MIT License (MIT)

Copyright (c) 2017 AndroidGpsStatus

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```