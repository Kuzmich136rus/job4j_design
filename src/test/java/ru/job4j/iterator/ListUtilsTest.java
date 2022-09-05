package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveOdd() {
        List<Integer> checkList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        ListUtils.removeIf(checkList, e -> e % 2 != 0);
        assertThat(checkList).containsSequence(2, 4, 6, 8);
    }

    @Test
    void whenReplace() {
        List<Integer> checkList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        ListUtils.replaceIf(checkList, e -> e % 2 == 0, 7);
        assertThat(checkList).containsSequence(1, 7, 3, 7, 5, 7, 7, 7);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> checkList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        List<Integer> elements = new ArrayList<>(Arrays.asList(8, 1, 7));
        ListUtils.removeAll(checkList, elements);
        assertThat(checkList).containsSequence(2, 3, 4, 5, 6);
    }

}