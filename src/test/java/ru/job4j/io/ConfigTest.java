package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithCommentAndEmptyLines() {
        String path = "./data/pair_with_comment_and_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenLineNotKey() {
        String path = "./data/when_not_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void whenLineNotValue() {
        String path = "./data/when_not_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a value");
    }

    @Test
    void whenLineNotEqualSign() {
        String path = "./data/when_not_equal_sign.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain the equal sing");
    }

    @Test
    void whenLineOnlyEqualSign() {
        String path = "./data/when_only_equal_sign.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key and value");
    }

    @Test
    void whenValueHasEqualSign() {
        String path = "./data/when_value_has_equal_sign.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev=");
        assertThat(config.value("name1")).isEqualTo("Petr Arsentev=123");
    }
}