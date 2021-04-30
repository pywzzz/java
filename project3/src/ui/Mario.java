package ui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Mario {
    BufferedImage img;
    int x;// 横坐标
    int y;// 纵坐标
    int w;// 宽度
    int h;// 高度
    int mvSpeed = 10; // 马里奥的移动速度
    int dir; // 马里奥移动的方向

    List<BufferedImage> imgR = new ArrayList<BufferedImage>(); // 定义一个集合，用来存放向右移动的动画数组的图片
    List<BufferedImage> imgL = new ArrayList<BufferedImage>(); // 定义一个集合，用来存放向左移动的动画数组的图片
    BufferedImage jumpR = ImageUtil.getImg("/img/mario/5.png");// 定义向右跳跃时候的动画
    BufferedImage jumpL = ImageUtil.getImg("/img/mario/10.png");// 定义向左跳跃时候的动画

    /* 下面来实现跳跃 */
    /* v0*t-1/2*g*t*t */
    double g = 5; // 重力加速度
    double t = 0.25; // 移动的时间
    double s; // t时间后的位移
    double speed; // 初始速度
    int groundY; // 马里奥在地面的纵坐标
    boolean isJump; // 是否处于跳跃状态

    public void step() {
        double v0 = speed; // 初始速度
        s = v0 * t - 1 / 2 * g * t * t; // 位移
        double vt = v0 - g * t; // t时间后的当前速度
        speed = vt; // 下次移动的初始速度
        y -= (int) s; // 修改马里奥的纵坐标
        if (y >= groundY) {// 如果马里奥下落到地面的位置时
            y = groundY; // 则让马里奥停留在地面的位置

        }
    }

    public Mario() {
        for (int i = 1; i <= 4; i++) {
            imgR.add(ImageUtil.getImg("/img/mario/R" + i + ".png"));// 加载向右移动的动画图片，并加入到向右的集合当中
            imgL.add(ImageUtil.getImg("/img/mario/L" + i + ".png"));// 加载向左移动的动画图片，并加入到向左的集合当中
        }
        img = ImageUtil.getImg("/img/mario/R1.png");
        w = img.getWidth();
        h = img.getHeight();
        x = 400;
        groundY = y = 600 - h - 50; // 确定马里奥在地面上的位置
    }

    int index = 0; // 定义集合的下标

    public void changeImg() {
        index++;
        if (index == imgR.size()) { // 如果下标达到了集合的长度
            index = 0; // 则让下标归零，继续从第一张图片开始显示
        }
        if (dir == App.DIR_RIGHT) { // 如果方向向右
            img = imgR.get(index); // 则从向右集合中取出图片，替换马里奥当前显示的图片（imgR是个集合，get是集合中的方法）
        } else {// 如果方向向左
            img = imgL.get(index); // 则从向右集合中取出图片，替换马里奥当前显示的图片（imgR是个集合，get是集合中的方法）
        }

    }

    public void jump() {
        isJump = true;
        speed = 40;
        if (dir == App.DIR_RIGHT) {
            img = jumpR;
        } else {
            img = jumpL;

        }
    }

}
