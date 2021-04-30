package game;

import javax.swing.JFrame;

public class Msnake {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(50, 50, 900, 720); // 前两个参数是这个框出现时候左上角的坐标，后两个参数是窗口的大小
        frame.setResizable(false); // 表示不能手动改变这个窗口的大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 点击叉号关闭
        frame.add(new MPanel());// 执行那个类
        frame.setVisible(true); // 让这个窗口显示出来
    }
}
