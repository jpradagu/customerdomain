package com.nttdata.bootcamp.customerdomain.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Document(collection = "customerPersonals")
@Data
public class CustomerPersonal {
	@Id
	private String id;
	@NotEmpty
	private String dni;
	@NotEmpty
	private String name;
	@NotEmpty
	private String lastname;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datebirth;
	@NotEmpty
	private String phone;
	@NotEmpty
	private String email;
	private String address;

}
