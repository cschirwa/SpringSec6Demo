package com.kemtech.springsec6demo.controller;

import com.kemtech.springsec6demo.entity.Student;
import com.kemtech.springsec6demo.entity.StudentRequest;
import com.kemtech.springsec6demo.entity.StudentResponse;
import com.kemtech.springsec6demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping(value = {"/students/{id}", "/students/{id}/"})
    public ResponseEntity<StudentResponse> findById(@PathVariable("id") Long studentId){
        return ResponseEntity.ok(studentService.findById(studentId));
    }

    @GetMapping(value = {"/students", "/students/"})
    public ResponseEntity<List<StudentResponse>> findAll(){
        return ResponseEntity.ok(studentService.findAll());
    }

    @PostMapping(value = {"/students", "/students/"})
    public ResponseEntity<StudentResponse> add(StudentRequest studentRequest){
        return ResponseEntity.ok(studentService.add(studentRequest));
    }
}
