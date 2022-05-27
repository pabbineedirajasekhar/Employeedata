package com.imagnative.utils;

import java.util.List;

import com.imagnative.constants.StringConstants;
import com.imagnative.rs.core.BaseDataRs;
import com.imagnative.rs.core.BaseRs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {

	public static String getValidString(String str) {
		return ((null == str) ? StringConstants.EMPTY : str.trim());
	}

	public static boolean isValidString(String str) {
		return (((null == str) || (str.trim().isEmpty())) ? false : true);
	}

	public static boolean isEmpty(String str) {
		return (((null == str) || (str.trim().isEmpty())) ? true : false);
	}

	public static boolean isNotEmpty(String str) {
		return !(isEmpty(str));
	}

	public static boolean isEmpty(String[] strings) {
		return (((null == strings) || (0 == strings.length)) ? true : false);
	}

	public static boolean isNotEmpty(String[] strings) {
		return !(isEmpty(strings));
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(List list) {
		return (((null == list) || (list.isEmpty())) ? true : false);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(List list) {
		return !(isEmpty(list));
	}

	public static boolean isValidEmail(String email) {
		String strEmail = Utils.getValidString(email);
		if ((Utils.isNotEmpty(strEmail)) && (strEmail
				.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))) {
			return true;
		}
		return false;
	}

	public static BaseRs success(BaseDataRs dataRs) {

		if (log.isDebugEnabled()) {
			log.debug("Executing success(BaseDataRs) ->");
		}

		BaseRs rs = new BaseRs();
		rs.setStatus("SUCCESS");
		if (null != dataRs) {
			rs.setData(dataRs);
		}

		return rs;
	}

}
