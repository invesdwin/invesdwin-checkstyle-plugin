package de.invesdwin.checkstyle;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.checks.regexp.RegexpCheck;

public class StackOverflowErrorIgnoringRegexpCheck extends RegexpCheck {

	protected String getMessageBundle() {
		// fix message bundle lookup
		String className = RegexpCheck.class.getName();
		return getMessageBundle(className);
	}

	//see AbstractViolationReporter
	private static String getMessageBundle(String className) {
		int endIndex = className.lastIndexOf('.');
		if (endIndex < 0) {
			return "messages";
		}
		String packageName = className.substring(0, endIndex);
		return packageName + "." + "messages";
	}

	@Override
	public void beginTree(DetailAST rootAST) {
		try {
			super.beginTree(rootAST);
		} catch (StackOverflowError t) {
			// ignore e.g. StackOverflowException, can happen on large files
			// with some complex regexes
		}
	}

}
