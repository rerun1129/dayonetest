package com.example.dayonetest.service;

import com.example.dayonetest.controller.request.SaveExamScoreRequest;
import com.example.dayonetest.controller.response.ExamFailStudentResponse;
import com.example.dayonetest.controller.response.ExamPassStudentResponse;
import com.example.dayonetest.model.StudentFail;
import com.example.dayonetest.model.StudentPass;
import com.example.dayonetest.model.StudentScore;
import com.example.dayonetest.repository.StudentFailRepository;
import com.example.dayonetest.repository.StudentPassRepository;
import com.example.dayonetest.repository.StudentScoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StudentScoreServiceTest {
    @Test
    @DisplayName ( "Mock 생성 테스트" )
    public void saveScoreMockTest() throws Exception{
        //given
        StudentScoreService studentScoreService = new StudentScoreService (
                                        Mockito.mock ( StudentScoreRepository.class ),
                                        Mockito.mock ( StudentPassRepository.class ),
                                        Mockito.mock ( StudentFailRepository.class )
                                                    );
        String exam = "testExam";


        //when
        studentScoreService.saveScore (  new SaveExamScoreRequest ( "yunseok", 80, 100, 60 ), exam );
        //then
        studentScoreService.getPassStudentsList ( exam );
    }
    @Test
    @DisplayName ( "통과 성적 저장 로직 검증" )
    public void savePassScore() throws Exception{
        //given
        StudentScoreRepository studentScoreRepository = Mockito.mock ( StudentScoreRepository.class );
        StudentPassRepository studentPassRepository = Mockito.mock ( StudentPassRepository.class );
        StudentFailRepository studentFailRepository = Mockito.mock ( StudentFailRepository.class );
        StudentScoreService studentScoreService = new StudentScoreService ( studentScoreRepository,studentPassRepository, studentFailRepository);
        String exam = "testExam";
        SaveExamScoreRequest testPassStudent = new SaveExamScoreRequest ( "pass", 60, 60, 60 );

        StudentScore expectedScore = StudentScore.builder ( )
                                            .studentName ( "pass" )
                                            .exam ( exam )
                                            .korScore ( 60 )
                                            .englishScore ( 60 )
                                            .mathScore ( 60 )
                                            .build ( );

        StudentPass expectedPass = StudentPass.builder ( )
                                                .studentName ( "pass" )
                                                .exam ( exam )
                                                .avgScore ( 60.0 )
                                                .build ( );

        ArgumentCaptor <StudentScore> studentScoreArgumentCaptor = ArgumentCaptor.forClass ( StudentScore.class );
        ArgumentCaptor <StudentPass> studentPassArgumentCaptor = ArgumentCaptor.forClass ( StudentPass.class );
        ArgumentCaptor <StudentFail> studentFailArgumentCaptor = ArgumentCaptor.forClass ( StudentFail.class );

        //when
        studentScoreService.saveScore ( testPassStudent, exam );
        //then
        Mockito.verify ( studentScoreRepository, Mockito.times ( 1 ) ).save ( studentScoreArgumentCaptor.capture () );
        StudentScore captorValue = studentScoreArgumentCaptor.getValue ( );
        Assertions.assertEquals ( expectedScore.studentName, captorValue.studentName );
        Assertions.assertEquals ( expectedScore.exam, captorValue.exam );
        Assertions.assertEquals ( expectedScore.korScore, captorValue.korScore );
        Assertions.assertEquals ( expectedScore.englishScore, captorValue.englishScore );
        Assertions.assertEquals ( expectedScore.mathScore, captorValue.mathScore );

        Mockito.verify ( studentPassRepository, Mockito.times ( 1 ) ).save ( studentPassArgumentCaptor.capture () );
        StudentPass captorPassValue = studentPassArgumentCaptor.getValue ( );
        Assertions.assertEquals ( expectedPass.studentName, captorPassValue.studentName );
        Assertions.assertEquals ( expectedPass.exam, captorPassValue.exam );
        Assertions.assertEquals ( expectedPass.avgScore, captorPassValue.avgScore );

        Mockito.verify ( studentFailRepository, Mockito.times ( 0 ) ).save ( studentFailArgumentCaptor.capture () );
    }

    @Test
    @DisplayName ( "불합격 성적 저장 로직 검증" )
    public void saveFailScore() throws Exception{
        //given
        StudentScoreRepository studentScoreRepository = Mockito.mock ( StudentScoreRepository.class );
        StudentPassRepository studentPassRepository = Mockito.mock ( StudentPassRepository.class );
        StudentFailRepository studentFailRepository = Mockito.mock ( StudentFailRepository.class );
        StudentScoreService studentScoreService = new StudentScoreService ( studentScoreRepository,studentPassRepository, studentFailRepository);
        String exam = "testExam";
        SaveExamScoreRequest testFailStudent = new SaveExamScoreRequest ( "pass", 60, 59, 60 );
        //when
        studentScoreService.saveScore ( testFailStudent, exam );
        //then
        Mockito.verify ( studentScoreRepository, Mockito.times ( 1 ) ).save ( Mockito.any () );
        Mockito.verify ( studentPassRepository, Mockito.times ( 0 ) ).save ( Mockito.any () );
        Mockito.verify ( studentFailRepository, Mockito.times ( 1 ) ).save ( Mockito.any () );
    }

    @Test
    @DisplayName ( "합격자 명단 가져오기" )
    public void getPassList() throws Exception{
        //given
        StudentScoreRepository studentScoreRepository = Mockito.mock ( StudentScoreRepository.class );
        StudentPassRepository studentPassRepository = Mockito.mock ( StudentPassRepository.class );
        StudentFailRepository studentFailRepository = Mockito.mock ( StudentFailRepository.class );
        StudentScoreService studentScoreService = new StudentScoreService ( studentScoreRepository,studentPassRepository, studentFailRepository);
        String exam = "testExam";

        StudentPass student1 = StudentPass.builder ( ).id ( 1L ).exam ( "testExam" ).studentName ( "yunseok" )
                                        .avgScore ( 70.0 ).build ( );
        StudentPass student2 = StudentPass.builder ( ).id ( 2L ).exam ( "testExam" ).studentName ( "yunseok2" )
                                        .avgScore ( 80.0 ).build ( );
        StudentPass student3 = StudentPass.builder ( ).id ( 3L ).exam ( "secondExam" ).studentName ( "yunseok3" )
                                        .avgScore ( 90.0 ).build ( );
        Mockito.when ( studentPassRepository.findAll ( ) ).thenReturn (
                List.of (
                        student1,
                        student2,
                        student3
                    ) );
        //when
        List <ExamPassStudentResponse> expectList = Stream.of ( student1, student2 ).map ( StudentPass::toResponse ).toList ( );
        List <ExamPassStudentResponse> passStudentsList = studentScoreService.getPassStudentsList ( exam );
        //then
        Assertions.assertIterableEquals ( expectList, passStudentsList );
    }

    @Test
    @DisplayName ( "불합격자 명단 가져오기" )
    public void getFailList() throws Exception{
        //given
        StudentScoreRepository studentScoreRepository = Mockito.mock ( StudentScoreRepository.class );
        StudentPassRepository studentPassRepository = Mockito.mock ( StudentPassRepository.class );
        StudentFailRepository studentFailRepository = Mockito.mock ( StudentFailRepository.class );
        StudentScoreService studentScoreService = new StudentScoreService ( studentScoreRepository,studentPassRepository, studentFailRepository);
        String exam = "testExam";

        StudentFail student1 = StudentFail.builder ( ).id ( 1L ).exam ( "testExam" ).studentName ( "yunseok" )
                                    .avgScore ( 30.0 ).build ( );
        StudentFail student2 = StudentFail.builder ( ).id ( 2L ).exam ( "testExam" ).studentName ( "yunseok2" )
                                    .avgScore ( 40.0 ).build ( );
        StudentFail student3 = StudentFail.builder ( ).id ( 3L ).exam ( "secondExam" ).studentName ( "yunseok3" )
                                    .avgScore ( 80.0 ).build ( );
        Mockito.when ( studentFailRepository.findAll ( ) ).thenReturn (
                List.of (
                        student1,
                        student2,
                        student3
                        ) );
        //when
        List <ExamFailStudentResponse> expectList = Stream.of ( student1, student2 ).map ( StudentFail::toResponse ).toList ( );
        List <ExamFailStudentResponse> failStudentsList = studentScoreService.getFailStudentsList ( exam );
        //then
        Assertions.assertIterableEquals ( expectList, failStudentsList );
    }
}