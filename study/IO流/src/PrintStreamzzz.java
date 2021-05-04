import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamzzz {
    public static void main(String[] args) throws IOException {

        // 在默认情况下PrintStream输出数据的位置是标准输出（即输出到显示器上）
        PrintStream out = System.out;

        // 可以用print方法去输出
        out.print("test");

        // 因为print的源码使用的是write方法，所以我们可以直接调用write方法来进行打印（输出）
        out.write("你家里真的死人啦".getBytes());

        // 用setOut方法可以改变输出的位置
        // 这么一改之后，紧接着的这个System.out.println("哈哈哈哈哈哈");就不会输出到控制台了，而是输入到news.txt这个文件中
        System.setOut(new PrintStream("file/PrintStream/news.txt"));
        System.out.println("哈哈哈哈哈哈");

        out.close();
    }
}
