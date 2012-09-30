package com.twoclams.hww.server.filters;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ValidationFilter implements Filter {
	private static final String SECRET_DIGEST_SEED = "The sky is blue";

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		boolean valid = false;
		
		if (request.getParameterMap().keySet().size() > 1) {
			String validation = request.getParameter("validation");
			String digest = digestForRequest(request);
			valid = validation.equalsIgnoreCase(digest);
		} else {
			valid = true;
		}

		if (!valid) {
			response.getWriter().write("{errorCode:2}");
		} else {
			chain.doFilter(request, response);
		}
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	private String digestForRequest(ServletRequest request) {
		String digest;
		
		@SuppressWarnings("unchecked")
		List<String> parameterNames = Collections.list(request.getParameterNames());
		
		Collections.sort(parameterNames);
		String textToDigest = new String(SECRET_DIGEST_SEED);
		for (String parameterName : parameterNames) {
			if (!parameterName.equals("validation")) {
				textToDigest += request.getParameter(parameterName);
			}
		}

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			sha.update(textToDigest.getBytes());
			byte digestBytes[] = sha.digest();
			digest = toHex(digestBytes);
		} catch (NoSuchAlgorithmException e) {
			digest = "NO";
		}
		return digest;
	}

	public static String toHex(byte[] bytes) {
	    BigInteger bi = new BigInteger(1, bytes);
	    return String.format("%0" + (bytes.length << 1) + "X", bi);
	}
}