package com.imagnative.service;

import com.imagnative.rq.EmpIdRq;
import com.imagnative.rq.EmployeeRq;
import com.imagnative.rs.core.BaseRs;

public interface EmployeeService {

	public BaseRs saveEmployeeData(EmployeeRq rq) throws Exception;

	public BaseRs retrieveEmployeeTax(EmpIdRq rq) throws Exception;

}
