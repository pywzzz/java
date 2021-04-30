package ���߳�;

/* 
*�߳�ͬ�����ƣ�
*������һ���߳��ڶ��ڴ棨Ҳ�����ݣ����в���ʱ�������̶߳������Զ�����ڴ��ַ���в�����ֱ�����߳���ɲ��ޣ������̲߳ſ��Զ�����ڴ��ַ���в���

*ͬ�������ʵ�ַ���������һ�־��Ǽӻ�����synchronized
*���������ȱ����ǻᵼ�´����ִ��Ч�ʱ��
*/

/* 
*���漸�ֲ������ͷŽ����õ����������
*1.��ǰ�̵߳�ͬ��������ͬ�������ִ�н������ϲ��������������
*2.��ǰ�߳���ͬ��������ͬ�����������break��return���ڼ����˽���ɵ����飬û���������꣬�����ѳ�����
*3.��ǰ�̵߳�ͬ��������ͬ������������δ�����Error��Exception�������쳣�������ڼ�û��ֽ��û���������꣬�����ѳ�����
*4.��ǰ�̵߳�ͬ��������ͬ�������ִ�����̶߳����wait��������ǰ�߳���ͣ���ͷ�����������Ҫ�����£�û���������꣬�����ѳ�������һ�ỹ���ȥ��

*���漸�ֲ��������ͷ�����
*1.��ǰ�߳���ִ��ͬ��������ͬ�������ʱ�����������sleep��yield������ͣ��ǰ�̵߳�ִ�У����̲߳����ͷ������������˯�ˣ�û�г�����
*2.��ǰ�߳�ִ��ͬ�������ʱ�������̵߳����˸��̵߳�suspend���������̹߳��𣬸��̲߳����ͷ���
*���ˣ�suspend��resume��������������������������������
 */

public class SellTicket {
    public static void main(String[] args) {

        // �����ַ����������Ʊ��Ϊ���������
        // ��������״����ԭ����ǣ�3���߳�ͬʱȥif���������������ʣ1�ŵĻ���������������ͬʱȥ��sell�����е�if����ʱ�򻹶�ͨ����if�����Ը�����

        /* �ü̳еķ�ʽ */
        // SellTicket01 sellTicket01 = new SellTicket01();
        // SellTicket01 sellTicket02 = new SellTicket01();
        // SellTicket01 sellTicket03 = new SellTicket01();
        // sellTicket01.start();
        // sellTicket02.start();
        // sellTicket03.start();

        /* �ýӿڵķ�ʽ */
        // SellTicket02 sellTicket02 = new SellTicket02();
        // new Thread(sellTicket02).start();
        // new Thread(sellTicket02).start();
        // new Thread(sellTicket02).start();

        /* ��ͬ���ķ�ʽ */
        SellTicket03 sellTicket03 = new SellTicket03();
        new Thread(sellTicket03).start();
        new Thread(sellTicket03).start();
        new Thread(sellTicket03).start();
    }
}

class SellTicket01 extends Thread {
    private static int ticketNum = 100; // һ��100��Ʊ���Ӹ�static�Ե��ﹲ���Ч����

    @Override
    public void run() {
        while (true) {
            if (ticketNum <= 0) {
                System.out.println("��Ʊ����");
                break;
            }
            try {
                Thread.sleep(50); // Ҫ��û����Ʊ����ÿ��50ms��һ��
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("����" + Thread.currentThread().getName() + "����һ��Ʊ" + "  ʣ��Ʊ��=" + (--ticketNum));
        }
    }
}

class SellTicket02 implements Runnable {
    private int ticketNum = 100; // һ��100��Ʊ������Ͳ���ȥstatic�ˣ���Ϊ�ӿ����ַ�������ȥ�����̣߳�

    @Override
    public void run() {
        while (true) {
            if (ticketNum <= 0) {
                System.out.println("��Ʊ����");
                break;
            }
            try {
                Thread.sleep(50); // Ҫ��û����Ʊ����ÿ��50ms��һ��
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("����" + Thread.currentThread().getName() + "����һ��Ʊ" + "  ʣ��Ʊ��=" + (--ticketNum));
        }
    }
}

// �ڷ����ϼӻ�����
class SellTicket03 implements Runnable {
    private int ticketNum = 100; // һ��100��Ʊ������Ͳ���ȥstatic�ˣ���Ϊ�ӿ����ַ�������ȥ�����̣߳�
    private boolean loop = true;

    public synchronized void sell() {// ����������ӻ���������ʱ����ͬһʱ�̣�ֻ����һ���߳���ִ��sell����
        if (ticketNum <= 0) {
            System.out.println("��Ʊ����");
            loop = false;
            return;
        }
        try {
            Thread.sleep(50); // Ҫ��û����Ʊ����ÿ��50ms��һ��
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("����" + Thread.currentThread().getName() + "����һ��Ʊ" + "  ʣ��Ʊ��=" + (--ticketNum));
    }

    @Override
    public void run() {
        while (loop) {
            sell(); // sell������һ��ͬ������
        }
    }
}

// �ڴ�����ϼӻ�����
class SellTicket04 implements Runnable {
    private int ticketNum = 100; // һ��100��Ʊ������Ͳ���ȥstatic�ˣ���Ϊ�ӿ����ַ�������ȥ�����̣߳�
    private boolean loop = true;

    public void sell() {

        synchronized (this) {// �����sell��һ��ʵ�����������������this��Ҳ�������������󣨵���Ҫ�����̵߳�������Ϊͬһ����
            // �ͣ��������������ͬһ���Ļ����Ͳ��ܴﵽһ���߳�ȥ��ͬһ����������Ч����
            // ��������this�ĳ�new Object() �Ļ������ֳ���Ʊ��Ϊ���������
            /*
             * �������Ҳ�ɰ�this��һ�£� Object object = new Object(); synchronized (object) {...}
             */
            if (ticketNum <= 0) {
                System.out.println("��Ʊ����");
                loop = false;
                return;
            }
            try {
                Thread.sleep(50); // Ҫ��û����Ʊ����ÿ��50ms��һ��
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("����" + Thread.currentThread().getName() + "����һ��Ʊ" + "  ʣ��Ʊ��=" + (--ticketNum));
        }

    }

    /*
     * ����������෽���Ļ����ڷ����ϼӻ�������ʵ��������ʽһ����public synchronized static void mm(){...}
     * ����������ش�����еģ��������治�ܼ�this�ˣ���ֻ���ǵ�ǰ�౾�� ����synchronized (SellTicket04.class) {...}
     */

    @Override
    public void run() {
        while (loop) {
            sell();
        }
    }
}
