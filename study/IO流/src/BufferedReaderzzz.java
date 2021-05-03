import java.io.BufferedReader;

import java.io.FileReader;

//BufferedReader是按字符处理的，如果用它处理二进制文件（即字节文件）的话，可能会出现文件的损坏
public class BufferedReaderzzz {
    public static void main(String[] args) throws Exception {
        String filePath = "file/BufferedReader/news.txt";

        // BufferedReader实际上是一个包装类。在这儿执行还是FileReader这个节点流
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String line;

        // 按行读取（类比之前的那个按数组读取）（这个就体现出来BufferedReader这个包装类的用途了，它为FileReader类附加上了些新功能）
        // 这个方法读完之后，会return一个null
        line = bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        // 这儿关闭BufferedReader就等于关闭节点流FileReader（因为ufferedReader的源码中会关闭节点流）
        bufferedReader.close();

    }
}
