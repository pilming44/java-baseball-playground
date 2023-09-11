package study;

public class StringCalculatorParser {

    String[] values;

    public StringCalculatorParser(String expression) {
        values = expression.split(" ");
    }

    public boolean isExpressionValid() {
        int valuesLength = values.length;

        //식의 길이가 3보다 작거나 짝수이면 비정상
        if(valuesLength < 3 || valuesLength % 2 ==0) {
            return false;
        }

        for(int i =0; i < valuesLength; i++) {
            //짝수번째는 숫자
            if(i % 2 == 0 && !isNumber(values[i])) {
                return false;
            }
            //홀수번째는 연산자
            if(i % 2 != 0 && !isOperator(values[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean isNumber(String value) {
        return value.matches("\\d+");
    }

    private boolean isOperator(String value) {
        return value.matches("[\\+\\-\\*/]+");
    }

    public String[] getValues() {
        return values;
    }
}
