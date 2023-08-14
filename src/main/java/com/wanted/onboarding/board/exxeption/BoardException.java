package com.wanted.onboarding.board.exxeption;


import com.wanted.onboarding.support.exception.CustomException;
import com.wanted.onboarding.support.exception.ErrorCode;

public class BoardException extends CustomException {
	public BoardException() {
		super();
	}
	
	public BoardException(String message) {
		super(message);
	}
	
	public BoardException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BoardException(ErrorCode errorCode) {
		super(errorCode);
	}
	
	public BoardException(ErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}
}
