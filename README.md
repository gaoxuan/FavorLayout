#FavorLayout

#### My colleague's project, here are more information about [him](https://github.com/gaoxuan/FavorLayout).

### [中文](./README-zh.md)

A custom view animation looks like the Periscope.

![addOne](https://github.com/gaoxuan/FavorLayout/blob/master/raw/addOne.gif)

## Features

These features of the Custom View can be configured:

- Time of the animation
- Floating range 
- Initial position
- Custom shapes

## Add More

![addMore](https://github.com/gaoxuan/FavorLayout/blob/master/raw/addMore.gif)

## Use Custom View

![addMore](https://github.com/gaoxuan/FavorLayout/blob/master/raw/custom.gif)

## Gradle

```xml
android {
    ...	
	buildscript{
	        repositories {
	            jcenter()
	            maven { url = 'https://jitpack.io' }
	        }
	    }
	    allprojects {
	        repositories {
	            jcenter()
	            maven { url = 'https://jitpack.io' }
	        }
	    }
}
```
```xml
dependencies {
    ...
    compile 'com.github.gaoxuan:FavorLayout:1.0'
}
```
## Usage
```xml
 <com.gx.favorlayout_favorlayout.FavorLayout
        android:id="@+id/favor"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:favorHeartWidth="40dp"
        app:favorNodeNum="5"
        app:favorRangeWidth="40dp"/>
```
```java
class YourImageView extends AnimImageView {
    protected void onDraw(Canvas canvas) {
        //custom
    }
}

favorlayout.setViewType(YourImageView.class.getName());
```

## Tips

- You can set false of favorTip in the xml
- These Heart View can also be customized
