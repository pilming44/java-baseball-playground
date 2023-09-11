package study;

public class StringCalculator {
    StringCalculatorParser parser;

    public StringCalculator(String expression) {
        //식 유효성 검사
        parser = new StringCalculatorParser(expression);

        if(!parser.isExpressionValid()) {
            throw new IllegalArgumentException("식이 잘못됐습니다.");
        }
    }

    public Double getResult() {
        String[] values = parser.getValues();

        Double CalculateResult = Double.parseDouble(values[0]);

        String[] buffer = new String[2];

        for(int i = 1; i < values.length; i++) {
            //홀수번째는 연산자
            if(i % 2 != 0) {
                buffer[0] = values[i];
                continue;
            }
            //짝수번째는 숫자
            if(i % 2 == 0) {
                buffer[1] = values[i];
            }

            //계산
            switch (buffer[0]) {
                case "+":
                    CalculateResult += Integer.parseInt(buffer[1]);
                    break;
                case "-":
                    CalculateResult -= Integer.parseInt(buffer[1]);
                    break;
                case "*":
                    CalculateResult *= Integer.parseInt(buffer[1]);
                    break;
                case "/":
                    CalculateResult /= Integer.parseInt(buffer[1]);
                    break;
            }

            //버퍼 초기화
            buffer = new String[2];
        }
        return CalculateResult;
    }
}
