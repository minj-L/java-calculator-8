package calculator.utils;

import java.util.regex.Pattern;

public class Patterns {
    public static final Pattern POSITIVE_NUMBER_PATTERN = Pattern.compile("\\d+");
    public static final Pattern NEGATIVE_NUMBER_PATTERN = Pattern.compile("--\\d+");
    public static final Pattern DIGIT_END_PATTERN = Pattern.compile("\\d$");
}
