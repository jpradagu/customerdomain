package com.nttdata.bootcamp.customerdomain.model;


import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    @Id
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String email;

}
