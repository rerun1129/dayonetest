package com.example.dayonetest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyCalculatorTest {

    MyCalculator myCalculator;

    @BeforeEach
    private void init() {
        myCalculator = new MyCalculator ( 10.0 );
    }

    @Test
    void add ( ) {
        myCalculator.add ( 10.0 );
        assertEquals ( 20.0, myCalculator.getResult () );
    }

    @Test
    void minus ( ) {
        myCalculator.minus ( 10.0 );
        assertEquals ( 0.0, myCalculator.getResult () );
    }

    @Test
    void multiply ( ) {
        myCalculator.multiply ( 5.0 );
        assertEquals ( 50.0, myCalculator.getResult () );
    }

    @Test
    void divide ( ) {
        myCalculator.divide ( 2.0 );
        assertEquals ( 5.0, myCalculator.getResult () );
    }

    @Test
    void complicatedCalculate ( ) {
        //given
        MyCalculator myCalculatorTwo = new MyCalculator ( );
        //when
        myCalculatorTwo.add ( 10.0 )
                        .minus ( 5.0 )
                        .multiply ( 5.0 )
                        .divide ( 5.0 );
        //then
        assertEquals ( 5.0, myCalculatorTwo.getResult () );
    }

    @Test
    public void divideZeroTest(){
        //given
        MyCalculator myCalculatorTwo = new MyCalculator ( );
        //when
        //then
        assertThrows ( MyCalculator.ZeroDivisionException.class,  () -> myCalculatorTwo.divide ( 0.0 ));
    }
}