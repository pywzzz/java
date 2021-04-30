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
    ImageIcon banner = new ImageIcon("banner.png"); // ���һ����ť
    ImageIcon bomb = new ImageIcon("bomb.png");
    ImageIcon fail = new ImageIcon("fail.png");
    ImageIcon guess = new ImageIcon("guess.png");
    ImageIcon win = new ImageIcon("win.png");
    ImageIcon win_flag = new ImageIcon("win_flag.png");
    JButton bannerBtn = new JButton(banner); // ��ʾbanner����ͼƬ

    /* ���������ݽṹ */
    int ROW = 20;
    int COL = 20;
    int[][] data = new int[ROW][COL]; // ÿ���׶�Ӧ��һ�����ݣ����Զ���������������������Щ���ݣ�������׵Ļ����ͷ�-1�������׵Ļ��ͷ�0~8��������Χ���׵ĸ�����
    JButton[][] btns = new JButton[ROW][COL];
    int LEICOUNT = 15; // һ���ж�����
    int LEICODE = -1; // -1�����������
    int unopened = ROW * COL; // û��ɨ������
    int opened = 0; // ɨ�����ף�Ĭ��Ϊ0��
    int seconds = 0;// ���ʱ������ʱ

    /* ��NORTH���֣���ϸ��һ�£���ԭ�еĻ��������·���������״̬�� */
    JLabel label1 = new JLabel("��ʣ����" + unopened);
    JLabel label2 = new JLabel("��ɨ����" + opened);
    JLabel label3 = new JLabel("��ʱ" + seconds + "s");
    Timer timer = new Timer(1000, this); // ��һ��������ʱ�Ӷ���ʱ�䴥��һ�Σ�1000ms�����ڶ��ǲ����Ǵ�������˭

    public Saolei() { // ���캯��
        frame.setSize(600, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());// �����һ�ֲ���
        setHeader();
        addLei();// ��ÿ�����Ӻ���Ӷ�Ӧ������������û����ɶ��
        setButtons();
        timer.start();
        frame.setVisible(true);

    }

    private void addLei() {
        /* �����������λ������LEICOUNT���� */
        Random rand = new Random();
        for (int i = 0; i < LEICOUNT;) {
            int r = rand.nextInt(ROW);
            int c = rand.nextInt(COL);
            if (data[r][c] != LEICODE) {
                data[r][c] = LEICODE;
                i++;
            }
        }

        /* ����һ��ʹ��400�����Ӷ�Ӧ�����֣���LEICODE����-1������Ϊ0 */
        /* ����Ҫ����������ࡱ���0~8����ʾ����Χ�м����� */
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (data[i][j] == LEICODE) { // ���������׵Ļ���continue
                    continue;
                }
                int tempCount = 0;

                /* ����8��if�������ĵ�ǰ�벿�������Ƿ�Խ�� */
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
        Container con = new Container(); // ��Ҫһ����������Щ��ť����ȥ
        con.setLayout(new GridLayout(ROW, COL));// ��Container����һ������
        for (int i = 0; i < ROW; i++) { // ������forѭ������Щ��ť����������
            for (int j = 0; j < COL; j++) {

                // JButton btn = new JButton(data[i][j] + ""); // + ""����Ϊ���Ҫ����ַ����Ķ���

                JButton btn = new JButton(guess);

                /* ���ø�����ɫ */
                btn.setOpaque(true);
                btn.setBackground(new Color(244, 183, 113));

                btn.setMargin(new Insets(0, 0, 0, 0)); // ��ʾ...������ʾ��������Ϊ����̫С�����ַŲ���,�������֮��Ϳ��Բ���ʾ...����ʾ������
                btn.addActionListener(this); // ����btn��ť�����Ҫʵ��ActionListener�ӿ�
                con.add(btn); // ��btn���ص�con����
                btns[i][j] = btn; // �ŵ������㶨���btns�����У�֮��Ҫ�ã�������͵�ģ�
            }

        }

        frame.add(con, BorderLayout.CENTER); // ��con���ص�frame�У�����BorderLayout��CENTERλ�ã�
    }

    private void setHeader() {
        JPanel panel = new JPanel(new GridBagLayout()); // ��������bannerIcon������label�ˣ�������Ҫһ��������������������װ����������ǣ������������һ�顰������Ҳ���������JPnel�����Ĳ���ΪGridBagLayout()

        /* ��������������label��һ��bannerIcon��ť���ص���������� */
        /* ���֣�Ҳ���������棬ȷʵ�ܸ��� */
        GridBagConstraints c1 = new GridBagConstraints(0, 0, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0); // ǰ����������ͼƬ���Ͻǵ����꣬�ڶ����ǿ�ȣ���������3����Ϊ�����3�����ӡ����ˣ����3����ָ3������ɶ�ģ����������Ǹ߶ȡ������漸����������Ҫ��
        panel.add(bannerBtn, c1); // ָ����bannerBtn���ص�c1����������д����Ǽ����ڵ��Ǹ��ط�Ҫ���������
        bannerBtn.addActionListener(this); // ֮ǰû����仰ʱ���������ťû�з�Ӧ����������󣬵��ˣ�����ȥ��this

        label1.setOpaque(true); // ���óɲ�͸������Ҫ��͸���Ļ���ô������ɫɶ�İ�
        label1.setBackground(Color.white); // ����ɫ���ð�ɫ
        label1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // ���ø���ɫ�ı߿�

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

        frame.add(panel, BorderLayout.NORTH); // ��������һ�Ѵ���Ū�õĻ���panel�������BorderLayout���ֵ����棬������NORTH
    }

    public static void main(String[] args) {
        new Saolei();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof Timer) { // �����Timer�����������������
            seconds++;
            label3.setText("��ʱ" + seconds + "s");
            timer.start();
            return;

        }

        JButton btn = (JButton) e.getSource(); // getSource()�ǻ�ȡe����¼������ĸ�Դ�����ķ���ֵ�����Ǳ�Ķ������������ǽ���һ��ǿ������ת��
        if (btn.equals(bannerBtn)) {// �жϳ���������Ǹ���İ�ť��������������label��Ҳ����С�İ�ť
            restart();
            ;// ����������Ǹ���İ�ť���͵��������
            return;
        }

        for (int i = 0; i < ROW; i++) { // ��400��btns��������forѭ��ȥѰ����Ҫ���Ǹ�btns
            for (int j = 0; j < COL; j++) {
                if (btn.equals(btns[i][j])) {// ������������ҵ��Ǹ�btns
                    if (data[i][j] == LEICODE) {// �����㵽��һ����
                        lose(); // ����������
                    } else {// ������ȥ openCell(i, j);
                        openCell(i, j);
                        checkWin();
                    }
                    return; // ������Ѿ��ҵ���Ҫ��btns�ˣ�����return�����񲻼�Ҳû���⣩
                }
            }
        }
    }

    private void checkWin() {
        timer.stop(); // ���Ӯ�ˣ���ʱ��ͣ��
        int count = 0; // ��һ�����ڻ��м�����ťû�п�
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (btns[i][j].isEnabled()) {
                    count++;
                }
            }
        }

        if (count == LEICOUNT) {// ���û�п��ĸ��������׵ĸ�������Ӯ�˵�״̬
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    if (btns[i][j].isEnabled()) { // ��û���İ�ť����û���׵ĵط������Զ�����һ�����ӵ�ͼƬ
                        btns[i][j].setIcon(win_flag);
                    }
                }
            }
            bannerBtn.setIcon(win); // �Ϸ��Ŀ������Ӯ�˵�����ͼƬ
            JOptionPane.showMessageDialog(frame, "�������Banner���¿�ʼ", "Ŷ��ţ��", JOptionPane.PLAIN_MESSAGE);
        }

    }

    private void lose() {
        timer.stop(); // ������ˣ���ʱ��ͣ��
        bannerBtn.setIcon(fail); // ���ʱ����fail����ͼ
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (btns[i][j].isEnabled()) {// ��������ť��û�д�
                    JButton btn = btns[i][j];
                    if (data[i][j] == LEICODE) {
                        btn.setEnabled(false);// �Ƿ�����Ӧ��Ĳ������������Ѿ��㿪�������ť�����Բ�����ȥ��Ӧ�ˣ�����false
                        btn.setIcon(bomb);
                        btn.setDisabledIcon(bomb);
                    } else {
                        btn.setIcon(null); // �Ȱ������ͼƬȥ��
                        btn.setEnabled(false); // �Ƿ�����Ӧ��Ĳ������������Ѿ��㿪�������ť�����Բ�����ȥ��Ӧ�ˣ�����false
                        btn.setOpaque(true); // ����Ϊ��͸���ġ�������������ʾ����ɫ
                        btn.setText(data[i][j] + "");

                    }
                }
            }
        }
        JOptionPane.showMessageDialog(frame, "�������Banner���¿�ʼ", "����", JOptionPane.PLAIN_MESSAGE); // ��һ�������ǳ������Ķ������棬��������ĸ���frame���ڶ��������ǿ�����ݣ������������ǿ��������ʾ�����ĸ��������趨�����Ϣ������һ����ͨ��Ϣ�������Ͳ�������Ǹ������ͼ��

    }

    private void openCell(int i, int j) {
        JButton btn = btns[i][j];
        if (!btn.isEnabled()) {// ������Ѿ����򿪹��ˣ���return���ݹ飨���if�����˳��ݹ飩
            return;
        }

        btn.setIcon(null); // �Ȱ������ͼƬȥ��
        btn.setEnabled(false); // �Ƿ�����Ӧ��Ĳ������������Ѿ��㿪�������ť�����Բ�����ȥ��Ӧ�ˣ�����false
        btn.setOpaque(true); // ����Ϊ��͸���ġ�������������ʾ����ɫ
        btn.setBackground(Color.GREEN);
        btn.setText(data[i][j] + "");
        addOpenCount(); //

        /* ���濪ʼ�ݹ� */
        if (data[i][j] == 0) {
            /* ����8��if�������ĵ�ǰ�벿�������Ƿ�Խ�� ,˳��ݹ� */
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
        label1.setText("��ʣ����" + unopened);
        label2.setText("��ɨ����" + opened);
    }

    private void restart() {// Ҫ���������飺1.���������㣻2.����ť�ָ�״̬��3.��������ʱ��

        /* �ָ����ݺͰ�ť */
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                data[i][j] = 0;
                btns[i][j].setBackground(new Color(244, 183, 113));
                btns[i][j].setEnabled(true);
                btns[i][j].setText(""); // ���ı����
                btns[i][j].setIcon(guess);

            }
        }

        /* �ָ�״̬�� */
        unopened = ROW * COL;
        opened = 0;
        seconds = 0;
        label1.setText("��ʣ����" + unopened);
        label2.setText("��ɨ����" + opened);
        label3.setText("��ʱ" + seconds + "s");
        bannerBtn.setIcon(banner);

        addLei();
        timer.start();

    }
}

