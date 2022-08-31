package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenUsernameIsIvan() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Ivan"));
        Role result = role.findById("1");
        assertThat(result.getName()).isEqualTo("Ivan");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Ivan"));
        Role result = role.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsIvan() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Ivan"));
        role.add(new Role("1", "Olga"));
        Role result = role.findById("1");
        assertThat(result.getName()).isEqualTo("Ivan");
    }

    @Test
    void whenReplaceThenUsernameIsOlga() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Ivan"));
        role.replace("1", new Role("1", "Olga"));
        Role result = role.findById("1");
        assertThat(result.getName()).isEqualTo("Olga");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Ivan"));
        role.replace("10", new Role("10", "Olga"));
        Role result = role.findById("1");
        assertThat(result.getName()).isEqualTo("Ivan");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Ivan"));
        role.delete("1");
        Role result = role.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsIvan() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Ivan"));
        role.delete("10");
        Role result = role.findById("1");
        assertThat(result.getName()).isEqualTo("Ivan");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Ivan"));
        boolean rsl = role.replace("1", new Role("1", "Olga"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Ivan"));
        boolean rsl = role.replace("10", new Role("10", "Olga"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Ivan"));
        boolean rsl = role.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Ivan"));
        boolean rsl = role.delete("2");
        assertThat(rsl).isFalse();
    }

}