package com.example.dayonetest.model;

import com.example.dayonetest.controller.response.ExamPassStudentResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "student_pass")
@Entity
@Getter
public class StudentPass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_pass_id")
    private Long id;

    @Column(name = "exam")
    public String exam;

    @Column(name = "student_name")
    public String studentName;

    @Column(name = "avg_score")
    public Double avgScore;

    public ExamPassStudentResponse toResponse () {
        return new ExamPassStudentResponse(studentName, avgScore);
    }

}
