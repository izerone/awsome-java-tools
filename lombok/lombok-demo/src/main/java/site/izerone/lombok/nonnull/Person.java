package site.izerone.lombok.nonnull;

import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * Lombok NonNull 注解
 *
 * 进行参数或字段非 null 校验
 *
 * @author izerone
 * @version 1.0
 * @date 2019/6/1 17:57
 */
@AllArgsConstructor
public class Person {

    private String name;
    private String age;
    @NonNull
    private String sex;

    public Person(String name){
        this.name = name;
    }

    public Person getPerson(@NonNull String name) {
        return new Person(name);
    }
}
