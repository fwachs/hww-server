package com.twoclams.hww.server.exceptions;

public class UserIdAlreadyRegisteredException extends Exception {
	private static final long serialVersionUID = -6380254627693537917L;

	public UserIdAlreadyRegisteredException() {
		super();
	}

	public UserIdAlreadyRegisteredException(String arg0, Throwable cause) {
		super(arg0, cause);
	}

	public UserIdAlreadyRegisteredException(String arg0) {
		super(arg0);
	}

	public UserIdAlreadyRegisteredException(Throwable cause) {
		super(cause);
	}
}
