package tankgame;

import javax.swing.JFrame;
import java.awt.event.*;
import java.util.Scanner;

public class TankGame extends JFrame {
    MyPanel mp = null; // ������һ�����Ż�
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TankGame tankGame = new TankGame();
    }

    public TankGame() {
        System.out.println("������ѡ��  1������Ϸ   2��������һ��");
        String key = scanner.next();
        mp = new MyPanel(key);

        // ��mp�ŵ�thread�в������߳�
        Thread thread = new Thread(mp);
        thread.start();

        this.add(mp); // ��mp���Ż��ŵ�������
        this.setSize(1300, 750);
        this.addKeyListener(mp); // ��JFrame����mp�ļ����¼�
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���óɵ����ž��˳�����

        this.addWindowListener(new WindowAdapter() { // ͨ����Ѷ�����ʵ�ֹرմ���ʱ������Զ�����keepRecord����
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
