#FavorLayout

An Android library implementing praise animation like Periscope.
<br>The animation use ObjectAnimator and the path consists some different Bézier curve.</br>

### [中文](./README-zh.md)

in emulator：
#### Add One

![addOne](https://github.com/gaoxuan/FavorLayout/blob/master/raw/addOne.gif)

#### Add More

![addMore](https://github.com/gaoxuan/FavorLayout/blob/master/raw/addMore.gif)

#### Use Custom View

![addMore](https://github.com/gaoxuan/FavorLayout/blob/master/raw/custom.gif)

## Features
<br>A custom view animation looks like the Periscope.</br>
These features of the Custom View can be configured:

- Time of the animation
- Floating range 
- Initial position
- Custom shapes

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
    compile 'com.github.gaoxuan:FavorLayout:1.1'
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
##### Attributes:
* `favorDuration` animation duration
* `favorSpeedMode` speed mode, `linear|accelerate|decelerate|acceleratedecelerate`
* `favorIntercurrentHeartNum` set maximum number of concurrent
* `favorMaxHeartNum` set maximum number of view in screen except the white ImageView and the number TextView
* `favorNodeNum` node number in path
* `favorHeartWidth` view's width and height
* `favorRangeWidth` floating range
* `favorMarginRight` view's margin left values
* `favorMarginBottom` view's margin bottom values
* `favorTip` set the white ImageView and the number TextView to show of the given values `true|false`

##### If you want to use custom view
```java
class YourImageView extends AnimImageView {
    protected void onDraw(Canvas canvas) {
        //custom
    }
}

favorlayout.setViewType(YourImageView.class.getName());
```

## Update

- You can set false of favorTip in the xml to hide the white ImageView and the number TextView
- These Heart View can also be customized
