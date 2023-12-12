package com.example.dayonetest.repository;

import com.example.dayonetest.model.StudentFail;
import com.example.dayonetest.model.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentFailRepository extends JpaRepository<StudentFail, Long> {

}
