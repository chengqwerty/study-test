package som.make.origin.jdk8;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalsTest {

    @Test
    public void test1() {
        Optional<String> optional = Optional.of("Lion");
        optional.ifPresent(System.out::println);
    }

}
