package study;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringCalculator {
    private String numbers = "";
    private String operators = "";

    public StringCalculator(String expression) {
        parsingNumbersAndOperators(expression);
        //식 유효성 검사
        if(!expressionValidCheck(expression)) {
            throw new IllegalArgumentException("식이 잘못됐습니다.");
        }
    }

    //식에서 숫자와 연산자 파싱
    private void parsingNumbersAndOperators(String expression) {
        //식을 공백으로 스플릿
        String[] values = expression.split(" ");

        //숫자부분과 연산자부분 구분
        for(int i = 0; i < values.length; i++) {
            //짝수부분은 숫자
            if(i % 2 == 0) {
                this.numbers += values[i];
                continue;
            }
            //홀수부분은 연산자
            if(i % 2 != 0) {
                this.operators += values[i];
            }
        }
    }

    //식 유효성 검사
    private boolean expressionValidCheck(String expression) {
        //숫자로만 이루어졌는지 확인
        if(!this.numbers.matches("\\d+")) {
            return false;
        }
        //연산자로만 이루어졌는지 확인
        if(!this.operators.matches("[\\+\\-\\*/]+")) {
            return false;
        }

        //숫자와 연산자의 갯수가 맞지않을경우
        if(this.numbers.length() < 2 || this.operators.length() != this.numbers.length() - 1) {
            return false;
        }

        return true;
    }
}
