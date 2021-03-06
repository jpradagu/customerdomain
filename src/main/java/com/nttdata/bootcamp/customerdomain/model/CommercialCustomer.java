package com.nttdata.bootcamp.customerdomain.model;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Commercial Customers document.
 *
 */
@Document(collection = "commercialCustomers")
@Getter
@Setter
@ToString(callSuper = true)
public class CommercialCustomer extends Person {
  @NotEmpty
  private String ruc;
  @NotEmpty
  private String reasonSocial;
  @NotEmpty
  private String address;
}
