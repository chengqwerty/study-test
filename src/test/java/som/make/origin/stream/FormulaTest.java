package som.make.origin.stream;

import org.junit.jupiter.api.Test;
import som.make.origin.stream.Formula;

public class FormulaTest {

    /**
     * 测试jdk默认方法
     */
    @Test
    public void test1() {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return a * 100;
            }
        };
        System.out.println(formula.calculate(100));
        System.out.println(formula.add(100, 50));
    }

}
