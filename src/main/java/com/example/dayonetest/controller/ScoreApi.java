package com.example.dayonetest.controller;

import com.example.dayonetest.controller.request.SaveExamScoreRequest;
import com.example.dayonetest.controller.response.ExamFailStudentResponse;
import com.example.dayonetest.controller.response.ExamPassStudentResponse;
import com.example.dayonetest.service.StudentScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam/{exam}")
@RequiredArgsConstructor
public class ScoreApi {
    private final StudentScoreService studentScoreService;

    @PutMapping("/score")
    public void save (@PathVariable("exam") String exam, @RequestBody SaveExamScoreRequest request ){
        studentScoreService.saveScore ( request.studentName (), exam, request.korScore ( ), request.englishScore ( ), request.mathScore ( ) );
    }

    @GetMapping("/pass")
    public List <ExamPassStudentResponse> pass ( @PathVariable("exam") String exam ){
        return studentScoreService.getPassStudentsList ( exam );
    }

    @GetMapping("/fail")
    public List <ExamFailStudentResponse> fail ( @PathVariable("exam") String exam ){
        return studentScoreService.getFailStudentsList ( exam );
    }


}
