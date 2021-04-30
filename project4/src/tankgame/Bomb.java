package tankgame;

public class Bomb {
    int x;
    int y;
    int life = 9; // 炸弹的生命值
    boolean isLive = true; // 炸弹是否存活

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void lifeDown() { //减少生命值的方法,这个是为了配合让炸弹出现爆炸的效果
        if (life > 0) {
            life--;  //这儿的“减减”让动画看起来更连贯，可以理解为一帧一帧的播放云
        } else {
            isLive = false;
        }
    }

}
