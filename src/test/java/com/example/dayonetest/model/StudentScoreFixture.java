package com.example.dayonetest.model;

public class StudentScoreFixture {

    public static StudentScore passed() {
        return StudentScore.builder ()
                            .korScore ( 80 )
                            .englishScore ( 100 )
                            .mathScore ( 90 )
                            .studentName ( "defaultName" )
                            .exam ( "defaultExam" ).build ();
    }

    public static StudentScore failed() {
        return StudentScore.builder ()
                            .korScore ( 30 )
                            .englishScore ( 60 )
                            .mathScore ( 20 )
                            .studentName ( "defaultName" )
                            .exam ( "defaultExam" ).build ();
    }
}
