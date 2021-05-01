import java.io.File;

public class FileInformation {
    public static void main(String[] args) {
        info();
    }

    public static void info() {
        File file = new File("file/FileInformation/news.txt"); // 线创建一个对象
        System.out.println(file.getName()); // 获取文件名
        System.out.println(file.getAbsolutePath()); // 获取绝对路径
        System.out.println(file.getParent()); // 获得父级目录
        System.out.println(file.length()); // 获得文件大小，以字节为单位（UTF-8编码下，一个汉字3个字节，一个字母1个字节）
        System.out.println(file.exists()); // 是否存在
        System.out.println(file.isFile());// 是否为文件
        System.out.println(file.isDirectory()); // 是否为文件夹

    }

}
