package com.imagnative.rs.core;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.imagnative.constants.StringConstants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseRs implements Serializable {

	private static final long serialVersionUID = 4334669267115607360L;

	private String status = StringConstants.EMPTY;

	private BaseErrorRs error;

	private BaseDataRs data;

}
