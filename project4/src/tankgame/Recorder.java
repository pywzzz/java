package tankgame;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

//���ڼ�¼�������
public class Recorder {
    private static int allEnemyTankNum = 0; // ���ٵط�̹�˵�����

    private static BufferedWriter bw = null; // ����IO�������ڽ�����д�뵽�ļ�����
    private static BufferedReader br = null;// read�ⶫ�����Ӷ��ﵽ�ָ���Ч��

    private static String recordFile = "src/resource/myRecord.txt";

    private static Vector<EnemyTank> enemyTanks = null;// ��setEnemyTanks�������

    private static Vector<Node> nodes = new Vector<>();// ����һ��Node��Vector�����ڱ������̹�˵���ϢNode

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) { // Ϊ�˵õ�MyPanel�е���̹�˵���Ϣ
        Recorder.enemyTanks = enemyTanks;
    }

    public static String getRecordFile() { //�������Ǹ�txt�ļ���·��
        return recordFile;
    }

    // ���淽�����ڶ�ȡrecordFile���ָ������Ϣ
    public static Vector<Node> getNodesAndEnemyTankRec() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());// ��ȡ��һ�У���û��ٵط�̹�˵������������ȡ�����Ǹ��ַ�����������Integerת��һ��

            // ������ѭ����ȡ�ļ�������nodes����
            String line = "";
            while ((line = br.readLine()) != null) { // ����ȡ�Ķ�����Ϊ�գ��Ǿ�һֱ��ȡ��
                String[] xyd = line.split(" "); // ��x��y��direct�浽���xyd����ַ��������У��Կո�Ϊsplit�ı�׼��
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2])); // ���췽��
                nodes.add(node); // �ŵ�Vector������
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();  //����ǵù�
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return nodes; // ����nodes
    }

    // �����������ʵ�ֵ���Ϸ�˳�ʱ����addAllEnemyTankNum�������浽recordFile
    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum + "\r\n");

            // �������ڼ�¼ʣ��ĵз�̹�˵���Ϣ
            for (int i = 0; i < enemyTanks.size(); i++) {// �����з�̹��
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive) { // ����ж�һ�£����������Ͻ�
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

    public static void addAllEnemyTankNum() { // ����һ���ͼ�1
        Recorder.allEnemyTankNum++;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }
}
