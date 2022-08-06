package som.make.origin.base;

import org.junit.jupiter.api.Test;

/**
 * 测试java是值传递还是引用传递
 */
public class JavaParamTest {

    @Test
    public void test1() {
        String a = new String("aa");
        fun1(a);
        System.out.println(a);
    }

    private void fun1(String a) {
        System.out.println(a);
        a = a + "bb";
        System.out.println(a);
    }

    @Test
    public void test2() {
        PersonValue pv = new PersonValue(75);
        pvProcess(pv);
        System.out.println(pv.value);
    }

    private void pvProcess(PersonValue pv) {
        pv.value = pv.value + 15;
        pv = new PersonValue(36);
        System.out.println(pv.value);
    }

}
