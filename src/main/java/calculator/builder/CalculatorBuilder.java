package calculator.builder;

import calculator.service.CalculatorService;

public record CalculatorBuilder(CalculatorService calculatorService) {

    public static void runApp() {
        new CalculatorBuilder(new CalculatorService()).run();
    }

    private void run() {
        System.out.println("덧셈할 문자열을 입력해 주세요");
//        calculatorService.inputTargetLine();
        System.out.println("결과 : " + calculatorService.splitSperator());
    }
}

