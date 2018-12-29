package com.qa.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileValidator {

	private Pattern pattern;
	private Matcher matcher;

	
	public MobileValidator() {
		pattern = Pattern.compile("[7-9][0-9]{9}");
	}

	public boolean validate(final String hex) {

		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
}
