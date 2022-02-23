package som.make.stream;

/**
 * jdk8 允许接口中定义默认方法
 */
public interface Formula {

    default double sqrt(int a) {
        return Math.sqrt(a);
    }

    default int add(int a, int b) {
        return a + b;
    }

    double calculate(int a);

}
