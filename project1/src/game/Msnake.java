package game;

import javax.swing.JFrame;

public class Msnake {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(50, 50, 900, 720); // ǰ������������������ʱ�����Ͻǵ����꣬�����������Ǵ��ڵĴ�С
        frame.setResizable(false); // ��ʾ�����ֶ��ı�������ڵĴ�С
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �����Źر�
        frame.add(new MPanel());// ִ���Ǹ���
        frame.setVisible(true); // �����������ʾ����
    }
}
