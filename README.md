#FavorLayout
----
实现手机视频直播中给主播点赞的功能，每点一下会从底部出现一个心形飘到屏幕最上方，可以自定义动画时间，浮动范围，起始位置等
<br/>
<br>下面是在模拟器上录制的，有点卡，在手机上是没问题<br/>
![addOne](https://github.com/gaoxuan/FavorLayout/blob/master/raw/addOne.gif)
<br/>
![addMore](https://github.com/gaoxuan/FavorLayout/blob/master/raw/addMore.gif)
<br/>
<br/>
使用自定义的图形
<br/>
![addMore](https://github.com/gaoxuan/FavorLayout/blob/master/raw/custom.gif)
## Gradle

```
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
```
dependencies {
    ...
    compile 'com.github.gaoxuan:FavorLayout:1.1'
}
```
## 用法
```
 <com.gx.favorlayout_favorlayout.FavorLayout
        android:id="@+id/favor"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:favorHeartWidth="40dp"
        app:favorNodeNum="5"
        app:favorRangeWidth="40dp"/>
```
## 更新
现在开发者可以自定义漂浮的图形
```
class YourImageView extends AnimImageView {

    protected void onDraw(Canvas canvas) {
        //custom
    }
}
```