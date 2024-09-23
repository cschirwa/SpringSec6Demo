package com.kemtech.springsec6demo.entity;

public record StudentRequest (
        String firstname,
        String lastname,
        String studentNumber
){
}
