package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleNameIsDirector() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "������������"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("������������");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "������������"));
        Role result = store.findById("2");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsDirector() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "������������"));
        store.add(new Role("1", "�����������"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("������������");
    }

    @Test
    void whenReplaceThenRoleNameIsSubordinated() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "������������"));
        store.replace("1", new Role("1", "�����������"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("�����������");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "������������"));
        store.replace("10", new Role("2", "�����������"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("������������");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "������������"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsDirector() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "������������"));
        store.delete("2");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("������������");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "������������"));
        boolean rsl = store.replace("1", new Role("1", "�����������"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "������������"));
        boolean rsl = store.replace("2", new Role("2", "�����������"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "������������"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "������������"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}