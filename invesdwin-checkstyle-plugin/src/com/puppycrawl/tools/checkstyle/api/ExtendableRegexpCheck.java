package com.puppycrawl.tools.checkstyle.api;

import com.puppycrawl.tools.checkstyle.checks.regexp.RegexpCheck;

public class ExtendableRegexpCheck extends RegexpCheck {

	protected String getMessageBundle() {
		//fix message bundle lookup
		String className = RegexpCheck.class.getName();
		return AbstractViolationReporter.getMessageBundle(className);
	}

}
