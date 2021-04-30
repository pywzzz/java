package ui;

import java.awt.image.BufferedImage;

public class Mushroom {
    BufferedImage img;
    int x;
    int y;
    int w;
    int h;
    BufferedImage deadImg = ImageUtil.getImg("/img/monster/triangle3.png");

    public Mushroom() {
        img = ImageUtil.getImg("/img/monster/triangle1.png");
        w = img.getWidth();
        h = img.getHeight();
        x = 900 - w;
        y = 600 - h - 50;
    }

    public boolean caiBy(Mario m) {
        boolean hit = x <= m.x + m.w && x >= m.x - w && y <= m.y + m.h;
        return hit && m.isJump; // 踩到并且是跳跃状态就返回true，
    }

    boolean isDie; // 判蘑菇是否死亡

    public void goDie() {
        img = deadImg; // 蘑菇如果死亡的话就显示这张死亡的图片
        isDie = true; // 蘑菇已经死亡
        w = img.getWidth();
        h = img.getHeight();
        y = 600 - h - 50; // 重新设置蘑菇地面的位置

    }

}
