package ui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Mario {
    BufferedImage img;
    int x;// ������
    int y;// ������
    int w;// ���
    int h;// �߶�
    int mvSpeed = 10; // ����µ��ƶ��ٶ�
    int dir; // ������ƶ��ķ���

    List<BufferedImage> imgR = new ArrayList<BufferedImage>(); // ����һ�����ϣ�������������ƶ��Ķ��������ͼƬ
    List<BufferedImage> imgL = new ArrayList<BufferedImage>(); // ����һ�����ϣ�������������ƶ��Ķ��������ͼƬ
    BufferedImage jumpR = ImageUtil.getImg("/img/mario/5.png");// ����������Ծʱ��Ķ���
    BufferedImage jumpL = ImageUtil.getImg("/img/mario/10.png");// ����������Ծʱ��Ķ���

    /* ������ʵ����Ծ */
    /* v0*t-1/2*g*t*t */
    double g = 5; // �������ٶ�
    double t = 0.25; // �ƶ���ʱ��
    double s; // tʱ����λ��
    double speed; // ��ʼ�ٶ�
    int groundY; // ������ڵ����������
    boolean isJump; // �Ƿ�����Ծ״̬

    public void step() {
        double v0 = speed; // ��ʼ�ٶ�
        s = v0 * t - 1 / 2 * g * t * t; // λ��
        double vt = v0 - g * t; // tʱ���ĵ�ǰ�ٶ�
        speed = vt; // �´��ƶ��ĳ�ʼ�ٶ�
        y -= (int) s; // �޸�����µ�������
        if (y >= groundY) {// �����������䵽�����λ��ʱ
            y = groundY; // ���������ͣ���ڵ����λ��

        }
    }

    public Mario() {
        for (int i = 1; i <= 4; i++) {
            imgR.add(ImageUtil.getImg("/img/mario/R" + i + ".png"));// ���������ƶ��Ķ���ͼƬ�������뵽���ҵļ��ϵ���
            imgL.add(ImageUtil.getImg("/img/mario/L" + i + ".png"));// ���������ƶ��Ķ���ͼƬ�������뵽����ļ��ϵ���
        }
        img = ImageUtil.getImg("/img/mario/R1.png");
        w = img.getWidth();
        h = img.getHeight();
        x = 400;
        groundY = y = 600 - h - 50; // ȷ��������ڵ����ϵ�λ��
    }

    int index = 0; // ���弯�ϵ��±�

    public void changeImg() {
        index++;
        if (index == imgR.size()) { // ����±�ﵽ�˼��ϵĳ���
            index = 0; // �����±���㣬�����ӵ�һ��ͼƬ��ʼ��ʾ
        }
        if (dir == App.DIR_RIGHT) { // �����������
            img = imgR.get(index); // ������Ҽ�����ȡ��ͼƬ���滻����µ�ǰ��ʾ��ͼƬ��imgR�Ǹ����ϣ�get�Ǽ����еķ�����
        } else {// �����������
            img = imgL.get(index); // ������Ҽ�����ȡ��ͼƬ���滻����µ�ǰ��ʾ��ͼƬ��imgR�Ǹ����ϣ�get�Ǽ����еķ�����
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
