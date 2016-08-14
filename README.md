#FavorLayout
----
实现手机视频直播中给主播点赞的功能，每点一下会从底部出现一个心形飘到屏幕最上方，可以自定义动画时间，浮动范围，起始位置等
<br/>
<br>下面是在模拟器上录制的，有点卡，在手机上是没问题<br/>
![addOne](https://github.com/gaoxuan/FavorLayout/blob/master/raw/addOne.gif)
<br/>
![addMore](https://github.com/gaoxuan/FavorLayout/blob/master/raw/addMore.gif)

##怎么用

```
 <com.gx.favorlayout_favorlayout.FavorLayout
        android:id="@+id/favor"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:favorHeartWidth="40dp"
        app:favorNodeNum="5"
        app:favorRangeWidth="40dp"/>
```
