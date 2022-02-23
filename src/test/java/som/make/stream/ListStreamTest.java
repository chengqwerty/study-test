package som.make.stream;

import org.junit.jupiter.api.Test;

import java.util.*;

public class ListStreamTest {

    /**
     * 原始的排序写法,需要手写一个匿名类
     */
    @Test
    public void test1() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        names.sort(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        System.out.println(names);
    }

    /**
     * 当接口只有一个抽象方法时可以使用Lambda表达式，不用手写匿名类了
     * 只要时方法就可以，而且方法参数类型也可以省略
     */
    @Test
    public void test2() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        names.sort((a, b) -> b.compareTo(a));
        System.out.println(names);
    }

}
