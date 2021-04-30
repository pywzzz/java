package ui;

import javax.swing.JFrame;

/**
 * GameFrame
 */
public class GameFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public GameFrame() {
        setTitle("ZZZ");
        setSize(900, 600);
        setResizable(false);
        setLocationRelativeTo(null); // 相对谁去居中（null，即给空，就是相对于屏幕左上角居中）
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 哦，这个要是不设置一下的话你点叉号，这个程序还是在运行的，弄一下这个才能达到点叉号然后关闭程序

    }

    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        GamePanel panel = new GamePanel();
        frame.add(panel); // 将panel这个面板传到frame这个框体上
        frame.setVisible(true);
        panel.requestFocus();  //如果要让键盘起到作用，就一定要让面板获取到事件的焦点
    }

}