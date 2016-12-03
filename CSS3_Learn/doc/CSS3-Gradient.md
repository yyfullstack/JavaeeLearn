###CSS3 Gradient
    CSS3 Gradient分为linear-gradient(线性渐变)和radial-gradient(径向渐变)

####一、线性渐变在Mozilla下的应用
    语法：
    -moz-linear-gradient( [<point> || <angle>,]? <stop>, <stop> [, <stop>]* )
    参数：其共有三个参数，第一个参数表示线性渐变的方向，top是从上到下、left是从左到右，如果定义成left top，那就是从左上角到右下角。
    第二个和第三个参数分别是起点颜色和终点颜色。你还可以在它们之间插入更多的参数，表示多种颜色的渐变
####二、线性渐变在Webkit下的应用
    语法：
    -webkit-linear-gradient( [<point> || <angle>,]? <stop>, <stop> [, <stop>]* )//最新发布书写语法
    -webkit-gradient(<type>, <point> [, <radius>]?, <point> [, <radius>]? [, <stop>]*) //老式语法书写规则
    参数：-webkit-gradient是webkit引擎对渐变的实现参数，一共有五个。第一个参数表示渐变类型（type），可以是linear（线性渐变）
    或者radial（径向渐变）。第二个参数和第三个参数，都是一对值，分别表示渐变起点和终点。这对值可以用坐标形式表示，也可以用关键值表示，
    比如 left top（左上角）和left bottom（左下角）。第四个和第五个参数，分别是两个color-stop函数。color-stop函数接受两个参数，
    第一个表示渐变的位置，0为起点，0.5为中点，1为结束点；第二个表示该点的颜色

    起始点（Starting Point）的工作方式类似于background position。您可以设置水平和垂直位置为百分比，或以像素为单位，
    或在水平方向上可以使用left/center/right，在垂直方向上可以使用top/center/bottom。位置起始于左上角。如果你不指定水平或垂直位置，
    它将默认为center。其工作方式主要包含：Top → Bottom、Left → Right、bottom → top、right → left等，接着我们主要一种一种来看其实现的效果：

###CSS3的径向渐变
    CSS3的径向渐变和其线性渐变是很相似的。我们首先来看其语法：
    -moz-radial-gradient([<bg-position> || <angle>,]? [<shape> || <size>,]? <color-stop>, <color-stop>[, <color-stop>]*);
    -webkit-radial-gradient([<bg-position> || <angle>,]? [<shape> || <size>,]? <color-stop>, <color-stop>[, <color-stop>]*);
    除了您已经在线性渐变中看到的起始位置，方向，和颜色，径向梯度允许你指定渐变的形状（圆形或椭圆形）和大小（最近端，最近角，最远端，最远角，
    包含或覆盖 (closest-side, closest-corner, farthest-side, farthest-corner, contain or cover)）。 颜色起止(Color stops)：就像用线性渐变，
    你应该沿着渐变线定义渐变的起止颜色。
####1、 颜色起止(Color stops)：就像用线性渐变，你应该沿着渐变线定义渐变的起止颜色。下面的圆具有相同的起止颜色，
                              但在左边的为默认的颜色间隔均匀的渐变，而右边的每种颜色都有特定的位置
####2、形状(Shape)：在这里你可以看到两个可能的形状间的差异，一个圆（左侧）和椭圆（右侧）

####3、大小(Size)：size的不同选项(closest-side, closest-corner, farthest-side, farthest-corner, contain or cover)指向被用来定义圆或椭圆大小的点。

###重复渐变的应用。
    如果您想重复一个渐变，您可以使用-moz-repeating-linear-gradient和-moz-repeating-radial-gradient。 在下面的例子，
    每个实例都指定了两个起止颜色，并无限重复。
