package com.imagnative.helper;

import java.util.ArrayList;
import java.util.List;

import com.imagnative.constants.ErrorCodes;
import com.imagnative.rq.EmpIdRq;
import com.imagnative.rq.EmployeeRq;
import com.imagnative.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeHelper {

	public static List<String> saveEmployeeData(EmployeeRq rq) {
		if (log.isDebugEnabled()) {
			log.debug("Executing saveEmployeeData(EmployeeRq) ->");
		}

		List<String> errors = new ArrayList<>();
		try {
			if (Utils.isEmpty(rq.getEmpid())) {
				log.error(ErrorCodes.EC_REQUIRED_EMP_ID);
				errors.add(ErrorCodes.EC_REQUIRED_EMP_ID);
			}
			if (Utils.isEmpty(rq.getFirstname())) {
				log.error(ErrorCodes.EC_REQUIRED_FIRST_NAME);
				errors.add(ErrorCodes.EC_REQUIRED_FIRST_NAME);
			}
			if (Utils.isEmpty(rq.getLastname())) {
				log.error(ErrorCodes.EC_REQUIRED_LAST_NAME);
				errors.add(ErrorCodes.EC_REQUIRED_LAST_NAME);
			}
			if (Utils.isEmpty(rq.getEmail())) {
				log.error(ErrorCodes.EC_REQUIRED_EMAIL);
				errors.add(ErrorCodes.EC_REQUIRED_EMAIL);
			}
			if (Utils.isEmpty(rq.getPhoneno())) {
				log.error(ErrorCodes.EC_REQUIRED_PHONE_NO);
				errors.add(ErrorCodes.EC_REQUIRED_PHONE_NO);
			}
			if (Utils.isEmpty(rq.getEmail())) {
				log.error(ErrorCodes.EC_REQUIRED_EMAIL);
				errors.add(ErrorCodes.EC_REQUIRED_EMAIL);
			} else if (Utils.isValidEmail(rq.getEmail())) {
				log.error(ErrorCodes.EC_INVALID_EMAIL);
				errors.add(ErrorCodes.EC_INVALID_EMAIL);
			}

		} catch (Exception e) {
			log.error("Exception in saveEmployeeData(EmployeeRq) - " + e);
			errors.add(ErrorCodes.EC_INVALID_INPUT);
		}
		return errors;
	}

	public static List<String> retrieveEmployeeData(EmpIdRq rq) {
		if (log.isDebugEnabled()) {
			log.debug("Executing retrieveEmployeeData(EmpIdRq) ->");
		}

		List<String> errors = new ArrayList<>();
		try {
			if (Utils.isEmpty(rq.getEmpId())) {
				log.error(ErrorCodes.EC_REQUIRED_EMP_ID);
				errors.add(ErrorCodes.EC_REQUIRED_EMP_ID);
			}

		} catch (Exception e) {
			log.error("Exception in retrieveEmployeeData(EmpIdRq) - " + e);
			errors.add(ErrorCodes.EC_INVALID_INPUT);
		}
		return errors;
	}
}
