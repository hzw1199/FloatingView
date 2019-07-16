# FloatingView
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![License](https://img.shields.io/badge/License-MIT-blue.svg?style=flat)](http://opensource.org/licenses/MIT)
[![](https://jitpack.io/v/hzw1199/FloatingView.svg)](https://jitpack.io/#hzw1199/FloatingView)

[中文看这里](/READMEcn.md)  

![](/media/viewgroup.gif)
![](/media/system.gif)
![](/media/activity.gif)

# Features

* Support three modes: ```OverlaySystem``` 、 ```OverlayActivity``` and ```OverlayViewGroup``` 
* ```OverlaySystem```: Display FloatingView above other apps
* ```OverlayActivity```: Display FloatingView above certain Activity
* ```OverlayViewGroup```: Display FloatingView above certain ViewGroup
* Move FloatingView with finger
* Define 9 initial position for FloatingView
* Define initial paddings for FloatingView

# Usage

### Step 1

Add it in your build.gradle at the end of repositories:  

```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

Add the dependency in the form:  

```
dependencies {
    compile 'com.github.hzw1199:FloatingView:1.0.0'
}
```

### Step 2

Configure and display

* ```OverlaySystem``` mode

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

* ```OverlayActivity``` mode

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

* ```OverlayViewGroup ``` mode

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

```lyViewGroup``` is the ViewGroup to display FloatingView above

### Step 3

Click event

```java
floatingView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        
    }
});
```

## Proguard
No need to add more proguard rules, `consumerProguardFiles` has already handled library proguard rules.

## Tip

* Please check the demo before use.
* If this project helps you, please star me.

## About Me

* [Home Page](https://zongheng.pro/index.html)
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
