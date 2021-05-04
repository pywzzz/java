import java.util.Scanner;

//标准输入流System.in的父类、编译类型是InputStream，运行类型是BufferedInputStream，默认设备是键盘
//Scanner就是从System.in这个流上获得从键盘上输入的信息

//标准输出流System.out的父类、编译类型是PrintStream，运行类型也是PrintStream，默认设备是显示器
//平常用的：System.out.println("xxx"); 就是使用out对象将数据输出到显示器上

public class SystemXX {
    public static void main(String[] args) {
        System.out.println("test");

        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        System.out.println("test：" + next);
    }

}
