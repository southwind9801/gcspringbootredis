package com.southwind.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class Student implements Serializable {
    private Long id;
    private String name;
    private Double score;
    private Date birthday;
}
