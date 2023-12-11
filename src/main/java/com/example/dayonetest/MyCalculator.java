package com.example.dayonetest;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MyCalculator {
    private Double result;

    public MyCalculator ( ) {
        this.result = 0.0;
    }

    public MyCalculator add(Double num){
        this.result += num;
        return this;
    }

    public MyCalculator minus(Double num){
        this.result -= num;
        return this;
    }

    public MyCalculator multiply(Double num){
        this.result *= num;
        return this;
    }

    public MyCalculator divide(Double num){
        if(num == 0.0) throw new ZeroDivisionException ( "0으로 나눌 수 없습니다." );
        this.result /= num;
        return this;
    }

    public static class ZeroDivisionException extends RuntimeException {
        public ZeroDivisionException ( String message ) {
            super ( message );
        }
    }
}
