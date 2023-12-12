package com.example.dayonetest.service;

import com.example.dayonetest.repository.StudentFailRepository;
import com.example.dayonetest.repository.StudentPassRepository;
import com.example.dayonetest.repository.StudentScoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        String studentName = "yunseok";
        String exam = "testExam";
        Integer kor = 80;
        Integer english = 100;
        Integer math = 60;
        //when
        studentScoreService.saveScore ( studentName, exam, kor, english, math );
        //then
        studentScoreService.getPassStudentsList ( exam );
    }
}