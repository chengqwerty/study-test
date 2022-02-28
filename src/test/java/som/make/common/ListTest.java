package som.make.common;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListTest {

    /**
     * Arrays.asList 返回的是一个自己内部的ArrayList
     * 它不是我们通用的ArrayList,是一个只读的list，
     * 没有add，remove方法，使用这些方法会抛出异常
     */
    @Test
    public void test1() {
        String[] os = new String[] {"1", "2", "3", "4", "5"};
        List<String> ss = Arrays.asList(os);
        System.out.println(ss);
    }

    /**
     * Arrays.asList 不能把基本数据类型转化为列表
     * 仔细观察可以发现asList接受的参数是一个泛型的变长参数，而基本数据类型是无法泛型化的
     * 它会把int类型的数组当参数了，所以转换后的列表就只包含一个int[]元素。
     */
    @Test
    public void test2() {
        int[] is = new int[] {1, 2, 3, 4, 5};
        List<int[]> ints = Arrays.asList(is);
        System.out.println(ints);
    }

    // jdk8可以使用stream把数组转换为list
    @Test
    public void test3() {
        String[] os = new String[] {"1", "2", "3", "4", "5"};
        List<String> ss = Stream.of(os).collect(Collectors.toList());
        System.out.println(ss);
    }

}
