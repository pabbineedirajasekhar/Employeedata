package com.imagnative.serviceimpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imagnative.bo.EmployeeBO;
import com.imagnative.constants.ErrorCodes;
import com.imagnative.exception.EmployeeExistsException;
import com.imagnative.exception.EmployeeNotFoundException;
import com.imagnative.exception.InvalidInputException;
import com.imagnative.helper.EmployeeHelper;
import com.imagnative.mapper.EmployeeDataRs;
import com.imagnative.mapper.EmployeeMapper;
import com.imagnative.repo.EmployeeRepo;
import com.imagnative.rq.EmpIdRq;
import com.imagnative.rq.EmployeeRq;
import com.imagnative.rs.EmployeeRs;
import com.imagnative.rs.core.BaseRs;
import com.imagnative.service.EmployeeService;
import com.imagnative.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	public static final String dd_MMM_yyyy = "dd-MMM-yyyy";
	public static DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern(dd_MMM_yyyy);

	@Autowired
	EmployeeRepo employeeRepo;

	@Override
	public BaseRs saveEmployeeData(EmployeeRq rq) throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Executing saveEmployeeData(EmployeeRq) ->");
		}
		List<String> errors = EmployeeHelper.saveEmployeeData(rq);
		if (Utils.isNotEmpty(errors)) {
			log.error(ErrorCodes.EC_INVALID_INPUT);
			throw new InvalidInputException(ErrorCodes.EC_INVALID_INPUT, errors);
		}

		String empId = Utils.getValidString(rq.getEmpid());
		EmployeeBO bo = employeeRepo.findByEmpidAndEnabledIsTrue(empId);
		if (bo != null) {
			log.error(ErrorCodes.EC_EMPLOYEE_ALREADY_EXISTS);
			throw new EmployeeExistsException(ErrorCodes.EC_INVALID_INPUT, errors);
		} else {
			bo = new EmployeeBO();
			bo.setEmpid(rq.getEmpid());
			bo.setFirstname(rq.getFirstname());
			bo.setLastname(rq.getLastname());
			bo.setPhoneno(Long.parseLong(rq.getPhoneno()));
			bo.setDoj(convertStringToLd(rq.getDoj()));
			bo.setEmail(rq.getEmail());
			bo.setSalary(Long.parseLong(rq.getSalary()));
		}
		employeeRepo.save(bo);

		EmployeeRs employeeRs = EmployeeMapper.mapToEmployeeDataRs(bo);
		return Utils.success(new EmployeeDataRs("Created Sucessfully", employeeRs));
	}

	@Override
	public BaseRs retrieveEmployeeTax(EmpIdRq rq) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Executing saveEmployeeData(EmployeeRq) ->");
		}
		List<String> errors = EmployeeHelper.retrieveEmployeeData(rq);
		if (Utils.isNotEmpty(errors)) {
			log.error(ErrorCodes.EC_INVALID_INPUT);
			throw new InvalidInputException(ErrorCodes.EC_INVALID_INPUT, errors);
		}
		String empId = Utils.getValidString(rq.getEmpId());
		EmployeeBO bo = employeeRepo.findByEmpidAndEnabledIsTrue(empId);
		if (bo == null) {
			log.error(ErrorCodes.EC_INVALID_INPUT);
			throw new EmployeeNotFoundException(ErrorCodes.EC_INVALID_INPUT, errors);
		}
		EmployeeRs employeeRs = EmployeeMapper.mapToEmployeeTaxRs(bo);
		return Utils.success(new EmployeeDataRs("Retrieve Sucessfully", employeeRs));
	}

	public static LocalDate convertStringToLd(final String dateString) {

		if (log.isDebugEnabled()) {
			log.debug("Executing convertStringToLd(dateString) ->");
		}

		String strDateTime = Utils.getValidString(dateString);
		if (Utils.isNotEmpty(strDateTime)) {
			try {
				return LocalDate.parse(strDateTime, DEFAULT_DATE_FORMATTER);
			} catch (Exception e) {
				log.error("Exception in convertStringToLd(dateString) - " + e);
			}
		}
		return null;
	}

}
