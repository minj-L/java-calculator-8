package calculator.service;

import calculator.utils.Patterns;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static java.util.Objects.isNull;

public class CalculatorService {

    public String inputLine(String target) {
        if (isNull(target)) {
            throw new IllegalArgumentException("입력된 값이 없습니다.");
        }

        // fixme
        if (target.isBlank()){
            return "0";
        }

        if (!Patterns.POSITIVE_NUMBER_PATTERN.matcher(target).find()) {
            throw new IllegalArgumentException("잘못된 값이 입력되었습니다. : 양수인 숫자가 포함되지 않은 문자열 입력");
        }

        if (!Patterns.DIGIT_END_PATTERN.matcher(target).find()) {
            throw new IllegalArgumentException("잘못된 값이 입력되었습니다. : 구분자로 마무리 된 문자열 입력");
        }

        if ((target.startsWith("-") && Character.isDigit(target.charAt(1)))
                || Patterns.NEGATIVE_NUMBER_PATTERN.matcher(target).find()) {
            throw new IllegalArgumentException("잘못된 값이 입력되었습니다. : 음수가 포함 된 문자열 입력");
        }

        return target;
    }

    public int splitSperator(String target) {
        String input = inputLine(target);
        return Pattern.compile("\\d+")
                .matcher(input)
                .results()
                .mapToInt(mr -> Integer.parseInt(mr.group()))
                .sum();
    }

    public int splitSperator2(String target) {
        String input = inputLine(target);
        input = input.replace("\\n", "\n");

        String parts = extractBetween(input, "//", "\n");
        String numbersPart = input.substring(input.indexOf("\n") + 1);

        String regex = "[" + parts + "]";

        return Pattern.compile(regex)
                .splitAsStream(numbersPart)
                .mapToInt(Integer::parseInt)
                .sum();
    }

    public String extractBetween(String target, String start, String end) {
        Pattern pattern = Pattern.compile(start + "(.*?)" + end);
        Matcher matcher = pattern.matcher(target);
        if (matcher.find()) {
            String between = matcher.group(1);

            if (between.isEmpty()) {
                throw new IllegalArgumentException("구분자가 없습니다.");
            }

            return between;
        }

        throw new IllegalArgumentException("A와 B 사이의 패턴을 찾을 수 없습니다.");
    }
}
