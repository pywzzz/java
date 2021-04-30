package tankgame;

public class Shot implements Runnable {
    int x; // 子弹的x坐标
    int y; // 子弹的y坐标
    int direct = 0; // 子弹的方向
    int speed = 5;// 子弹的速度
    boolean isLive = true; // 子弹是否存活

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

            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {// 正常范围取反就是不正常的范围，即碰到了边界

                // 子弹在击中敌人后也应当退出死亡，所以这儿的if中加了个isLive来实现这种效果
                // 这个isLive是结合你那个hitTank方法来理解的

                isLive = false; // 子弹死亡
                break; // 碰到了边界就break退出
            }
        }
    }
}
