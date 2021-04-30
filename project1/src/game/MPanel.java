package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.sound.sampled.*;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer; //��������Ǳ��Timer������java.util�е�Timerɶ�ģ�

public class MPanel extends JPanel implements KeyListener, ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = -4696865800008914037L;
    ImageIcon title;// ����һ�������ͼƬ
    ImageIcon body;
    ImageIcon food;
    ImageIcon left;
    ImageIcon right;
    ImageIcon up;
    ImageIcon down;

    int len = 3;
    int score = 0;
    int[] snakex = new int[750];
    int[] snakey = new int[750];
    String fx = "D";
    boolean isStared = false; // ���ø�����ֵ����Ӧ���Ǹ����ո�ʼ
    boolean isFailed = false; // ����Ϸ�Ƿ�ʧ��
    boolean isMusic = false; // ��Ҫ��Ҫ����
    Timer timer = new Timer(100, this); // 100ms�󵽵㣬�����ȥ��this����Ϊ����this�����Ե�ʵ��ActionListener����ӿڣ�
    int foodx;
    int foody;
    Random rand = new Random();
    Clip bgm; // ��������

    public MPanel() {// ���캯��
        loadImages();
        initSnake();
        this.setFocusable(true); // �����Ƿ���Ի�ȡ���㣬Ҳ�Ͷ�����Ի�ü����¼�
        this.addKeyListener(this); // ���˼���֮��˭����������¼�����this��Ҳ�����Լ���������������¼�����Ϊ��this������MPanel�Լ���ʵ�ָ�eyListener�ӿڣ�
        timer.start(); // ��ʱ��������
        loadBGM(); // ����BGM
    }

    @Override
    protected void paintComponent(Graphics g) { // ������g�������

        super.paintComponent(g); // �����Ѿ���������������
        this.setBackground(Color.GREEN); // �����˸���ɫ����
        title.paintIcon(this, g, 25, 11); // ��һ���α�ʾ�������Ż����ϣ�Ҳ����this���ڶ���������ʾ����g��������������ʾ��Ի�����λ�ã����Ͻ�Ϊԭ�㣩
        g.fillRect(25, 75, 850, 600); // �����Ū����Ϸ����������ͼƬ����Ҳ�����û���g�е�fillRect�������������� ǰ������������ʾ��Ի������ã������������������Ĵ�С
        g.setColor(Color.WHITE);// Ҫ��Ȼ�����Len��Score������

        /* �������������� */
        g.drawString("Len:" + len, 750, 35);
        g.drawString("Score:" + score, 750, 50);
        if (fx == "R") {
            right.paintIcon(this, g, snakex[0], snakey[0]);
        } else if (fx == "L") {
            left.paintIcon(this, g, snakex[0], snakey[0]);

        } else if (fx == "U") {
            up.paintIcon(this, g, snakex[0], snakey[0]);

        } else if (fx == "D") {
            down.paintIcon(this, g, snakex[0], snakey[0]);

        }

        for (int i = 1; i < len; i++) {
            body.paintIcon(this, g, snakex[i], snakey[i]);
        }

        food.paintIcon(this, g, foodx, foody);

        if (isStared == false) { // ��ʼ��Ϸ
            g.setColor(Color.WHITE);// ���ʵ���ɫ���ڵ׺ڻ���������
            g.setFont(new Font("", Font.BOLD, 40)); // Font�ĵ�һ�������ǣ�ʲô���͵����壨������ɶ�ģ����ڶ����������ֵ����ӣ�б�����ɶ�ģ������������ֵĴ�С
            g.drawString("���ո�ʼ", 300, 300);
        }

        if (isFailed) { // ���¿�ʼ��Ϸ
            g.setColor(Color.RED);
            g.setFont(new Font("", Font.BOLD, 100)); // Font�ĵ�һ�������ǣ�ʲô���͵����壨������ɶ�ģ����ڶ����������ֵ����ӣ�б�����ɶ�ģ������������ֵĴ�С
            g.drawString("����", 280, 400);
        }

    }

    public void initSnake() {// ��ʼ����
        len = 3;
        snakex[0] = 100;
        snakey[0] = 100;
        snakex[1] = 75;
        snakey[1] = 100;
        snakex[2] = 50;
        snakey[2] = 100;
        foodx = 25 + 25 * rand.nextInt(34); // ����0~33��Χ��һ�����������������һ����34�����ӣ�
        foody = 75 + 25 * rand.nextInt(24); // ����0~23��Χ��һ�����������������һ����24�����ӣ�
        fx = "R"; // ���¿�ʼ��Ϸʱ����ͷ�����ұߣ�Ҫ����ȥһ�غϸ�ֱ��ʧ���ˣ�
        score = 0;
    }

    /* ���������@Override����KeyListener����е���������������������һ��ʵ�־��� */

    @Override
    public void keyPressed(KeyEvent e) {
        // ���õ�����
        int keyCode = e.getKeyCode(); // ������ÿ��������Ӧ��һ�����֣�������getKeyCode()����ȡ�������
        if (keyCode == KeyEvent.VK_M) {
            isMusic = !isMusic;
        }
        if (keyCode == KeyEvent.VK_SPACE) {// ����ģ�KeyEvent.VK_SPACEҲ��д��32���ո����Ӧ32����������ʾ���л�˵�������ļ��������ּ���
            if (isFailed) { // ��Ϸ�����������ʵ�����¿�ʼ��Ϸ
                isFailed = false; // ���¿�ʼ��Ϸʱ��isFailed��ֵ��false
                initSnake(); // ���³�ʼ���ߴӶ����¿�ʼ��Ϸ
            } else {
                isStared = !isStared; // �ø�̾��ʵ��true��false֮���ת��
            }
            repaint(); // �ٻ�һ�Ρ�Ȼ��false�Ļ��Ͳ�ִ�� if (isStared == false)�Ĵ����ȥ�������ո�ȥ��ʼ����
            if (isStared && isMusic) {// �����ʱ����Ϸ�е�״̬��ȷ��Ҫ����BGM
                playBGM(); // �򲥷�BGM
            } else {
                stopBGM(); // ��������򲻲���
            }
        } else if (keyCode == KeyEvent.VK_A) {
            fx = "L";
        } else if (keyCode == KeyEvent.VK_D) {
            fx = "R";
        } else if (keyCode == KeyEvent.VK_W) {
            fx = "U";
        } else if (keyCode == KeyEvent.VK_S) {
            fx = "D";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // ��̧������
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // ������
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // ���캯���еĵ�ʱ��star��100ms���������������
        if (isStared && !isFailed) { // isStared��֤���true�������˿ո񣩣��߲Żᶯ��!isFailedȥ����Ϸ�Ƿ�ʧ����
            for (int i = len - 1; i > 0; i--) {// ������
                snakex[i] = snakex[i - 1];
                snakey[i] = snakey[i - 1];
            }
            if (fx == "R") {
                snakex[0] = snakex[0] + 25; // ������ͷ
                if (snakex[0] > 850) {
                    snakex[0] = 25;
                }
            } else if (fx == "L") {
                snakex[0] = snakex[0] - 25; // ������ͷ
                if (snakex[0] < 25) {
                    snakex[0] = 850;
                }

            } else if (fx == "U") {
                snakey[0] = snakey[0] - 25; // ������ͷ
                if (snakey[0] < 75) {
                    snakey[0] = 650;
                }

            } else if (fx == "D") {
                snakey[0] = snakey[0] + 25; // ������ͷ
                if (snakey[0] > 650) {
                    snakey[0] = 75;
                }

            }

            if (snakex[0] == foodx && snakey[0] == foody) {// ��ͷ��ʳ�������غϼ��Ե���ʳ��
                len++; // ����ʳ��֮���߳���+1
                score += 1;

                /* �Ե�ʳ��֮����������ʳ�� */
                foodx = 25 + 25 * rand.nextInt(34);
                foody = 75 + 25 * rand.nextInt(24);
            }

            for (int i = 1; i < len; i++) {
                if (snakex[0] <= 25 || snakex[0] >= 850 || snakey[0] >= 650 || snakey[0] <= 75) {
                    isFailed = true; // ����ǽ��ʧ��
                } else if (snakex[i] == snakex[0] && snakey[i] == snakey[0]) {// ��ͷ����β�Ƿ��غ�
                    isFailed = true; // �غ���ʧ��
                }
            }
            repaint();
        }

        timer.start();// 100ms��ʱ����������Ҫ������һ��ʱ�Ӿ�����һ�Σ�

    }

    private void loadBGM() { // ����BGM
        try {
            bgm = AudioSystem.getClip();
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("/resource/bgm.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));// ���ֱ����is������new
                                                                                                // BufferedInputStream(is)���򵼳�Ϊjar�ļ���getAudioInputStream��get�����㹻�����ݣ��Ӷ�BGM���Ქ��
            bgm.open(ais);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void playBGM() {
        /* bgm.start(); // ����һ�ξ�ֹͣ */
        bgm.loop(Clip.LOOP_CONTINUOUSLY);// ��ͣ��ѭ������
    }

    private void stopBGM() {
        bgm.stop();
    }

    private void loadImages() {
        InputStream is;
        try {
            is = getClass().getClassLoader().getResourceAsStream("/resource/title.jpg");
            title = new ImageIcon(ImageIO.read(is));
            is = getClass().getClassLoader().getResourceAsStream("/resource/body.png");
            body = new ImageIcon(ImageIO.read(is));
            is = getClass().getClassLoader().getResourceAsStream("/resource/food.png");
            food = new ImageIcon(ImageIO.read(is));
            is = getClass().getClassLoader().getResourceAsStream("/resource/left.png");
            left = new ImageIcon(ImageIO.read(is));
            is = getClass().getClassLoader().getResourceAsStream("/resource/right.png");
            right = new ImageIcon(ImageIO.read(is));
            is = getClass().getClassLoader().getResourceAsStream("/resource/up.png");
            up = new ImageIcon(ImageIO.read(is));
            is = getClass().getClassLoader().getResourceAsStream("/resource/down.png");
            down = new ImageIcon(ImageIO.read(is));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
