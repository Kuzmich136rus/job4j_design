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
    void whenHasCommentsAndEmptyString() {
        String path = "./data/with_comments_and_empty_string.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("user")).isEqualTo("Nikita Shcherbakov");
    }

    @Test
    void whenIllegalValues() {
        String path = "./data/with_illegal_values.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }


}