
//gbk��utf8������д��utf-8����
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OutputStreamReaderzzz {
    public static void main(String[] args) throws IOException {
        String filePath = "file/OutputStreamReader/news.txt";
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath), "gbk");
        osw.write("�����������");
        osw.close();

    }
}
