package com.kemtech.springsec6demo.entity;

public record StudentResponse(
        Long id,
        String firstname,
        String lastname,
        String studentNumber
) {
}
