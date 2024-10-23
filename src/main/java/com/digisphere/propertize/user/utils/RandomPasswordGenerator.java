package com.digisphere.propertize.user.utils;

import java.util.Random;

public class RandomPasswordGenerator {

    private static final String[] uppercaseLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static final String[] lowercaseLetters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private static final String[] symbols = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "=", "+"};
    private static final String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final Random random = new Random();

    public static String toGenerate() {
        var upper = uppercaseLetters[random.nextInt(uppercaseLetters.length - 1)];
        var lower = lowercaseLetters[random.nextInt(lowercaseLetters.length - 1)];
        var symbol = symbols[random.nextInt(symbols.length - 1)];
        var number = numbers[random.nextInt(numbers.length - 1)];

        return upper.concat(lower).concat(symbol).concat(number);
    }
}
