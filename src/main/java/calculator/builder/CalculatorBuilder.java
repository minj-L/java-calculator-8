package calculator.builder;

import calculator.service.CalculatorService;
import static camp.nextstep.edu.missionutils.Console.readLine;

public record CalculatorBuilder(CalculatorService calculatorService) {

    public static void runApp() {
        new CalculatorBuilder(new CalculatorService()).run();
    }

    private void run() {
        System.out.println("덧셈할 문자열을 입력해 주세요");
        String inputLine = readLine();
        if (!inputLine.contains("//")) {
            System.out.println("결과 : " + calculatorService.splitSperator(inputLine));
        }

        if (inputLine.contains("//")) {
            System.out.println("결과 : " + calculatorService.splitSperator2(inputLine));
        }
 }
}

