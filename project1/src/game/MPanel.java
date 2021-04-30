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
import javax.swing.Timer; //这儿不能是别的Timer（不能java.util中的Timer啥的）

public class MPanel extends JPanel implements KeyListener, ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = -4696865800008914037L;
    ImageIcon title;// 加上一个标题的图片
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
    boolean isStared = false; // 设置个布尔值，对应着那个按空格开始
    boolean isFailed = false; // 判游戏是否失败
    boolean isMusic = false; // 判要不要音乐
    Timer timer = new Timer(100, this); // 100ms后到点，到点后去找this（因为又是this，所以得实现ActionListener这个接口）
    int foodx;
    int foody;
    Random rand = new Random();
    Clip bgm; // 背景音乐

    public MPanel() {// 构造函数
        loadImages();
        initSnake();
        this.setFocusable(true); // 定义是否可以获取焦点，也就定义可以获得键盘事件
        this.addKeyListener(this); // 敲了键盘之后谁来处理这个事件，用this，也就是自己，来处理这个就事件（因为是this，所以MPanel自己得实现个eyListener接口）
        timer.start(); // 让时钟启动。
        loadBGM(); // 加载BGM
    }

    @Override
    protected void paintComponent(Graphics g) { // 传进来g这个画笔

        super.paintComponent(g); // 父类已经将基本工作做好
        this.setBackground(Color.GREEN); // 设置了个绿色背景
        title.paintIcon(this, g, 25, 11); // 第一个参表示画在这张画布上，也就是this，第二个参数表示画笔g，后两个参数表示相对画布的位置（左上角为原点）
        g.fillRect(25, 75, 850, 600); // 这儿是弄个游戏区，可以用图片，但也可以用画笔g中的fillRect函数来填满方框 前两个参数，表示相对画布的置，后两个参数是这个框的大小
        g.setColor(Color.WHITE);// 要不然下面的Len跟Score看不清

        /* 这俩参数是坐标 */
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

        if (isStared == false) { // 开始游戏
            g.setColor(Color.WHITE);// 画笔的颜色，黑底黑还看你吗呢
            g.setFont(new Font("", Font.BOLD, 40)); // Font的第一个参数是，什么类型的字体（如宋体啥的），第二个参数是字的样子（斜体粗体啥的），第三个是字的大小
            g.drawString("按空格开始", 300, 300);
        }

        if (isFailed) { // 重新开始游戏
            g.setColor(Color.RED);
            g.setFont(new Font("", Font.BOLD, 100)); // Font的第一个参数是，什么类型的字体（如宋体啥的），第二个参数是字的样子（斜体粗体啥的），第三个是字的大小
            g.drawString("好死", 280, 400);
        }

    }

    public void initSnake() {// 初始化蛇
        len = 3;
        snakex[0] = 100;
        snakey[0] = 100;
        snakex[1] = 75;
        snakey[1] = 100;
        snakex[2] = 50;
        snakey[2] = 100;
        foodx = 25 + 25 * rand.nextInt(34); // 生成0~33范围内一个整数随机数（横着一共有34个格子）
        foody = 75 + 25 * rand.nextInt(24); // 生成0~23范围内一个整数随机数（竖着一共有24个格子）
        fx = "R"; // 重新开始游戏时候蛇头朝向右边（要不上去一重合该直接失败了）
        score = 0;
    }

    /* 这儿的三个@Override就是KeyListener借口中的三个方法，在这儿随便找一个实现就行 */

    @Override
    public void keyPressed(KeyEvent e) {
        // 键敲到底了
        int keyCode = e.getKeyCode(); // 键盘上每个键都对应着一个数字，可以用getKeyCode()来获取这个数字
        if (keyCode == KeyEvent.VK_M) {
            isMusic = !isMusic;
        }
        if (keyCode == KeyEvent.VK_SPACE) {// 这儿的，KeyEvent.VK_SPACE也可写成32（空格键对应32）（代码提示器中还说了其他的键代表数字几）
            if (isFailed) { // 游戏结束，代码块实现重新开始游戏
                isFailed = false; // 重新开始游戏时候，isFailed的值是false
                initSnake(); // 重新初始化蛇从而重新开始游戏
            } else {
                isStared = !isStared; // 用感叹号实现true跟false之间的转换
            }
            repaint(); // 再画一次。然后false的话就不执行 if (isStared == false)的代码块去画“按空格去开始”了
            if (isStared && isMusic) {// 如果这时是游戏中的状态且确定要播放BGM
                playBGM(); // 则播放BGM
            } else {
                stopBGM(); // 如果不是则不播放
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
        // 键抬起来了
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // 键敲了
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 构造函数中的的时钟star，100ms后来调用这个函数
        if (isStared && !isFailed) { // isStared保证如果true（即敲了空格），蛇才会动；!isFailed去判游戏是否失败了
            for (int i = len - 1; i > 0; i--) {// 移身体
                snakex[i] = snakex[i - 1];
                snakey[i] = snakey[i - 1];
            }
            if (fx == "R") {
                snakex[0] = snakex[0] + 25; // 右移蛇头
                if (snakex[0] > 850) {
                    snakex[0] = 25;
                }
            } else if (fx == "L") {
                snakex[0] = snakex[0] - 25; // 左移蛇头
                if (snakex[0] < 25) {
                    snakex[0] = 850;
                }

            } else if (fx == "U") {
                snakey[0] = snakey[0] - 25; // 上移蛇头
                if (snakey[0] < 75) {
                    snakey[0] = 650;
                }

            } else if (fx == "D") {
                snakey[0] = snakey[0] + 25; // 下移蛇头
                if (snakey[0] > 650) {
                    snakey[0] = 75;
                }

            }

            if (snakex[0] == foodx && snakey[0] == foody) {// 蛇头和食物坐标重合即吃掉了食物
                len++; // 吃完食物之后蛇长度+1
                score += 1;

                /* 吃掉食物之后重新生成食物 */
                foodx = 25 + 25 * rand.nextInt(34);
                foody = 75 + 25 * rand.nextInt(24);
            }

            for (int i = 1; i < len; i++) {
                if (snakex[0] <= 25 || snakex[0] >= 850 || snakey[0] >= 650 || snakey[0] <= 75) {
                    isFailed = true; // 碰到墙则失败
                } else if (snakex[i] == snakex[0] && snakey[i] == snakey[0]) {// 蛇头和蛇尾是否重合
                    isFailed = true; // 重合则失败
                }
            }
            repaint();
        }

        timer.start();// 100ms后时钟再启动（要不加这一句时钟就启动一次）

    }

    private void loadBGM() { // 加载BGM
        try {
            bgm = AudioSystem.getClip();
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("/resource/bgm.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));// 这儿直接填is而不是new
                                                                                                // BufferedInputStream(is)，则导出为jar文件后getAudioInputStream会get不到足够的数据，从而BGM不会播放
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
        /* bgm.start(); // 播放一次就停止 */
        bgm.loop(Clip.LOOP_CONTINUOUSLY);// 不停地循环播放
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
