package calculator.service;

import calculator.utils.Patterns;

import java.util.regex.Pattern;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static java.util.Objects.isNull;

public class CalculatorService {

    public String inputLine() {
        String target = readLine();
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

    public int splitSperator() {
//        String[] parts = targetLine().split("[,;]");

        int numberSum = Pattern.compile("\\d+")
                .matcher(inputLine())
                .results()
                .mapToInt(mr -> Integer.parseInt(mr.group()))
                .sum();

        return numberSum;
    }

}
