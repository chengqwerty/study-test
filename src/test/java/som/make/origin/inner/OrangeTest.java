package som.make.origin.inner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OrangeTest {

    @DisplayName("OrangeTree 内部类测试")
    @Test
    public void test() {
        OrangeTree orangeTree = new OrangeTree("dfkdjfdjfldkjf", 12);
        orangeTree.grow(3);
        List<OrangeTree.Orange> orangeList = orangeTree.getOrangeList();
        orangeList.forEach((OrangeTree.Orange::treeInfo));
    }

}
