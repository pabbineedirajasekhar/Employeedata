package com.imagnative.mapper;

import com.imagnative.rs.EmployeeRs;
import com.imagnative.rs.core.BaseDataRs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDataRs extends BaseDataRs {

	private static final long serialVersionUID = 5001939833326415621L;

	private EmployeeRs employee;

	public EmployeeDataRs(String message) {
		super(message);
	}

	public EmployeeDataRs(String message, EmployeeRs employee) {
		super(message);
		this.employee = employee;
	}
}