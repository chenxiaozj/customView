package com.chenxiao.customview.widgets;

/**
 * Canvas.java
 *
 * drawRect(RectF rect, Paint paint) //绘制区域，参数一为RectF一个区域
 drawPath(Path path, Paint paint) //绘制一个路径，参数一为Path路径对象
 drawBitmap(Bitmap bitmap, Rect src, Rect dst, Paint paint)  //贴图，参数一就是我们常规的Bitmap对象，参数二是源区域(这里是bitmap)，参数三是目标区域(应该在canvas的位置和大小)，参数四是Paint画刷对象，因为用到了缩放和拉伸的可能，当原始Rect不等于目标Rect时性能将会有大幅损失。
 drawLine(float startX, float startY, float stopX, float stopY, Paintpaint) //画线，参数一起始点的x轴位置，参数二起始点的y轴位置，参数三终点的x轴水平位置，参数四y轴垂直位置，最后一个参数为Paint 画刷对象。
 drawPoint(float x, float y, Paint paint) //画点，参数一水平x轴，参数二垂直y轴，第三个参数为Paint对象。
 drawText(String text, float x, floaty, Paint paint)  //渲染文本，Canvas类除了上面的还可以描绘文字，参数一是String类型的文本，参数二x轴，参数三y轴，参数四是Paint对象。
 drawOval(RectF oval, Paint paint)//画椭圆，参数一是扫描区域，参数二为paint对象；
 drawCircle(float cx, float cy, float radius,Paint paint)// 绘制圆，参数一是中心点的x轴，参数二是中心点的y轴，参数三是半径，参数四是paint对象；
 drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)//画弧，
 参数一是RectF对象，一个矩形区域椭圆形的界限用于定义在形状、大小、电弧，参数二是起始角(度)在电弧的开始，
 参数三扫描角(度)开始顺时针测量的，参数四是如果这是真的话,包括椭圆中心的电弧,并关闭它,如果它是假这将是一个

 Paint.java
 setARGB(int a, int r, int g, int b) // 设置 Paint对象颜色，参数一为alpha透明值
 setAlpha(int a) // 设置alpha不透明度，范围为0~255
 setAntiAlias(boolean aa) // 是否抗锯齿
 setColor(int color)  // 设置颜色，这里Android内部定义的有Color类包含了一些常见颜色定义
 setTextScaleX(float scaleX)  // 设置文本缩放倍数，1.0f为原始
 setTextSize(float textSize)  // 设置字体大小
 setUnderlineText(booleanunderlineText)  // 设置下划线
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.chenxiao.customview.R;


// 自定义 view,需要实现 一个显式的构造函数，重写 onDraw 方法，一切的操作都在该方法上进行
public class CustomView extends View {

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
		 * 方法 说明 drawRect 绘制矩形 drawCircle 绘制圆形 drawOval 绘制椭圆 drawPath 绘制任意多边形
		 * drawLine 绘制直线 drawPoint 绘制点
		 */
        // 创建画笔
        Paint p = new Paint();
        p.setTextSize(24 * getResources().getDisplayMetrics().density);
        p.setColor(Color.RED);// 设置红色
        //  clip_drawCanvas(canvas, p);
        //  save_drawCanvas(canvas, p);

        canvas.drawText("画圆：", 100, 120, p);// 画文本
        canvas.drawCircle(600, 100, 40, p);// 小圆
        p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
        canvas.drawCircle(800, 100, 80, p);// 大圆

        canvas.drawText("画线及弧线：", 100, 300, p);
        canvas.drawLine(600, 200, 800, 300, p);// 画线
        canvas.drawLine(600, 300, 800, 300, p);// 斜线
        //画笑脸弧线
        p.setStyle(Paint.Style.STROKE);//设置空心
        RectF oval1 = new RectF(400, 400, 600, 500);
        canvas.drawRect(oval1, p);
        canvas.drawArc(oval1, 90, 180, false, p);//小弧形
        oval1.set(700, 400, 800, 500);
        canvas.drawArc(oval1, 180, 180, false, p);//小弧形
        oval1.set(160, 30, 210, 60);
        canvas.drawArc(oval1, 0, 180, false, p);//小弧形*//*

        canvas.drawText("画矩形：", 100, 650, p);
        p.setColor(Color.BLACK);// 设置灰色
        p.setStyle(Paint.Style.FILL);//设置填满
        canvas.drawRect(600, 600, 700, 700, p);// 正方形
        canvas.drawRect(800, 600, 1000, 700, p);// 长方形

        canvas.drawText("画扇形和椭圆:", 100, 900, p);
		/* 设置渐变色 这个正方形的颜色是改变的 */
        Shader mShader = new LinearGradient(0, 0, 100, 100,
                new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
                        Color.LTGRAY}, null, Shader.TileMode.REPEAT); // 一个材质,打造出一个线性梯度沿著一条线。
        p.setShader(mShader);
        p.setColor(Color.BLUE);
        RectF oval2 = new RectF(600, 800, 700, 1000);// 设置个新的长方形，扫描测量
        canvas.drawArc(oval2, 90, 180, true, p);
        // 画弧，第一个参数是RectF：该类是第二个参数是角度的开始，第三个参数是多少度，第四个参数是真的时候画扇形，是假的时候画弧线
        //画椭圆，把oval改一下
        oval2.set(800, 800, 1000, 900);
        canvas.drawRect(800, 1000, 1000, 1100, p);
        canvas.drawOval(oval2, p);


        p.setShader(null);
        p.setStyle(Paint.Style.STROKE);
        p.setAntiAlias(false);
        canvas.drawText("画三角形：", 100, 1200, p);
        // 绘制这个三角形,你可以绘制任意多边形
        Path path = new Path();
        path.moveTo(700, 1100);// 此点为多边形的起点
        path.lineTo(600, 1200);
        path.lineTo(800, 1200);
        path.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(path, p);

        // 你可以绘制很多任意多边形，比如下面画六连形
        p.reset();//重置
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);//设置空心
        /*Path path1=new Path();
        path1.moveTo(600, 1300);
        path1.lineTo(550, 1350);
        path1.lineTo(600, 1400);
        path1.lineTo(700, 1400);
        path1.lineTo(750, 1350);
        path1.lineTo(700, 1300);
        path1.close();//封闭
        canvas.drawPath(path1, p);
		*/
        /*
		 * Path类封装复合(多轮廓几何图形的路径
		 * 由直线段*、二次曲线,和三次方曲线，也可画以油画。drawPath(路径、油漆),要么已填充的或抚摸
		 * (基于油漆的风格),或者可以用于剪断或画画的文本在路径。
		 */

        //画圆角矩形
        p.setStyle(Paint.Style.FILL);//充满
        p.setColor(Color.RED);
        p.setAntiAlias(true);// 设置画笔的锯齿效果
        p.setTextSize(24 * getResources().getDisplayMetrics().density);
        canvas.drawText("画圆角矩形:", 100, 1400, p);
        RectF oval3 = new RectF(600, 1200, 800, 1300);// 设置个新的长方形
        canvas.drawRoundRect(oval3, 50, 20, p);//第二个参数是x半径，第三个参数是y半径

        //画贝塞尔曲线
        canvas.drawText("画贝塞尔曲线:", 100, 1500, p);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.GREEN);
        Path path2 = new Path();
        path2.moveTo(200, 1300);//设置Path的起点
        path2.quadTo(400, 1400, 600, 1300); //设置贝塞尔曲线的控制点坐标和终点坐标
        canvas.drawPath(path2, p);//画出贝塞尔曲线

        //画点
        p.reset();
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.FILL);
        p.setTextSize(24 * getResources().getDisplayMetrics().density);
        canvas.drawText("画点：", 100, 1600, p);
        canvas.drawPoint(600, 1600, p);//画一个点
        canvas.drawPoints(new float[]{400, 1650, 500, 1650, 600, 1650}, p);//画多个点
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        canvas.drawBitmap(bitmap, 200, 1000, p);
    }

    //这样的情况下，需要创建Canvas对象，然后在此对象上进行操作
    //对bitmap操作完成后，，显示该Bitmap有以下两种操作。
    //1、需要将bitmap转换为Drawable对象  Drawable drawable = new BitmapDrawable(bitmap) ;
    //2、直接setImageBitmap(bitmap)
    private void clip_drawCanvas(Canvas canvas, Paint paint) {
        //将icon图像转换为Bitmap对象
        Bitmap iconbit = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

        //创建一个的Bitmap对象
        Bitmap bitmap = Bitmap.createBitmap(200, 150, Bitmap.Config.ARGB_8888);

        //设置颜色来显示画图区域
        canvas.drawColor(Color.RED);

        paint.setColor(Color.BLACK);
        canvas.drawText("原先的画图区域--红色部分", 100, 400, paint);
        //画bitmap对象
        canvas.drawBitmap(iconbit, 20, 20, paint);

        //剪裁一个区域，当前的操作对象为Rect裁剪的区域
        Rect rect = new Rect(100, 200, 600, 1000);

        //当前的画图区域为Rect裁剪的区域，而不是我们之前赋值的bitmap
        canvas.clipRect(rect);
        canvas.drawColor(Color.YELLOW);
        //设置颜色来显示画图区域
        paint.setColor(Color.BLACK);
        canvas.drawText("裁剪clip后画图区域-黄色部分", 200, 300, paint);

        //将Bitmap对象转换为Drawable图像资源
        //Drawable drawable = new BitmapDrawable(bitmap) ;
        //img.setBackgroundDrawable(drawable) ;

        //显示，同上
        // imgClip.setImageBitmap(bitmap);
    }

    private void save_drawCanvas(Canvas canvas, Paint paint) {
        //将icon图像转换为Bitmap对象
        Bitmap iconbit = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

        //创建一个的Bitmap对象
        Bitmap bitmap = Bitmap.createBitmap(200, 100, Bitmap.Config.ARGB_8888);

        paint.setColor(Color.GREEN);
        paint.setTextSize(16);  //设置字体大小
        canvas.drawRect(100, 100, 600, 600, paint);
        canvas.drawText("我没有旋转", 200, 200, paint);
        //保存canvas之前的操作,在sava()和restore之间的操作不会对canvas之前的操作进行影响
        canvas.save();

        //顺时针旋转30度
        canvas.rotate(30);
        canvas.drawColor(Color.RED);
        canvas.drawBitmap(iconbit, 20, 20, paint);
        canvas.drawRect(50, 10, 80, 50, paint);
        //canvas.translate(20,20);
        canvas.drawText("我是旋转的", 115, 20, paint);

        //复原之前save()之前的属性,并且将save()方法之后的roate(),translate()以及clipXXX()方法的操作清空
        canvas.restore();

        //平移(20,20)个像素
        //canvas.translate(20,20);
        canvas.drawRect(80, 10, 110, 30, paint);
        canvas.drawText("我没有旋转222", 488, 799, paint);

        //将Bitmap对象转换为Drawable图像资
        //为ImageView设置图像
        //imgSave.setImageBitmap(bitmap);

        Drawable drawable = new BitmapDrawable(bitmap);
        //imgSave.setBackgroundDrawable(drawable) ;

    }
}

