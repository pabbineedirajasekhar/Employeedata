package com.imagnative.rs.core;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorRs implements Serializable {

	private static final long serialVersionUID = -4441996416869690126L;

	private String code;

	private String message;

}
