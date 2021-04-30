package ui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {
    public static BufferedImage getImg(String path) {

        try {// ImageIO就是img文件夹和程序之间建立的IO流管道
             // ImageUtil.class.getResource(path)就是让path对应的图片转换成流
             // ImageIO.read是让转换好的流读取到管道并传输到程序中
            BufferedImage img = ImageIO.read(ImageUtil.class.getResource(path));
            return img;   //如果找到的话就返回img，没有找到这张图的话就返回null
        } catch (

        IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
