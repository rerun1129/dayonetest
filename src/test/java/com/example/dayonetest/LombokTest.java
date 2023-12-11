package com.example.dayonetest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LombokTest {

    @Test
    public void testDataTest(){
        TestModel testModel = new TestModel ( );
        testModel.setName ( "고윤석" );
        Assertions.assertEquals ( "고윤석", testModel.getName ());
    }
}
