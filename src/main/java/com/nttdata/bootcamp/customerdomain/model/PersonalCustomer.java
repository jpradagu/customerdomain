package com.nttdata.bootcamp.customerdomain.model;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

/** Personal Customer Document.*/
@Document(collection = "personalCustomers")
@Getter
@Setter
@ToString(callSuper = true)
public class PersonalCustomer extends Person {

  @NotEmpty
  private String dni;
  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date datebirth;
  @NotEmpty
  private String address;
}
