package de.invesdwin.checkstyle;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.checks.regexp.RegexpCheck;

public class StackOverflowErrorIgnoringRegexpCheck extends RegexpCheck {

	@Override
	public void beginTree(DetailAST rootAST) {
		try {
			super.beginTree(rootAST);
		} catch (StackOverflowError t) {
			// ignore e.g. StackOverflowException, can happen on large files with some complex regexes
		}
	}

}
