package com.mhtech.platform.msrv.algorithm.exception;

import static com.mhtech.platform.msrv.algorithm.exception.ErrorCode.NOT_FOUND_TEAM_WORK;

import java.util.Map;

@SuppressWarnings("serial")
public class NotFoundTeamWorkException extends AlgorithmException {

	public NotFoundTeamWorkException() {
		super(NOT_FOUND_TEAM_WORK);
	}
	
	public NotFoundTeamWorkException(Map<String, Object> errorData) {
		this();
		super.errorData = errorData;
	}
}
