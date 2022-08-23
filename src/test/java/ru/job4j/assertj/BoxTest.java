package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 4);
        String name = box.whatsThis();
        assertThat(name).doesNotContain("UNKNOWN")
                .isEqualTo("Cube");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(3, 8);
        String name = box.whatsThis();
        assertThat(name).isNotBlank()
                .contains("Unknown");
    }

    @Test
    void whenCubeVertices() {
        Box box = new Box(8, 3);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEven()
                .isEqualTo(8);
    }

    @Test
    void whenSphereVertices() {
        Box box = new Box(0, 1);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEven()
                .isZero();
    }

    @Test
    void whenCubeIsExist() {
        Box box = new Box(8, 3);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void whenSphereIsNotExist() {
        Box box = new Box(0, 0);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void whenTetrahedronArea() {
        Box box = new Box(4, 3);
        double area = box.getArea();
        assertThat(area).isLessThan(15.59d)
                .isEqualTo(15.58d, withPrecision(0.01d));
    }

    @Test
    void whenCubeArea() {
        Box box = new Box(8, 4);
        double area = box.getArea();
        assertThat(area).isGreaterThan(95.99d)
                .isEqualTo(96.00d);
    }

}