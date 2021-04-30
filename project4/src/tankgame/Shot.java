package tankgame;

public class Shot implements Runnable {
    int x; // �ӵ���x����
    int y; // �ӵ���y����
    int direct = 0; // �ӵ��ķ���
    int speed = 5;// �ӵ����ٶ�
    boolean isLive = true; // �ӵ��Ƿ���

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            switch (direct) {
            case 0:
                y -= speed;
                break;
            case 1:
                x += speed;
                break;
            case 2:
                y += speed;
                break;
            case 3:
                x -= speed;
                break;

            }

            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {// ������Χȡ�����ǲ������ķ�Χ���������˱߽�

                // �ӵ��ڻ��е��˺�ҲӦ���˳����������������if�м��˸�isLive��ʵ������Ч��
                // ���isLive�ǽ�����Ǹ�hitTank����������

                isLive = false; // �ӵ�����
                break; // �����˱߽��break�˳�
            }
        }
    }
}
