package com.kemtech.springsec6demo.service;

import com.kemtech.springsec6demo.entity.Student;
import com.kemtech.springsec6demo.entity.StudentRequest;
import com.kemtech.springsec6demo.entity.StudentResponse;
import com.kemtech.springsec6demo.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public StudentResponse findById(Long id){
        var student =  studentRepository.findById(id);
        if(student.isPresent()){
            StudentResponse studentResponse = new StudentResponse(
                    student.get().getId(),
                student.get().getFirstname(),
                student.get().getLastname(),
                student.get().getStudentNumber()
            );
            return studentResponse;
        }
        throw new EntityNotFoundException("Student " + id + " not found");
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<StudentResponse> findAll(){
        var studentList = studentRepository.findAll();
        if(!studentList.isEmpty()){
            List<StudentResponse> students = new ArrayList<>();
            studentList.forEach(s -> students.add(new StudentResponse(s.getId(), s.getFirstname(), s.getLastname(), s.getStudentNumber())));
            return students;
        }
        throw new RuntimeException("No students saved");
    }

    @PreAuthorize("hasRole('ADMIN')")
    public StudentResponse add(StudentRequest studentRequest){
        if(studentRequest!=null || studentRequest.equals("")){
                Student student = new Student();
                student.setFirstname(studentRequest.firstname());
                student.setLastname(studentRequest.lastname());
                student.setStudentNumber(studentRequest.lastname());
                studentRepository.save(student);
                return new StudentResponse(student.getId(), student.getFirstname(), student.getLastname(), student.getStudentNumber());
        }
        throw new RuntimeException("invalid Student Supplied");
    }
}
