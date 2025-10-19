package calculator.service;

import calculator.utils.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public class CalculatorService {

    public String validateInput(String input) {
        if (isNull(input)) {
            throw new IllegalArgumentException("입력 값이 없습니다.");
        }

        if (input.isBlank()) {
            return "0";
        }

        if (!Patterns.POSITIVE_NUMBER_PATTERN.matcher(input).find()) {
            throw new IllegalArgumentException(
                    "잘못된 값이 입력되었습니다: 양수 숫자가 포함되어야 합니다.");
        }

        if (!Patterns.DIGIT_END_PATTERN.matcher(input).find()) {
            throw new IllegalArgumentException(
                    "잘못된 값이 입력되었습니다: 구분자로 끝나면 안 됩니다.");
        }

        if ((input.startsWith("-") && Character.isDigit(input.charAt(1)))
                || Patterns.NEGATIVE_NUMBER_PATTERN.matcher(input).find()) {
            throw new IllegalArgumentException(
                    "잘못된 값이 입력되었습니다: 음수 숫자가 포함되어서는 안 됩니다.");
        }

        return input;
    }

    public int sumNumbers(String input) {
        String validatedInput = validateInput(input);

        return Pattern.compile("\\d+")
                .matcher(validatedInput)
                .results()
                .mapToInt(mr -> Integer.parseInt(mr.group()))
                .sum();
    }

    public int sumNumbersWithCustomSeparator(String input) {
        String validatedInput = validateInput(input);
        validatedInput = validatedInput.replace("\\n", "\n");

        String separator = extractBetween(validatedInput, "//", "\n");
        String numbersPart = validatedInput.substring(validatedInput.indexOf("\n") + 1);

        String regex = "[" + separator + "]";

        return Pattern.compile(regex)
                .splitAsStream(numbersPart)
                .mapToInt(Integer::parseInt)
                .sum();
    }

    public String extractBetween(String input, String start, String end) {
        Pattern pattern = Pattern.compile(start + "(.*?)" + end);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String between = matcher.group(1);

            if (between.isEmpty()) {
                throw new IllegalArgumentException("구분자가 없습니다.");
            }

            return between;
        }

        throw new IllegalArgumentException("시작과 끝 패턴 사이의 값을 찾을 수 없습니다.");
    }
}
