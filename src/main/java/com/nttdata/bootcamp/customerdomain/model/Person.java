package com.nttdata.bootcamp.customerdomain.model;


import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/** Person model.*/
@Getter
@Setter
@ToString
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
