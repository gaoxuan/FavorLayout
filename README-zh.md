#FavorLayout
实现手机视频直播中给主播点赞的功能，每点一下会从底部出现一个心形飘到屏幕最上方。
<br>动画是利用ObjectAnimation完成的，路径是由几段不同的贝塞尔曲线拼接而成</br>

下面是在模拟器上录制的有点卡，在手机上很流畅
#### 添加一个

![addOne](https://github.com/gaoxuan/FavorLayout/blob/master/raw/addOne.gif)

#### 添加多个
![addMore](https://github.com/gaoxuan/FavorLayout/blob/master/raw/addMore.gif)

#### 使用自定义的图形
![addMore](https://github.com/gaoxuan/FavorLayout/blob/master/raw/custom.gif)

## 功能
<br>像是Periscope的点赞功能一样</br>
可以自定义

- 动画时间
- 浮动范围
- 起始位置
- 浮动的形状

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
##### 属性:
* `favorDuration` 动画持续时间
* `favorSpeedMode` 速度模式，使用给定值`linear|accelerate|decelerate|acceleratedecelerate`
* `favorIntercurrentHeartNum` 每次心形出现的最大并发数
* `favorMaxHeartNum` 屏幕最多的心形数
* `favorNodeNum` 路径节点个数
* `favorHeartWidth` 心形的宽带和高度
* `favorRangeWidth` 动画浮动范围
* `favorMarginRight` 心形距父容器右边的值
* `favorMarginBottom` 心形距父容器下面的值
* `favorTip` 是否隐藏开始的白色心形和心形数，使用给定值`true|false`

##### 如果你想用自定义的图形
```java
class YourImageView extends AnimImageView {
    protected void onDraw(Canvas canvas) {
        //custom
    }
}

favorlayout.setViewType(YourImageView.class.getName());
```
## 更新

* 如果不想显示起始的心形和数字可以这样做，在xml中设置favorTip属性为false
* 可以自定义漂浮的图形