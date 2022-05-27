package com.imagnative.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import com.imagnative.bo.EmployeeBO;
import com.imagnative.constants.StringConstants;
import com.imagnative.rs.EmployeeRs;
import com.imagnative.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeMapper {
	public static String FinancialYearStartDate = "01-APR-2022";
	public static final String dd_MMM_yyyy = "dd-MMM-yyyy";
	public static DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern(dd_MMM_yyyy);

	public static EmployeeRs mapToEmployeeDataRs(EmployeeBO bo) {

		if (log.isDebugEnabled()) {
			log.debug("Executing mapToEmployeeRs(EmployeeBO) ->");
		}
		try {
			EmployeeRs rs = null;
			if (null == bo) {
				log.warn("EmployeeBO is NULL");
				return rs;
			}
			rs = new EmployeeRs();
			rs.setEmpid(bo.getEmpid());
			rs.setFirstname(bo.getFirstname());
			rs.setLastname(bo.getLastname());
			rs.setEmail(bo.getEmail());
			rs.setPhoneNo(String.valueOf(bo.getPhoneno()));
			long yearlysalary = 0;
			if (bo.getSalary() > 0) {
				yearlysalary = bo.getSalary() * 12;
				rs.setYearlysalary(String.valueOf(yearlysalary));
			}
			return rs;
		} catch (Exception e) {
			log.error("Exception in mapToFormatRs(FormatBO) - " + e);
			return null;
		}

	}

	public static EmployeeRs mapToEmployeeTaxRs(EmployeeBO bo) {

		if (log.isDebugEnabled()) {
			log.debug("Executing mapToEmployeeRs(EmployeeBO) ->");
		}
		try {
			EmployeeRs rs = null;
			if (null == bo) {
				log.warn("EmployeeBO is NULL");
				return rs;
			}
			rs = new EmployeeRs();
			rs.setEmpid(bo.getEmpid());
			rs.setFirstname(bo.getFirstname());
			rs.setLastname(bo.getLastname());
			long yearlysalary = 0;
			if (bo.getSalary() > 0) {
				yearlysalary = bo.getSalary() * 12;
				rs.setYearlysalary(String.valueOf(yearlysalary));
			}
			long days = diffInDays(convertStringToLd(FinancialYearStartDate), bo.getDoj());
			long lop = days * (bo.getSalary() / 30);
			long tax = 0;
			long totalYearlysalary = yearlysalary - lop;
			if (totalYearlysalary <= 250000) {
				tax = 0;
			} else if ((250000 < totalYearlysalary) && (totalYearlysalary <= 500000)) {
				tax = (totalYearlysalary / 100) * 5;
			} else if ((500000 < totalYearlysalary) && (totalYearlysalary <= 1000000)) {
				tax = (totalYearlysalary / 100) * 10;
			} else if (totalYearlysalary > 1000000) {
				tax = (totalYearlysalary / 100) * 20;
			}
			rs.setTax(String.valueOf(tax));
			if (2500000 < totalYearlysalary) {
				long cess = ((totalYearlysalary) / 100) * 2;
				rs.setCess(String.valueOf(cess));
			}
			return rs;
		} catch (Exception e) {
			log.error("Exception in mapToFormatRs(FormatBO) - " + e);
			return null;
		}

	}

	public static long diffInDays(LocalDate fromDate, LocalDate toDate) {

		if (log.isDebugEnabled()) {
			log.debug("Executing diffInDays(fromDate, toDate) ->");
		}

		try {
			if (fromDate != null && toDate != null) {
				long days = ChronoUnit.DAYS.between(fromDate, toDate);
				if (days >= 0) {
					return days;
				}
			}
		} catch (Exception e) {
			log.error("Exception in diffInDays(fromDate, toDate) -" + e);
		}
		return -1;
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
