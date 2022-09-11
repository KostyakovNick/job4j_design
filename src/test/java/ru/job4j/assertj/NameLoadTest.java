package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkParseIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkParseNotEqualSing() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: 1 does not contain the symbol \"=\"");
    }

    @Test
    void checkParseNotKey() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("= 1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: = 1 does not contain a key");
    }

    @Test
    void checkParseNotValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("1 ="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: 1 = does not contain a value");
    }
}