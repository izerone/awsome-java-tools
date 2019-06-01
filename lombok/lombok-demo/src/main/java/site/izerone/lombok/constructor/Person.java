package site.izerone.lombok.constructor;


import lombok.AllArgsConstructor;

/**
 * Lombok NoArgsConstructor / RequiredArgsConstructor / AllArgsConstructor 注解
 *
 * 生成构造方法
 *
 *
 * @author izerone
 * @version 1.0
 * @date 2019/6/1 16:34
 */
@AllArgsConstructor
public class Person {

    private final String name;
    private String age;
    private String sex;
}
