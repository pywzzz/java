import java.io.File;

public class Directory {
    public static void main(String[] args) {
        System.out.println("test");
    }

    // 删除一个指定的文件
    public static void m1() {
        String filePath = "file/Directory/news.txt";
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) { // 这个删除的方法会返回一个boolean值
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        } else {
            System.out.println("该文件不存在");
        }
    }

    // 删除一个指定的目录
    public static void m2() {
        String filePath = "file/Directory1";
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) { // 这个删除的方法会返回一个boolean值
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        } else {
            System.out.println("该目录不存在");
        }
    }

    // 创建文件夹
    public static void m3() {
        String directoryPath = "file/Directory";
        File file = new File(directoryPath);
        if (file.exists()) {
            System.out.println(directoryPath + " 这个文件夹已经存在了。。。");
        } else {
            if (file.mkdirs()) {// 这个是用于创建多级目录，而 mkdir() 只能创建一级目录
                System.out.println(directoryPath + " 创建成功。。。");
            } else {
                System.out.println(directoryPath + " 创建失败。。。");
            }
        }
    }

}
