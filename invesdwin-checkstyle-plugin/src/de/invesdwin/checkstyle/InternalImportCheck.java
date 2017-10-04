package de.invesdwin.checkstyle;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FullIdent;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

// @NotThreadSafe
public class InternalImportCheck extends AbstractCheck {
	
    private static final String MESSAGE = "Internal import from a different package branch is forbidden. "
            + "The internal package also has to be a descendant of this one "
            + "and needs to be on the same internal level.";
    private String packageDef;

    private String internalPackageName = ".internal";

    public void setInternalPackageName(final String value) {
        internalPackageName = "." + value;
    }

    @Override
    public int[] getDefaultTokens() {
        return new int[] { TokenTypes.PACKAGE_DEF, TokenTypes.IMPORT, TokenTypes.STATIC_IMPORT };
    }

    @Override
    public void visitToken(final DetailAST aAST) {
        if (aAST.getType() == TokenTypes.PACKAGE_DEF) {
            final DetailAST nameAST = aAST.getLastChild().getPreviousSibling();
            final FullIdent full = FullIdent.createFullIdent(nameAST);
            packageDef = full.getText();
            return;
        }

        final FullIdent imp;
        if (aAST.getType() == TokenTypes.IMPORT) {
            imp = FullIdent.createFullIdentBelow(aAST);
        } else {
            imp = FullIdent.createFullIdent(aAST.getFirstChild().getNextSibling());
        }
        if (isIllegalImport(packageDef, imp.getText())) {
            log(aAST.getLineNo(), aAST.getColumnNo(), MESSAGE, imp.getText());
        }
    }

    protected boolean isIllegalImport(final String packageDef, final String importDef) {
        if (packageDef == null) {
            throw new IllegalStateException("PACKAGE_DEF not found yet?!?");
        }

        // import is no internal import
        if (!importDef.contains(internalPackageName)) {
            return false;
        }

        // remove everything after internal
        final String importDefAfterLastInternal = importDef.substring(0, importDef.lastIndexOf(internalPackageName)
                + internalPackageName.length());
        String packageDefAfterLastInternal;
        if (packageDef.contains(internalPackageName)) {
            packageDefAfterLastInternal = packageDef.substring(0, packageDef.lastIndexOf(internalPackageName)
                    + internalPackageName.length());
        } else {
            packageDefAfterLastInternal = packageDef;
        }

        // internal import in a higher package of this branch
        if (importDefAfterLastInternal.equals(packageDefAfterLastInternal)) {
            return false;
        }

        // in this internal package
        final int abstandAnzahlInternals = Math.abs(countMatches(importDef, internalPackageName)
                - countMatches(packageDef, internalPackageName));
        if (abstandAnzahlInternals == 1 && importDef.startsWith(packageDef)) {
            return false;
        }

        // internal import in a lower package of this branch
        if (abstandAnzahlInternals == 1 && packageDefAfterLastInternal.startsWith(importDefAfterLastInternal)) {
            return false;
        }

        // internal import of a different branch
        return true;
    }

    private static boolean isEmpty(final String str) {
        return str == null || str.length() == 0;
    }

    private static int countMatches(final String str, final String sub) {
        if (isEmpty(str) || isEmpty(sub)) {
            return 0;
        }
        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
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