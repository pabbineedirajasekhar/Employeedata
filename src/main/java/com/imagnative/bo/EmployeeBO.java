package com.imagnative.bo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeBO {

	private String empid;

	private String firstname;

	private String lastname;

	private String email;

	private long phoneno;

	private LocalDate doj;

	private long salary;

	boolean enabled = true;

}
