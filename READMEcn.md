# FloatingView
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![License](https://img.shields.io/badge/License-MIT-blue.svg?style=flat)](http://opensource.org/licenses/MIT)
[![](https://jitpack.io/v/hzw1199/FloatingView.svg)](https://jitpack.io/#hzw1199/FloatingView)

![](/media/viewgroup.gif)
![](/media/system.gif)
![](/media/activity.gif)

## Features

* 支持```OverlaySystem``` 、 ```OverlayActivity``` 和 ```OverlayViewGroup``` 三种模式
* ```OverlaySystem```模式下可在其他APP上显示，自动申请权限
* ```OverlayActivity```模式下只在指定的Activity上显示
* ```OverlayViewGroup```模式下只在指定的ViewGroup上显示
* 可随着手指拖动
* 可双指缩放（可开关）
* 可监听单击和双击事件
* 可指定9个初始位置
* 可指定初始paddings
* 可持有浮动View对象，从而可以动态修改View的内容，并可为浮动View的各个子控件单独设置OnClickListener

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
    compile 'com.github.hzw1199:FloatingView:1.6.0'
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

双击事件

```java
floatingView.setOnDoubleClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        
    }
});
```

最大化缩放

```java
floatingView.full();
```

## 持有浮动控件的View对象，可动态修改浮动View的内容
[demo](/app/src/main/java/com/wuadam/demo/AdvancedControlActivity.java)

```java
LayoutInflater mInflater = LayoutInflater.from(AdvancedControlActivity.this);
View floatingView = mInflater.inflate(R.layout.view_advanced_control, null, false);
floatingView = new FloatingView(AdvancedControlActivity.this, floatingView, config);
```

## 为浮动View的各个子控件单独设置OnClickListener
[demo](/app/src/main/java/com/wuadam/demo/AdvancedControlActivity.java)

```java
LayoutInflater mInflater = LayoutInflater.from(AdvancedControlActivity.this);
View floatingView = mInflater.inflate(R.layout.view_advanced_control, null, false);
floatingView = new FloatingView(AdvancedControlActivity.this, floatingView, config);

final View vTop = floatingView.findViewById(R.id.v_top);
final View vMiddle = floatingView.findViewById(R.id.v_middle);
final View vBottom = floatingView.findViewById(R.id.v_bottom);
final TextView tvTime = floatingView.findViewById(R.id.tv_time);


View.OnClickListener onClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (v == vTop) {
            Toast.makeText(AdvancedControlActivity.this, "Top clicked", Toast.LENGTH_SHORT).show();
        } else if (v == vMiddle) {
            Toast.makeText(AdvancedControlActivity.this, "Middle clicked", Toast.LENGTH_SHORT).show();
        } else if (v == vBottom) {
            Toast.makeText(AdvancedControlActivity.this, "Bottom clicked", Toast.LENGTH_SHORT).show();
        }
        tvTime.setText(String.valueOf(System.currentTimeMillis()));
    }
};

vTop.setOnClickListener(onClickListener);
vMiddle.setOnClickListener(onClickListener);
vBottom.setOnClickListener(onClickListener);
```

## Proguard
无需配置混淆规则, `consumerProguardFiles` 已经配置过了

## Tip

* 使用前请查看demo
* 若对你有帮助请加星

## About Me

* [Home Page](https://zongheng.pro)
* [Blog](https://blog.zongheng.pro)

## License

```
The MIT License (MIT)

Copyright (c) 2019 FloatingView

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