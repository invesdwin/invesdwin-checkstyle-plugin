package de.invesdwin.checkstyle;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

// @NotThreadSafe
public class NotNullParameterAnnotationCheck extends AbstractCheck {

    private static final String NOTNULL = "NotNull";
    private static final String FQ_NOTNULL = "javax.validation.constrains.NotNull";

    private String message = "Use @Nonnull instead of @NotNull for parameters.";

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public int[] getDefaultTokens() {
        return new int[] { TokenTypes.PARAMETER_DEF };
    }

    @Override
    public void visitToken(final DetailAST aAST) {
        final boolean containsAnnotation = AnnotationUtility.containsAnnotation(aAST, NOTNULL)
                || AnnotationUtility.containsAnnotation(aAST, FQ_NOTNULL);
        if (containsAnnotation) {
            log(aAST.getLineNo(), aAST.getColumnNo(), message);
        }
    }

	@Override
	public int[] getAcceptableTokens() {
		return getDefaultTokens();
	}

	@Override
	public int[] getRequiredTokens() {
		return getDefaultTokens();
	}

}
