    在CSS2中，大家都碰到过圆角的制作。当时，对于圆角的制作，我们都需要使用多张圆角图片做为背景，分别应用到每个角上，
    我应用最多的就是在需要圆角的元素标签中加四个空标签，然后在每个空标签中应用一个圆角的背景位置，然后在对这几个应用了
    圆角的标签进行定位到相应的位置，具体实现过程我就不在多讲，只看方法就知道烦死人。

    如今CSS3中的border-radius出现后，让我们没有那么多的烦恼了，首先制作圆角图片的时间是省了，而且其还有多个优点：
        其一减少网站的维护的工作量，少了对图片的更新制作，代码的替换等等;
        其二、提高网站的性能，少了对图片进行http的请求，网页的载入速度将变快；
        其三增加视觉美观性
 ###语法：
    border-radius ： none | <length>{1,4} [/ <length>{1,4} ]?

 ###取值：
    <length>： 由浮点数字和单位标识符组成的长度值。不可为负值。

 ###说明：
    border-radius是一种缩写方法。如果“/”前后的值都存在，那么“/”前面的值设置其水平半径，“/”后面值设置其垂直半径。
    如果没有“/”，则水平和垂直半径相等。另外其四个值是按照top-left、top-right、bottom-right、bottom-left的顺序来设置
    的其主要会有下面几种情形出现：
    1、border-radius: [ <length>{1,4} ]; //这里只有一个值，那么top-left、top-right、bottom-right、bottom-left四个值相等
    2、border-radius:[ <length>{1,4} ]  [ <length>{1,4} ] ; //这里设置两个值，那么top-left等于bottom-right，并且取第一个值；
        top-right等于bottom-left，并且取第二个值
    3、border-radius:[ <length>{1,4} ]   [ <length>{1,4} ]   [ <length>{1,4} ];//如果有三个值，其中第一个值是设置top-left;
        而第二个值是top-right和bottom-left并且他们会相等,第三个值是设置bottom-right
    4、border-radius:[ <length>{1,4} ]   [ <length>{1,4} ]  [ <length>{1,4} ]   [ <length>{1,4} ];//如果有四个值，
    其中第一个值是设置top-left;而第二个值是top-right,第三个值bottom-right,第四个值是设置bottom-left
    以把各个角单独拆分出来，也就是以下四种写法：
    border-top-left-radius: <length>  <length>   //左上角
    border-top-right-radius: <length>  <length>  //右上角
    border-bottom-right-radius:<length>  <length>  //右下角
    border-bottom-left-radius:<length>  <length>   //左下角

1. Mozilla(Firefox, Flock等浏览器)
  -moz-border-radius-topleft: //左上角
  -moz-border-radius-topright: //右上角
  -moz-border-radius-bottomright: //右下角
  -moz-border-radius-bottomleft: //左下角
   等价于：
  -moz-border-radius: //简写

2. WebKit (Safari, Chrome等浏览器)
  -webkit-border-top-left-radius:  //左上角
  -webkit-border-top-right-radius:  //右上角
  -webkit-border-bottom-right-radius:  //右下角
  -webkit-border-bottom-left-radius:  // 左下角
   等价于：
  -webkit-border-radius:  //简写

3. Opera浏览器：
 border-top-left-radius: //左上角
 border-top-right-radius: //右上角
 border-bottom-right-radius: //右下角
 border-bottom-left-radius: //左下角
 等价于：
 border-radius: //简写
4. Trident (IE)
    IE<9不支持border-radius;IE9下没有私有格式，都是用border-radius，
    其写法和Opera是一样的，这里就不在重复。

下面我们来看几个水平和垂直半径值不一样的实例
一、border-radius: 水平 / 垂直：只设置一个水平和一个垂直半径时，那么水平半径分别指定了元素个四个角的水平半径值，
同样垂直半径指定了元素的垂直半径值，此时四个角具有相同的效果，因为他们具有相同的值

二、border-radius: 水平1 水平2 / 垂直1  垂直2：设置了两个水平值和两个垂直值，此时我们top-left和bottom-right具有相同的水平和垂直半径，
也就是其中的水平1和垂直1；而top-right和bottom-left也具有相同的水平和垂直半径值，也就是水平2和垂直2

几种特殊点的应用：
一、对于border-radius还有一个内半径和外半径的区别，它主要是元素 边框值较大时，效果就很明显，当我们border-radius半径值小于
或等于border的厚度时，我们边框内部就不具有圆角效果,
二、如果角的两个相邻边有不同的宽度，那么这个角将会从宽的边平滑过度到窄的边。其中一条边甚至可以是0。相邻转角是由大向小转
三、相邻两条边颜色和线条样式不同时，那么两条相邻边颜色和样式转变的中心点是在一个和两边宽度成正比的角上。比如，两条边宽度相同，
这个点就是一个45°的角上，如果一条边是另外一条边的两倍，那么这个点就在一个30°的角上。界定这个转变的线就是连接在内外曲线上的两个点的直线