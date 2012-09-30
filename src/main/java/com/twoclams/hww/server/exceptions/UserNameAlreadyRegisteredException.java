package com.twoclams.hww.server.exceptions;

public class UserNameAlreadyRegisteredException extends Exception {
	private static final long serialVersionUID = -6380254627693537918L;

	public UserNameAlreadyRegisteredException() {
		super();
	}

	public UserNameAlreadyRegisteredException(String arg0, Throwable cause) {
		super(arg0, cause);
	}

	public UserNameAlreadyRegisteredException(String arg0) {
		super(arg0);
	}

	public UserNameAlreadyRegisteredException(Throwable cause) {
		super(cause);
	}
}
