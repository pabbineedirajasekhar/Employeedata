package com.imagnative.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imagnative.rq.EmpIdRq;
import com.imagnative.rq.EmployeeRq;
import com.imagnative.rs.core.BaseRs;
import com.imagnative.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@PostMapping("/employee")
	public ResponseEntity<?> saveEmployeeData(@RequestBody EmployeeRq rq) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Executing RESTfulService [POST /employee]");
		}
		BaseRs baseRsVM = employeeService.saveEmployeeData(rq);
		return new ResponseEntity<>(baseRsVM, HttpStatus.OK);
	}
	
	 @GetMapping("/employee/{empId}")
	    public ResponseEntity<BaseRs> retrieveEmployee(@PathVariable("empId") String empId)
	                    throws Exception {

	        if (log.isDebugEnabled()) {
	            log.debug("Executing RESTfulService [empId]");
	        }
	        EmpIdRq rq = new EmpIdRq();
	        rq.setEmpId(empId);
	        BaseRs baseRsVM = employeeService.retrieveEmployeeTax(rq);
	        return new ResponseEntity<BaseRs>(baseRsVM, HttpStatus.OK);
	    }

}
