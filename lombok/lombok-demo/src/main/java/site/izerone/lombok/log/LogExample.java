package site.izerone.lombok.log;

import lombok.extern.java.Log;

/**
 * Lombok Log 注解
 *
 * 自动生成日志工具类
 *
 * @author izerone
 * @version 1.0
 * @date 2019/6/1 18:22
 */
@Log
public class LogExample {

    public static void main(String[] args) {
        log.info("hello lombok log");
    }
}
