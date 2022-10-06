package ru.job4j.serialization.json;

import java.util.Arrays;

public class Worker {

    private final boolean q;
    private final int experience;
    private final String post;
    private final Person person;
    private final int[] size;

    public Worker(boolean q, int experience, String post, Person person, int[] size) {
        this.q = q;
        this.experience = experience;
        this.post = post;
        this.person = person;
        this.size = size;
    }

    public boolean isQ() {
        return q;
    }

    public int getExperience() {
        return experience;
    }

    public String getPost() {
        return post;
    }

    public Person getPerson() {
        return person;
    }

    public int[] getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Worker{"
                + " q=" + q
                + ", experience=" + experience
                + ", post=" + post
                + ", person=" + person
                + ", size=" + Arrays.toString(size)
                + '}';
    }
}
