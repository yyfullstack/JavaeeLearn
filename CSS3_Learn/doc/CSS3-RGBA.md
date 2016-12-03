    RGB对于大家来说一点不陌生，他就是红色R+绿色G+蓝色B，那现在我们所说的RGBA又是什么呢？说得简单一点就是在RGB的基础上
    加进了一个通道Alpha。从而形成了我们今天需要讨论的RGBA。
###语法:
    R：红色值。正整数 | 百分数
    G：绿色值。正整数 | 百分数
    B：蓝色值。正整数| 百分数
    A：透明度。取值0~1之间
###取值：
<length> ：Hue(色调)。 0(或360)表示红色，120表示绿色，240表示蓝色，当然可取其他数值来确定其它颜色；
<percentage> ：Saturation(饱和度)。 取值为0%到100%之间的值；
<percentage> ：Lightness(亮度)。 取值为0%到100%之间的值；
<opacity> ：alpha(透明度)。 取值在0到1之间；

###说明：
    RGB色彩模式（也翻译为“红绿蓝”，比较少用）是工业界的一种颜色标准，是通过对红(R)、绿(G)、蓝(B)三个颜色通道的变化
    以及它们相互之间的叠加来得到各式各样的颜色的，RGB即是代表红、绿、蓝三个通道的颜色，这个标准几乎包括了人类视力所能感知的
    所有颜色，是目前运用最广的颜色系统之一。

    RGBA在RGB的基础上多了控制alpha透明度的参数。以上R、G、B三个参数，正整数值的取值范围为：0 - 255。百分数值的取值范围为：
    0.0% - 100.0%。超出范围的数值将被截至其最接近的取值极限。并非所有浏览器都支持使用百分数值。A参数，取值在0~1之间，不可为负值

    从我们上面的实例中我们也知道，RGBA比元素设置CSS的透明度更好，因为单独的颜色可以在不影响整个元素的透明度，
    他不会影响到元素其他的属性，比如说边框，字体同时也不会影响到其他元素的相关透明度

###CSS2中Opacity能实现透明，而且大多主流浏览器都支持，虽然IE下有点麻烦
    /* IE5 - 7 */
    filter: alpha(opacity=80);
    /* IE 8 */
    -ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=80)";
    /* Everyone else */
    opacity: 0.8;

我们前面简单的带过一句，我们rgba不单可以应用在background上，我们还可以应用在只要设置了颜色的地方都可以使用，我在这里简单的说一下几种
1. 前景色color
  <p class="norgba-color">用rgba改变我的字体颜色</p>
  <p class="rgba-color">用rgba改变我的字体颜色</p>
  .norgba-color {
    color: rgb(255, 0, 0);
  }
  .rgba-color {
    color: rgb(255, 0, 0);
    color: rgba(255, 0, 0,0.5);
  }

2. 边框色border-color
    <p class="norgba-border-color">用rgba改变我的边框颜色</p>
    <p class="rgba-border-color">用rgba改变我的边框颜色</p>
    .norgba-border-color {
       border:5px solid rgb(255,0,0);
       width: 200px;
    }
    .rgba-border-color {
       border:5px solid rgb(255,0,0);
       border:5px solid rgba(255,0,0,0.5);
       width: 200px;
    }
3. 字体的阴影色text-shadow
<p class="norgba-text-shadow">用rgba改变我的字体阴影颜色</p>
 <p class="rgba-text-shadow">用rgba改变我的字体阴影颜色</p>

  .norgba-text-shadow {
    text-shadow : 0 2px 1px rgb(255,0,0);
  }
  .rgba-text-shadow {
    text-shadow : 0 2px 1px rgb(255,0,0);
    text-shadow : 0 2px 1px rgba(255,0,0,0.3);
  }
4. 改变边框阴影色
<p class="norgba-box-shadow">用rgba改变我的边框阴影颜色</p>
 <p class="rgba-box-shadow">用rgba改变我的边框阴影颜色</p>

  .norgba-box-shadow {
      border: 5px solid green;
      width: 200px;
      -webkit-box-shadow: 0 2px 2px rgb(255,0,0);
      -moz-box-shadow: 0 2px 2px rgb(255,0,0);
      box-shadow: 0 2px 2px rgb(255,0,0);
   }
   .rgba-box-shadow {
      border: 5px solid green;
      width: 200px;
      -webkit-box-shadow: 0 2px 2px rgba(255,0,0,0.6);
      -moz-box-shadow: 0 2px 2px rgba(255,0,0,0.6);
      box-shadow: 0 2px 2px rgba(255,0,0,0.6);
   }