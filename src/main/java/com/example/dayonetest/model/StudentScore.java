package com.example.dayonetest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "student_score")
@Entity
@Getter
public class StudentScore {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_score_id")
    private Long id;

    @Column(name = "exam")
    public String exam;

    @Column(name = "student_name")
    public String studentName;

    @Column(name = "kor_score")
    public Integer korScore;

    @Column(name = "english_score")
    public Integer englishScore;

    @Column(name = "math_score")
    public Integer mathScore;

    public StudentPass toPass(Double avgScore) {
        return StudentPass.builder ()
                            .studentName ( studentName )
                            .exam ( exam )
                            .avgScore ( avgScore )
                            .build (  );
    }

    public StudentFail toFail(Double avgScore) {
        return StudentFail.builder ()
                        .studentName ( studentName )
                        .exam ( exam )
                        .avgScore ( avgScore )
                        .build (  );
    }
}
