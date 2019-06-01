## Java 开发利器之 Lombok

<!-- toc -->

- [一、Lombok 是什么](#Lombok)
- [二、Lombok 安装](#Lombok-1)
  * [2.1 Mavan 引入](#21-Mavan)
  * [2.2 Intellij IDEA Lombok 插件安装](#22-Intellij-IDEA-Lombok)
- [三、Lombok 常用注解使用](#Lombok-2)
  * [3.1 @Getter / @Setter](#31-Getter-Setter)
  * [3.2 @ToString](#32-ToString)
  * [3.3 @EqualsAndHashCode](#33-EqualsAndHashCode)
  * [3.4 @NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor](#34-NoArgsConstructor-RequiredArgsConstructor-and-AllArgsConstructor)
    + [@NoArgsConstructor](#NoArgsConstructor)
    + [@RequiredArgsConstructor](#RequiredArgsConstructor)
    + [@AllArgsConstructor](#AllArgsConstructor)
  * [3.5 @Data](#35-Data)
  * [3.6 @NonNull](#36-NonNull)
  * [3.7 @Cleanup](#37-Cleanup)
  * [3.8 @Log  等](#38-Log)
- [四、其他](#)

<!-- tocstop -->

### 一、Lombok 是什么

> Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.


[官网地址](https://projectlombok.org/)


### 二、Lombok 安装

Lombok 支持多种环境安装，此处主要介绍 **Maven** 安装和 **Intellij IDEA**。

#### 2.1 Mavan 引入

```xml
<dependencies>
	<dependency>
		<groupId>org.projectlombok</groupId>
		<artifactId>lombok</artifactId>
		<version>1.18.8</version>
		<scope>provided</scope>
	</dependency>
</dependencies>
```

#### 2.2 Intellij IDEA Lombok 插件安装

IDE 安装了 Lombok 插件，才能支持 Lombok。

安装步骤：

- 切换菜单 File > Settings > Plugins
- 点击 Browse repositories
- 搜索 Lombok 插件，点击安装
- 重启 IDE

[更多安装操作见 https://projectlombok.org/setup/overview ](https://projectlombok.org/setup/overview)


### 三、Lombok 常用注解使用

本部分内容对经常使用的 Lombok 注解进行简单介绍。

#### 3.1 @Getter / @Setter

@Getter / @Setter 注解自动生成 get / set 方法

源代码：


```java
@Setter
@Getter
public class Person {

    private String name;
    private String age;
}

```

编译后代码：


```java
public class Person {
    private String name;
    private String age;

    public Person() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public String getAge() {
        return this.age;
    }
}
```

#### 3.2 @ToString

@ToString 自动生成 toString 方法。

源代码：

```java
@ToString
public class Person {

    private String name;
    private String age;
}

```


编译后代码：

```java
public class Person {
    private String name;
    private String age;

    public Person() {
    }

    public String toString() {
        return "Person(name=" + this.name + ", age=" + this.age + ")";
    }
}

```

#### 3.3 @EqualsAndHashCode

@EqualsAndHashCode 自动生成 equals 和 hashCode 方法。

源代码：

```java
@EqualsAndHashCode
public class Person {

    private String name;
    private String age;
}

```

编译后代码：

```java
public class Person {
    private String name;
    private String age;

    public Person() {
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Person)) {
            return false;
        } else {
            Person other = (Person)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$name = this.name;
                Object other$name = other.name;
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                Object this$age = this.age;
                Object other$age = other.age;
                if (this$age == null) {
                    if (other$age != null) {
                        return false;
                    }
                } else if (!this$age.equals(other$age)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Person;
    }

    public int hashCode() {
        int PRIME = true;
        int result = 1;
        Object $name = this.name;
        int result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $age = this.age;
        result = result * 59 + ($age == null ? 43 : $age.hashCode());
        return result;
    }
}
```

#### 3.4 @NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor

##### @NoArgsConstructor

@NoArgsConstructor 自动生成无参构造函数

源代码：

```java
@NoArgsConstructor
public class Person {

    private String name;
    private String age;
    private String sex;
}

```

编译后代码：

```java
public class Person {
    private String name;
    private String age;
    private String sex;

    public Person() {
    }
}
```

##### @RequiredArgsConstructor

@RequiredArgsConstructor 自动生成必需的参数构造器，如 final, @NonNull 修饰的域。 如果没有被特殊修饰的域，则生成无参构造器。

源代码1：


```java

@RequiredArgsConstructor
public class Person {

    private String name;
    private String age;
    private String sex;
}


```

编译后代码1：


```java
public class Person {
    private String name;
    private String age;
    private String sex;

    public Person() {
    }
}

```

源代码2：


```java
@RequiredArgsConstructor
public class Person {


    private final String name;
    @NonNull
    private String age;
    private String sex;
}

```


编译后代码2：

```java
public class Person {
    private final String name;
    @NonNull
    private String age;
    private String sex;

    public Person(String name, @NonNull String age) {
        if (age == null) {
            throw new NullPointerException("age is marked non-null but is null");
        } else {
            this.name = name;
            this.age = age;
        }
    }
}

```

#####  @AllArgsConstructor

@AllArgsConstructor: 自动生成包含所有域的构造函数。

源代码：


```java
@AllArgsConstructor
public class Person {

    private final String name;
    private String age;
    private String sex;
}
```


编译后代码：


```java
public class Person {
    private final String name;
    private String age;
    private String sex;

    public Person(String name, String age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}
```

#### 3.5 @Data

@Data 相当于以下注解集合


```
@ToString
@EqualsAndHashCode
@Getter
@Setter (非 final 字段)
@RequiredArgsConstructor
```

源代码：


```java
@Data
public class Person {

    private final String name;
    private String age;
    private String sex;
}

```

编译后代码：


```
public class Person {
    private final String name;
    private String age;
    private String sex;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getAge() {
        return this.age;
    }

    public String getSex() {
        return this.sex;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Person)) {
            return false;
        } else {
            Person other = (Person)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$name = this.getName();
                    Object other$name = other.getName();
                    if (this$name == null) {
                        if (other$name == null) {
                            break label47;
                        }
                    } else if (this$name.equals(other$name)) {
                        break label47;
                    }

                    return false;
                }

                Object this$age = this.getAge();
                Object other$age = other.getAge();
                if (this$age == null) {
                    if (other$age != null) {
                        return false;
                    }
                } else if (!this$age.equals(other$age)) {
                    return false;
                }

                Object this$sex = this.getSex();
                Object other$sex = other.getSex();
                if (this$sex == null) {
                    if (other$sex != null) {
                        return false;
                    }
                } else if (!this$sex.equals(other$sex)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Person;
    }

    public int hashCode() {
        int PRIME = true;
        int result = 1;
        Object $name = this.getName();
        int result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $age = this.getAge();
        result = result * 59 + ($age == null ? 43 : $age.hashCode());
        Object $sex = this.getSex();
        result = result * 59 + ($sex == null ? 43 : $sex.hashCode());
        return result;
    }

    public String toString() {
        return "Person(name=" + this.getName() + ", age=" + this.getAge() + ", sex=" + this.getSex() + ")";
    }
}
```

#### 3.6 @NonNull

@NonNull 对方法参数或者构造器参数进行非 null 检查。

源代码：


```
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
```


编译后代码：


```
public class Person {
    private String name;
    private String age;
    @NonNull
    private String sex;

    public Person(String name) {
        this.name = name;
    }

    public Person getPerson(@NonNull String name) {
        if (name == null) {
            throw new NullPointerException("name is marked non-null but is null");
        } else {
            return new Person(name);
        }
    }

    public Person(String name, String age, @NonNull String sex) {
        if (sex == null) {
            throw new NullPointerException("sex is marked non-null but is null");
        } else {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }
    }
}
```

#### 3.7 @Cleanup

@Cleanup 修饰的资源在代码执行路径退出当前作用域之前自动清除给定资源。

源代码：

```
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
```

编译后代码：


```
public class CleanupExample {
    public CleanupExample() {
    }

    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream(args[0]);

        try {
            FileOutputStream out = new FileOutputStream(args[1]);

            try {
                byte[] b = new byte[10000];

                while(true) {
                    int r = in.read(b);
                    if (r == -1) {
                        return;
                    }

                    out.write(b, 0, r);
                }
            } finally {
                if (Collections.singletonList(out).get(0) != null) {
                    out.close();
                }

            }
        } finally {
            if (Collections.singletonList(in).get(0) != null) {
                in.close();
            }

        }
    }
}
```

#### 3.8 @Log  等

@Log 自动生成日志记录工具类。不同注解对应的不同日志工具。如下：


```
@CommonsLog
private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(LogExample.class);

@Flogger
private static final com.google.common.flogger.FluentLogger log = com.google.common.flogger.FluentLogger.forEnclosingClass();

@JBossLog
private static final org.jboss.logging.Logger log = org.jboss.logging.Logger.getLogger(LogExample.class);

@Log
private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(LogExample.class.getName());

@Log4j
private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LogExample.class);

@Log4j2
private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(LogExample.class);

@Slf4j
private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LogExample.class);

@XSlf4j
private static final org.slf4j.ext.XLogger log = org.slf4j.ext.XLoggerFactory.getXLogger(LogExample.class);
```

源代码：

```java
@Log
public class LogExample {

    public static void main(String[] args) {
        log.info("hello lombok log");
    }
}
```

编译后代码：

```java
public class LogExample {
    private static final Logger log = Logger.getLogger(LogExample.class.getName());

    public LogExample() {
    }

    public static void main(String[] args) {
        log.info("hello lombok log");
    }
}

```


### 四、其他

- demo 代码地址：[lombok-demo](https://github.com/izerone/awsome-java-tools/tree/master/lombok/lombok-demo)















