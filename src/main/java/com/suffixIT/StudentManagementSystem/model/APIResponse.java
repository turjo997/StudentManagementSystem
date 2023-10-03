package com.suffixIT.StudentManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class APIResponse<T>{
    private T data;
    private String error_message;
}
