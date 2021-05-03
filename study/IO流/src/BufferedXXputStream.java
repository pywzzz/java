import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;

//���潫�ļ���1��ͼƬcopy���ļ���2
public class BufferedXXputStream {
    public static void main(String[] args) {
        String srcFilePath = "file/BufferedXXputStream/1/test.png";
        String destFilePath = "file/BufferedXXputStream/2/zzz.png";
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(srcFilePath));
            bos = new BufferedOutputStream(new FileOutputStream(destFilePath));
            byte[] buff = new byte[1024];
            int readLen = 0;

            // ѭ����ȡ�ļ�����д�뵽destFilePath��
            while ((readLen = bis.read(buff)) != -1) {
                bos.write(buff, 0, readLen);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {// ����ǵ�ȥ�ر���
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
