package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("one", "three", "five", "seven", "nine");
        assertThat(list).startsWith("one")
                .hasSize(5)
                .doesNotContain("two", "four");
    }

    @Test
    void checkElementOfList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("one", "three", "five", "seven", "nine");
        assertThat(list).element(2).isNotNull();
        assertThat(list).last().isEqualTo("nine");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("Alexey", "Ivan", "Olga", "Petr");
        assertThat(set).containsAnyOf("Marya", "Stas", "Petr")
                .doesNotContain("one")
                .containsExactlyInAnyOrder("Olga", "Alexey", "Petr", "Ivan");
    }

    @Test
    void checkElementOfSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("Alexey", "Ivan", "Olga", "Petr");
        assertThat(set).last().isNotNull()
                .isEqualTo("Alexey");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("Olga", "Ivan", "Nikita");
        assertThat(map).containsKeys("Ivan", "Olga")
                .doesNotContainValue(17)
                .hasSize(3);
    }

}