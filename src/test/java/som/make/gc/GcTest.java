package som.make.gc;

import org.junit.jupiter.api.Test;

public class GcTest {

    @Test
    public void test() {
        Person p1 = new Person("chengcheng");
        Person p2 = p1;
        p1 = null;
        System.out.println(p1);
        System.out.println(p2);
    }

}
