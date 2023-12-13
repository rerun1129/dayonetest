package com.example.dayonetest.service;

import com.example.dayonetest.IntegrationTest;
import com.example.dayonetest.controller.request.SaveExamScoreRequest;
import com.example.dayonetest.controller.response.ExamFailStudentResponse;
import com.example.dayonetest.controller.response.ExamPassStudentResponse;
import com.example.dayonetest.model.StudentScore;
import com.example.dayonetest.model.StudentScoreFixture;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentScoreServiceIntegrationTest extends IntegrationTest {
    @Autowired
    private StudentScoreService studentScoreService;

    @Autowired
    private EntityManager entityManager;


    @Test
    public void savePassedTest() throws Exception{
        //given
        StudentScore passed = StudentScoreFixture.passed ( );
        SaveExamScoreRequest saveExamScoreRequest = StudentScoreFixture.makeRequestScore ( passed );
        //when
        studentScoreService.saveScore ( saveExamScoreRequest, passed.exam );
        entityManager.flush ();
        entityManager.clear ();
        //then
        List <ExamPassStudentResponse> passStudentsList = studentScoreService.getPassStudentsList ( passed.exam );

        Assertions.assertEquals ( 1, passStudentsList.size () );

        ExamPassStudentResponse examPassStudentResponse = passStudentsList.get ( 0 );
        Assertions.assertEquals ( passed.studentName, examPassStudentResponse.studentName () );
    }

    @Test
    public void saveFailedTest() throws Exception{
        //given
        StudentScore failed = StudentScoreFixture.failed ( );
        SaveExamScoreRequest saveExamScoreRequest = StudentScoreFixture.makeRequestScore ( failed );
        //when
        studentScoreService.saveScore ( saveExamScoreRequest, failed.exam );
        entityManager.flush ();
        entityManager.clear ();
        //then
        List <ExamFailStudentResponse> failStudentsList = studentScoreService.getFailStudentsList ( failed.exam );

        Assertions.assertEquals ( 1, failStudentsList.size () );

        ExamFailStudentResponse examFailStudentResponse = failStudentsList.get ( 0 );
        Assertions.assertEquals ( failed.studentName, examFailStudentResponse.studentName () );

    }
}
