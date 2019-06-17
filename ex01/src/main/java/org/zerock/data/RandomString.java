// https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
package org.zerock.data;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class RandomString.
 */
public class RandomString {

    /**
     * Generate a random string.
     *
     * @return the string
     */
    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    /** The Constant upper. */
    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /** The Constant lower. */
    public static final String lower = upper.toLowerCase(Locale.ROOT);

    /** The Constant digits. */
    public static final String digits = "0123456789";

    /** The Constant alphanum. */
    public static final String alphanum = upper + lower + digits;

    /** The random. */
    private final Random random;

    /** The symbols. */
    private final char[] symbols;

    /** The buf. */
    private final char[] buf;

    /**
     * Instantiates a new random string.
     *
     * @param length the length
     * @param random the random
     * @param symbols the symbols
     */
    public RandomString(int length, Random random, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    /**
     * Create an alphanumeric string generator.
     *
     * @param length the length
     * @param random the random
     */
    public RandomString(int length, Random random) {
        this(length, random, alphanum);
    }

    /**
     * Create an alphanumeric strings from a secure generator.
     *
     * @param length the length
     */
    public RandomString(int length) {
        this(length, new SecureRandom());
    }

    /**
     * Create session identifiers.
     */
    public RandomString() {
        this(21);
    }

}
