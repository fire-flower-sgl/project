package com.mhtech.platform.msrv.algorithm.exception;

@SuppressWarnings("serial")
public class DataSetHadLoadedException extends AlgorithmException {

	public DataSetHadLoadedException() {
		super(ErrorCode.DATA_SET_HAD_LOADED);
	}
}
