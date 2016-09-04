#FavorLayout
### 同事的项目，欢迎为[他](https://github.com/gaoxuan/FavorLayout)Star，谢谢！

实现手机视频直播中给主播点赞的功能，每点一下会从底部出现一个心形飘到屏幕最上方

可以自定义

- 动画时间
- 浮动范围
- 起始位置等

下面是在模拟器上录制的有点卡，在手机上很流畅

![addOne](https://github.com/gaoxuan/FavorLayout/blob/master/raw/addOne.gif)



![addMore](https://github.com/gaoxuan/FavorLayout/blob/master/raw/addMore.gif)



### 使用自定义的图形

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
## 用法
```xml
 <com.gx.favorlayout_favorlayout.FavorLayout
        android:id="@+id/favor"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:favorHeartWidth="40dp"
        app:favorNodeNum="5"
        app:favorRangeWidth="40dp"/>
```
## 更新

* 如果不想显示起始的心形和数字可以这样做，在xml中设置favorTip属性为false
* 可以自定义漂浮的图形

```java
class YourImageView extends AnimImageView {
    protected void onDraw(Canvas canvas) {
        //custom
    }
}

favorlayout.setViewType(YourImageView.class.getName());
```