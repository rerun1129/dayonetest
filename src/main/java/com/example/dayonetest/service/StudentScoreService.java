package com.example.dayonetest.service;

import com.example.dayonetest.MyCalculator;
import com.example.dayonetest.controller.request.SaveExamScoreRequest;
import com.example.dayonetest.controller.response.ExamFailStudentResponse;
import com.example.dayonetest.controller.response.ExamPassStudentResponse;
import com.example.dayonetest.model.StudentFail;
import com.example.dayonetest.model.StudentPass;
import com.example.dayonetest.model.StudentScore;
import com.example.dayonetest.repository.StudentFailRepository;
import com.example.dayonetest.repository.StudentPassRepository;
import com.example.dayonetest.repository.StudentScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentScoreService {
    private final StudentScoreRepository studentScoreRepository;
    private final StudentPassRepository studentPassRepository;
    private final StudentFailRepository studentFailRepository;

    public void saveScore (SaveExamScoreRequest request, String exam ) {
        Integer korScore = request.korScore ( );
        Integer englishScore = request.englishScore ( );
        Integer mathScore = request.mathScore ( );
        StudentScore saveEntity = StudentScore.builder ( )
                                                .studentName ( request.studentName () )
                                                .exam ( exam )
                                                .korScore ( korScore )
                                                .englishScore ( englishScore )
                                                .mathScore ( mathScore )
                                                .build ();
        studentScoreRepository.save ( saveEntity );

        MyCalculator myCalculator = new MyCalculator ( );
        Double avgScore = myCalculator.add ( korScore.doubleValue ( ) )
                                    .add ( englishScore.doubleValue ( ) )
                                    .add ( mathScore.doubleValue ( ) )
                                    .divide ( 3.0 )
                                    .getResult ( );
        if(avgScore >= 60) {
            studentPassRepository.save ( saveEntity.toPass ( avgScore ) );
        }else {
            studentFailRepository.save ( saveEntity.toFail ( avgScore ) );
        }
    }

    public List <ExamPassStudentResponse> getPassStudentsList ( String exam ) {
        List <StudentPass> passList = studentPassRepository.findAll ( );

        return passList.stream ()
                        .filter ( item -> item.exam.equals ( exam ) )
                        .map ( StudentPass::toResponse )
                        .toList ( );
    }

    public List <ExamFailStudentResponse> getFailStudentsList(String exam) {
        List <StudentFail> passList = studentFailRepository.findAll ( );
        return passList.stream ()
                        .filter ( item -> item.exam.equals ( exam ) )
                        .map ( StudentFail::toResponse )
                        .toList ( );
    }

}
