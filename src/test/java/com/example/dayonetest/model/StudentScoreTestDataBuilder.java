package com.example.dayonetest.model;

public class StudentScoreTestDataBuilder {

    public static StudentScore.StudentScoreBuilder passed() {
        return StudentScore.builder ()
                .korScore ( 80 )
                .englishScore ( 100 )
                .mathScore ( 90 )
                .studentName ( "defaultName" )
                .exam ( "defaultExam" );
    }

    public static StudentScore.StudentScoreBuilder failed() {
        return StudentScore.builder ()
                        .korScore ( 30 )
                        .englishScore ( 60 )
                        .mathScore ( 20 )
                        .studentName ( "defaultName" )
                        .exam ( "defaultExam" );
    }
}
