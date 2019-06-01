package site.izerone.lombok.cleanup;

import lombok.Cleanup;

import java.io.*;

/**
 * Lombok Cleanup 注解
 *
 * 确保在代码执行路径退出当前作用域之前自动清除给定资源
 *
 * @author izerone
 * @version 1.0
 * @date 2019/6/1 18:06
 */
public class CleanupExample {

    public static void main(String[] args) throws IOException {
        @Cleanup InputStream in = new FileInputStream(args[0]);
        @Cleanup OutputStream out = new FileOutputStream(args[1]);
        byte[] b = new byte[10000];
        while (true) {
            int r = in.read(b);
            if (r == -1) {
                break;
            }
            out.write(b, 0, r);
        }
    }
}
