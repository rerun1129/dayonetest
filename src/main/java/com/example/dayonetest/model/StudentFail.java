package com.example.dayonetest.model;

import com.example.dayonetest.controller.response.ExamFailStudentResponse;
import com.example.dayonetest.controller.response.ExamPassStudentResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "student_fail")
@Entity
@Getter
public class StudentFail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_fail_id")
    private Long id;

    @Column(name = "exam")
    public String exam;

    @Column(name = "student_name")
    public String studentName;

    @Column(name = "avg_score")
    public Double avgScore;

    public ExamFailStudentResponse toResponse ( ) {
        return new ExamFailStudentResponse(studentName, avgScore);
    }
}
