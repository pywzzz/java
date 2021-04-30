package tankgame;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

//用于记录相关数据
public class Recorder {
    private static int allEnemyTankNum = 0; // 击毁地方坦克的数量

    private static BufferedWriter bw = null; // 定义IO对象，用于将数据写入到文件当中
    private static BufferedReader br = null;// read这东西，从而达到恢复的效果

    private static String recordFile = "src/resource/myRecord.txt";

    private static Vector<EnemyTank> enemyTanks = null;// 和setEnemyTanks方法配合

    private static Vector<Node> nodes = new Vector<>();// 定义一个Node的Vector，用于保存敌人坦克的信息Node

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) { // 为了得到MyPanel中敌人坦克的信息
        Recorder.enemyTanks = enemyTanks;
    }

    public static String getRecordFile() { //返回你那个txt文件的路径
        return recordFile;
    }

    // 下面方法用于读取recordFile，恢复相关信息
    public static Vector<Node> getNodesAndEnemyTankRec() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());// 读取第一行，获得击毁地方坦克的数量。这个读取出来是个字符串，所以用Integer转换一下

            // 下面来循环读取文件，生成nodes集合
            String line = "";
            while ((line = br.readLine()) != null) { // 当读取的东西不为空（那就一直读取）
                String[] xyd = line.split(" "); // 把x、y、direct存到这个xyd这个字符型数组中（以空格为split的标准）
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2])); // 构造方法
                nodes.add(node); // 放到Vector数组中
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();  //用完记得关
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return nodes; // 返回nodes
    }

    // 这个方法用于实现当游戏退出时，将addAllEnemyTankNum的数保存到recordFile
    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum + "\r\n");

            // 下面用于记录剩余的敌方坦克的信息
            for (int i = 0; i < enemyTanks.size(); i++) {// 遍历敌方坦克
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive) { // 最好判断一下，这样更加严谨
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect();
                    bw.write(record + "\r\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void addAllEnemyTankNum() { // 击毁一辆就加1
        Recorder.allEnemyTankNum++;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }
}
