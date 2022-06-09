package som.make.inner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class OrangeTree {

    private final String treeCode;
    private final int age;
    private final List<Orange> orangeList = new ArrayList<>();

    public OrangeTree(String treeCode, int age) {
        this.treeCode = treeCode;
        this.age = age;
    }

    public void grow(int num) {
        if (num < 1) return;
        IntStream.range(0, num).forEach((n) -> {
            orangeList.add(new Orange(LocalDateTime.now()));
        });
    }

    public List<Orange> getOrangeList() {
        return this.orangeList;
    }

    final class Orange {

        private LocalDateTime growTime;

        public Orange(LocalDateTime growTime) {
            this.growTime = growTime;
        }

        public void treeInfo() {
            System.out.println("Tree Code is " + OrangeTree.this.treeCode + ",Tree Age is " + OrangeTree.this.age);
        }

    }

}
