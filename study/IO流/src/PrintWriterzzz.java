import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterzzz {
    public static void main(String[] args) throws IOException {

        // 下面是输出到屏幕
        PrintWriter printWriter1 = new PrintWriter(System.out);
        printWriter1.print("你ma死了");
        printWriter1.close();

        // 下面是输出到文件
        PrintWriter printWriter2 = new PrintWriter(new FileWriter("file/PrintWriter/news.txt"));
        printWriter2.print("你钱夹都死了");
        printWriter2.close();
    }
}
