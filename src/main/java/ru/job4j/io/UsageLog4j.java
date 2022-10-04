package ru.job4j.io;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger("UsageLog4j");

    public static void main(String[] args) {
        byte b = 50;
        short s = 100;
        int i = 3000;
        long l = 10000;
        float f = 0.5f;
        double d = 0.7d;
        boolean bo = true;
        char c = '\u0000';


        LOG.debug("Primitive Data Types : byte - {}, short - {}, int - {}, long - {}, float - {}, double - {}, boolean - {}, char - {}", b, s, i, l, f, d, bo, c);

    }
}