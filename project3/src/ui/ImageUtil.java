package ui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {
    public static BufferedImage getImg(String path) {

        try {// ImageIO����img�ļ��кͳ���֮�佨����IO���ܵ�
             // ImageUtil.class.getResource(path)������path��Ӧ��ͼƬת������
             // ImageIO.read����ת���õ�����ȡ���ܵ������䵽������
            BufferedImage img = ImageIO.read(ImageUtil.class.getResource(path));
            return img;   //����ҵ��Ļ��ͷ���img��û���ҵ�����ͼ�Ļ��ͷ���null
        } catch (

        IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
