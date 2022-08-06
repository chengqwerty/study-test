package som.make.origin.jdk8;

import org.junit.jupiter.api.Test;

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
