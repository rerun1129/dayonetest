package com.example.dayonetest.controller.request;

public record SaveExamScoreRequest(String studentName, Integer korScore, Integer englishScore, Integer mathScore) {}
