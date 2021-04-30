package Saolei;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Saolei implements ActionListener {

    JFrame frame = new JFrame();
    ImageIcon banner = new ImageIcon("banner.png"); // 添加一个按钮
    ImageIcon bomb = new ImageIcon("bomb.png");
    ImageIcon fail = new ImageIcon("fail.png");
    ImageIcon guess = new ImageIcon("guess.png");
    ImageIcon win = new ImageIcon("win.png");
    ImageIcon win_flag = new ImageIcon("win_flag.png");
    JButton bannerBtn = new JButton(banner); // 显示banner这张图片

    /* 下面是数据结构 */
    int ROW = 20;
    int COL = 20;
    int[][] data = new int[ROW][COL]; // 每个雷对应着一个数据，所以定义这个整形数组来存放这些数据（如果是雷的话，就放-1；不是雷的话就放0~8：代表周围的雷的个数）
    JButton[][] btns = new JButton[ROW][COL];
    int LEICOUNT = 15; // 一共有多少雷
    int LEICODE = -1; // -1代表这儿是雷
    int unopened = ROW * COL; // 没有扫到的雷
    int opened = 0; // 扫到的雷（默认为0）
    int seconds = 0;// 设个时钟来计时

    /* 将NORTH部分，再细分一下，再原有的基础上再下方加上三个状态栏 */
    JLabel label1 = new JLabel("还剩的雷" + unopened);
    JLabel label2 = new JLabel("已扫的雷" + opened);
    JLabel label3 = new JLabel("用时" + seconds + "s");
    Timer timer = new Timer(1000, this); // 第一个参数是时钟多少时间触发一次（1000ms），第二是参数是触发后找谁

    public Saolei() { // 构造函数
        frame.setSize(600, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());// 这就是一种布局
        setHeader();
        addLei();// 在每个格子后添加对应数字来代表有没有雷啥的
        setButtons();
        timer.start();
        frame.setVisible(true);

    }

    private void addLei() {
        /* 下面来在随机位置生成LEICOUNT个雷 */
        Random rand = new Random();
        for (int i = 0; i < LEICOUNT;) {
            int r = rand.nextInt(ROW);
            int c = rand.nextInt(COL);
            if (data[r][c] != LEICODE) {
                data[r][c] = LEICODE;
                i++;
            }
        }

        /* 上面一步使得400个格子对应的数字，有LEICODE个放-1，其余为0 */
        /* 现在要把这个“其余”变成0~8：表示它周围有几颗雷 */
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (data[i][j] == LEICODE) { // 如果这儿是雷的话就continue
                    continue;
                }
                int tempCount = 0;

                /* 下面8个if的条件的的前半部分是判是否越界 */
                if (i > 0 && j > 0 && data[i - 1][j - 1] == LEICODE) {
                    tempCount++;
                }
                if (i > 0 && data[i - 1][j] == LEICODE) {
                    tempCount++;
                }
                if (i > 0 && j < 19 && data[i - 1][j + 1] == LEICODE) {
                    tempCount++;
                }
                if (j > 0 && data[i][j - 1] == LEICODE) {
                    tempCount++;
                }
                if (j < 19 && data[i][j + 1] == LEICODE) {
                    tempCount++;
                }
                if (i < 19 && j > 0 && data[i + 1][j - 1] == LEICODE) {
                    tempCount++;
                }
                if (i < 19 && data[i + 1][j] == LEICODE) {
                    tempCount++;
                }
                if (i < 19 && j < 19 && data[i + 1][j + 1] == LEICODE) {
                    tempCount++;
                }
                data[i][j] = tempCount;

            }

        }
    }

    private void setButtons() {
        Container con = new Container(); // 需要一个容器让这些按钮塞进去
        con.setLayout(new GridLayout(ROW, COL));// 给Container设置一个布局
        for (int i = 0; i < ROW; i++) { // 用两个for循环把这些按钮都创建出来
            for (int j = 0; j < COL; j++) {

                // JButton btn = new JButton(data[i][j] + ""); // + ""是因为这儿要存放字符串的对象

                JButton btn = new JButton(guess);

                /* 设置个背景色 */
                btn.setOpaque(true);
                btn.setBackground(new Color(244, 183, 113));

                btn.setMargin(new Insets(0, 0, 0, 0)); // 显示...而不显示数字是因为格子太小，数字放不下,加上这个之后就可以不显示...而显示数字了
                btn.addActionListener(this); // 监听btn按钮。这个要实现ActionListener接口
                con.add(btn); // 把btn加载到con当中
                btns[i][j] = btn; // 放到上面你定义的btns数组中，之后还要用（评论区偷的）
            }

        }

        frame.add(con, BorderLayout.CENTER); // 将con加载到frame中（放在BorderLayout的CENTER位置）
    }

    private void setHeader() {
        JPanel panel = new JPanel(new GridBagLayout()); // 现在有了bannerIcon跟三个label了，现在需要一个“容器”把它们三个装起来，这儿是，在它们下面垫一块“布”，也就是这儿的JPnel，它的布局为GridBagLayout()

        /* 下面来把这三个label跟一个bannerIcon按钮加载到这个画布上 */
        /* 布局，也就是做界面，确实很复杂 */
        GridBagConstraints c1 = new GridBagConstraints(0, 0, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0); // 前两个参数是图片左上角的坐标，第二个是宽度（这儿宽度是3，因为它横跨3个格子。对了，这个3不是指3个像素啥的）。第三个是高度。（后面几个参数不重要）
        panel.add(bannerBtn, c1); // 指定把bannerBtn加载到c1，上面的那行代码是加载在的那个地方要满足的条件
        bannerBtn.addActionListener(this); // 之前没加这句话时，点这个按钮没有反应，加了这个后，点了，它会去找this

        label1.setOpaque(true); // 设置成不透明，你要是透明的话怎么设置颜色啥的啊
        label1.setBackground(Color.white); // 背景色设置白色
        label1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // 设置个灰色的边框

        label2.setOpaque(true);
        label2.setBackground(Color.white);
        label2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        label3.setOpaque(true);
        label3.setBackground(Color.white);
        label3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        bannerBtn.setOpaque(true);
        bannerBtn.setBackground(Color.white);
        bannerBtn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        GridBagConstraints c2 = new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        panel.add(label1, c2);
        GridBagConstraints c3 = new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        panel.add(label2, c3);
        GridBagConstraints c4 = new GridBagConstraints(2, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        panel.add(label3, c4);

        frame.add(panel, BorderLayout.NORTH); // 把上面那一堆代码弄好的画布panel放在这个BorderLayout布局的上面，就是是NORTH
    }

    public static void main(String[] args) {
        new Saolei();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof Timer) { // 如果是Timer触发了这个“监听”
            seconds++;
            label3.setText("用时" + seconds + "s");
            timer.start();
            return;

        }

        JButton btn = (JButton) e.getSource(); // getSource()是获取e这个事件触发的根源，它的返回值可能是别的东西，所以我们进行一个强制类型转换
        if (btn.equals(bannerBtn)) {// 判断出是上面的那个大的按钮还是下面那三个label，也就是小的按钮
            restart();
            ;// 如果是上面那个大的按钮，就调这个方法
            return;
        }

        for (int i = 0; i < ROW; i++) { // 有400个btns，所以用for循环去寻找想要的那个btns
            for (int j = 0; j < COL; j++) {
                if (btn.equals(btns[i][j])) {// 如果是我们想找的那个btns
                    if (data[i][j] == LEICODE) {// 如果你点到了一个雷
                        lose(); // 那你就输掉了
                    } else {// 否则再去 openCell(i, j);
                        openCell(i, j);
                        checkWin();
                    }
                    return; // 到这儿已经找到想要的btns了，所以return（好像不加也没问题）
                }
            }
        }
    }

    private void checkWin() {
        timer.stop(); // 如果赢了，把时钟停掉
        int count = 0; // 数一下现在还有几个按钮没有开
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (btns[i][j].isEnabled()) {
                    count++;
                }
            }
        }

        if (count == LEICOUNT) {// 如果没有开的个数等于雷的个数，即赢了的状态
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    if (btns[i][j].isEnabled()) { // 给没开的按钮（即没有雷的地方），自动插上一个旗子的图片
                        btns[i][j].setIcon(win_flag);
                    }
                }
            }
            bannerBtn.setIcon(win); // 上方的框出现你赢了的这张图片
            JOptionPane.showMessageDialog(frame, "点上面的Banner重新开始", "哦，牛批", JOptionPane.PLAIN_MESSAGE);
        }

    }

    private void lose() {
        timer.stop(); // 如果输了，把时钟停掉
        bannerBtn.setIcon(fail); // 输的时候变成fail这张图
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (btns[i][j].isEnabled()) {// 如果这个按钮还没有打开
                    JButton btn = btns[i][j];
                    if (data[i][j] == LEICODE) {
                        btn.setEnabled(false);// 是否能响应你的操作，而现在已经点开了这个按钮，所以不用再去响应了，所以false
                        btn.setIcon(bomb);
                        btn.setDisabledIcon(bomb);
                    } else {
                        btn.setIcon(null); // 先把上面的图片去掉
                        btn.setEnabled(false); // 是否能响应你的操作，而现在已经点开了这个按钮，所以不用再去响应了，所以false
                        btn.setOpaque(true); // 设置为不透明的。这样，可以显示出绿色
                        btn.setText(data[i][j] + "");

                    }
                }
            }
        }
        JOptionPane.showMessageDialog(frame, "点上面的Banner重新开始", "好死", JOptionPane.PLAIN_MESSAGE); // 第一个参数是出现在哪儿框上面，这儿填它的父亲frame，第二个参数是框的内容，第三个参数是框上面的提示，第四个参数是设定这个消息类型是一个普通消息，这样就不会出来那个警告的图标

    }

    private void openCell(int i, int j) {
        JButton btn = btns[i][j];
        if (!btn.isEnabled()) {// 如果它已经被打开过了，则return出递归（这个if用于退出递归）
            return;
        }

        btn.setIcon(null); // 先把上面的图片去掉
        btn.setEnabled(false); // 是否能响应你的操作，而现在已经点开了这个按钮，所以不用再去响应了，所以false
        btn.setOpaque(true); // 设置为不透明的。这样，可以显示出绿色
        btn.setBackground(Color.GREEN);
        btn.setText(data[i][j] + "");
        addOpenCount(); //

        /* 下面开始递归 */
        if (data[i][j] == 0) {
            /* 下面8个if的条件的的前半部分是判是否越界 ,顺便递归 */
            if (i > 0 && j > 0 && data[i - 1][j - 1] == 0) {
                openCell(i - 1, j - 1);
            }
            if (i > 0 && data[i - 1][j] == 0) {
                openCell(i - 1, j);
            }
            if (i > 0 && j < 19 && data[i - 1][j + 1] == 0) {
                openCell(i - 1, j + 1);
            }
            if (j > 0 && data[i][j - 1] == 0) {
                openCell(i, j - 1);
            }
            if (j < 19 && data[i][j + 1] == 0) {
                openCell(i, j + 1);
            }
            if (i < 19 && j > 0 && data[i + 1][j - 1] == 0) {
                openCell(i + 1, j - 1);
            }
            if (i < 19 && data[i + 1][j] == 0) {
                openCell(i + 1, j);
            }
            if (i < 19 && j < 19 && data[i + 1][j + 1] == 0) {
                openCell(i + 1, j + 1);
            }
        }
    }

    private void addOpenCount() {
        opened++;
        unopened--;
        label1.setText("还剩的雷" + unopened);
        label2.setText("已扫的雷" + opened);
    }

    private void restart() {// 要干三件事情：1.给数据清零；2.给按钮恢复状态；3.重新启动时钟

        /* 恢复数据和按钮 */
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                data[i][j] = 0;
                btns[i][j].setBackground(new Color(244, 183, 113));
                btns[i][j].setEnabled(true);
                btns[i][j].setText(""); // 将文本清掉
                btns[i][j].setIcon(guess);

            }
        }

        /* 恢复状态栏 */
        unopened = ROW * COL;
        opened = 0;
        seconds = 0;
        label1.setText("还剩的雷" + unopened);
        label2.setText("已扫的雷" + opened);
        label3.setText("用时" + seconds + "s");
        bannerBtn.setIcon(banner);

        addLei();
        timer.start();

    }
}

