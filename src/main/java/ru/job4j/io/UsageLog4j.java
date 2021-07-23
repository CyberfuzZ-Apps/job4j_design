package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 126;
        short s = 31000;
        int i = 100000;
        long l = 99999L;
        float f = 3.14F;
        double d = 36.6;
        char c = 'J';
        boolean boo = true;
        LOG.debug("Types info byte : {}, short : {}, int : {}, long : {}, "
                        + "float : {}, double : {}, char : {}, boolean : {}",
                b, s, i, l, f, d, c, boo);
    }
}
