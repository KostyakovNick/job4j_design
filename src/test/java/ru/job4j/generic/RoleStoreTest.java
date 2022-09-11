package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleNameIsDirector() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Director"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Director");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Director"));
        Role result = store.findById("2");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsDirector() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Director"));
        store.add(new Role("1", "Subordinated"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Director");
    }

    @Test
    void whenReplaceThenRoleNameIsSubordinated() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Director"));
        store.replace("1", new Role("1", "Subordinated"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Subordinated");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Director"));
        store.replace("10", new Role("2", "Subordinated"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Director");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Director"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsDirector() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Director"));
        store.delete("2");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Director");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Director"));
        boolean rsl = store.replace("1", new Role("1", "Subordinated"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Director"));
        boolean rsl = store.replace("2", new Role("2", "Subordinated"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Director"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Director"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}