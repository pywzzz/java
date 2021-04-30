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
        setLocationRelativeTo(null); // ���˭ȥ���У�null�������գ������������Ļ���ϽǾ��У�
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ŷ�����Ҫ�ǲ�����һ�µĻ�����ţ���������������еģ�Ūһ��������ܴﵽ����Ȼ��رճ���

    }

    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        GamePanel panel = new GamePanel();
        frame.add(panel); // ��panel�����崫��frame���������
        frame.setVisible(true);
        panel.requestFocus();  //���Ҫ�ü��������ã���һ��Ҫ������ȡ���¼��Ľ���
    }

}