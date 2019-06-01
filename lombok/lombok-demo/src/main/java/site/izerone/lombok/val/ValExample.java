package site.izerone.lombok.val;

import lombok.val;

import java.util.ArrayList;

/**
 * Lombok val 和 var
 *
 * val 不可变变量域
 *
 * var 变量域
 *
 * @author izerone
 * @version 1.0
 * @date 2019/6/1 18:28
 */
public class ValExample {

    public static void main(String[] args) {
        val example = new ArrayList<String>();
        example.add("Hello, World!");
        val foo = example.get(0);
        System.out.println(foo.toLowerCase());
    }
}
