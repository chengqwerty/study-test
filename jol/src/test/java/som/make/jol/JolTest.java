package som.make.jol;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

public class JolTest {

    public static class Mock {

        public static String a = "000";

    }

    @Test
    public void test1() {
        Object obj = new Object();
        Mock mock = new Mock();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        System.out.println(ClassLayout.parseInstance(mock).toPrintable());
        System.out.println(ClassLayout.parseClass(Object.class).toPrintable());
        System.out.println(ClassLayout.parseClass(mock.getClass()).toPrintable());
        System.out.println(ClassLayout.parseClass(Class.class).toPrintable(mock.getClass()));
    }

}
