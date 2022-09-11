package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .containsIgnoringCase("sph")
                .contains("S", "re")
                .doesNotContain("Cube")
                .startsWith("Sp")
                .startsWithIgnoringCase("s")
                .endsWith("re")
                .isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .containsIgnoringCase("cu")
                .contains("C", "e")
                .doesNotContain("Sphere")
                .startsWith("Cu")
                .startsWithIgnoringCase("c")
                .endsWith("be")
                .isEqualTo("Cube");
    }

    @Test
    void whenTetrahedronGetNumberOfVertices() {
        Box box = new Box(4, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isNotZero()
                .isPositive()
                .isEven()
                .isGreaterThan(1)
                .isLessThan(5)
                .isEqualTo(4);
    }

    @Test
    void whenCubeGetNumberOfVertices() {
        Box box = new Box(8, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isNotZero()
                .isPositive()
                .isEven()
                .isGreaterThan(1)
                .isLessThan(9)
                .isEqualTo(8);
    }

    @Test
    void isExistCube() {
        Box box = new Box(8, 10);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void isExistSphere() {
        Box box = new Box(0, 10);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void checkDoubleSphere() {
        Box box = new Box(0, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(1256.64d, withPrecision(0.007d))
                .isCloseTo(1256.63d, withPrecision(0.01d))
                .isCloseTo(1256.63d, Percentage.withPercentage(1.0d))
                .isGreaterThan(1256.63d)
                .isLessThan(1256.64d);
    }

    @Test
    void checkDoubleTetrahedron() {
        Box box = new Box(4, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(173.21d, withPrecision(0.005d))
                .isCloseTo(173.20d, withPrecision(0.01d))
                .isCloseTo(173.20d, Percentage.withPercentage(1.0d))
                .isGreaterThan(173.20d)
                .isLessThan(173.21d);
    }
}