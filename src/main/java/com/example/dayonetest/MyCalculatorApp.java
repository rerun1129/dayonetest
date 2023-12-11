package com.example.dayonetest;

public class MyCalculatorApp {

    public static void main ( String[] args ) {
        MyCalculator myCalculator = new MyCalculator ( 10.0 );
        myCalculator.divide ( 2.0 );
        System.out.println ( myCalculator.getResult () );
    }
}
