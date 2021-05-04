import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamReaderzzz {
    public static void main(String[] args) throws IOException {

        // 这个news.txt的编码不是默认的UTF-8了。而是ANSI，所以如果不转换的话，读取时会是个乱码
        String filePath = "file/InputStreamReader/news.txt";

        // 把FileInputStream转换成InputStreamReader并指定编码为gbk
        InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), "gbk");

        // 用BufferedReader去读取比较高效些
        BufferedReader br = new BufferedReader(isr);

        // 上面这两行也可写到一起：
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "gbk"));

        String s = br.readLine();
        System.out.println(s);

        br.close();// 关闭流
    }
}
