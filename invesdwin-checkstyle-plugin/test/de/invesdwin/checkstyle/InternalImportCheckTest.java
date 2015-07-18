package de.invesdwin.checkstyle;

// @Immutable
public final class InternalImportCheckTest {

    private InternalImportCheckTest() {}

    //CHECKSTYLE:OFF
    public static void main(final String[] args) {
        //CHECKSTYLE:ON
        final InternalImportCheck check = new InternalImportCheck();
        assertTrue(check.isIllegalImport("de.invesdwin.finanzdaten.internal.importieren.initial.boersen",
                "de.invesdwin.finanzdaten.internal.importieren.initial.unternehmen.internal.leads.XetraIsinSammler"));
        assertTrue(check.isIllegalImport("de.invesdwin.finanzdaten.internal.bla",
                "de.invesdwin.finanzdaten.internal.importieren.initial.unternehmen.internal.leads.XetraIsinSammler"));
        assertTrue(check.isIllegalImport("de.invesdwin.finanzdaten",
                "de.invesdwin.finanzdaten.internal.importieren.initial.unternehmen.internal.leads.XetraIsinSammler"));
        assertTrue(!check.isIllegalImport("de.invesdwin.finanzdaten.internal.importieren",
                "de.invesdwin.finanzdaten.internal.importieren.initial.unternehmen.internal.leads.XetraIsinSammler"));
        assertTrue(!check.isIllegalImport("de.invesdwin.finanzdaten.internal.importieren.initial.unternehmen.internal",
                "de.invesdwin.finanzdaten.internal.importieren.initial.unternehmen.internal.leads.XetraIsinSammler"));
        assertTrue(!check.isIllegalImport("de.invesdwin.finanzdaten.importieren.initial.unternehmen",
                "de.invesdwin.finanzdaten.importieren.initial.unternehmen.leads.XetraIsinSammler"));
        assertTrue(!check.isIllegalImport(
                "de.invesdwin.finanzdaten.internal.importieren.initial.unternehmen.internal.leads",
                "de.invesdwin.finanzdaten.internal.importieren.ImportUtil"));
        assertTrue(!check.isIllegalImport(
                "de.invesdwin.finanzdaten.internal.importieren.initial.unternehmen.internal.leads",
                "de.invesdwin.finanzdaten.importieren.ImportUtil"));
        assertTrue(!check.isIllegalImport(
                "de.invesdwin.finanzdaten.internal.importieren.internal.initial.unternehmen.internal.leads",
                "de.invesdwin.finanzdaten.importieren.ImportUtil"));
        assertTrue(!check.isIllegalImport("de.invesdwin.finanzdaten",
                "de.invesdwin.finanzdaten.internal.importieren.ImportUtil"));
        assertTrue(check.isIllegalImport("de.invesdwin.finanzdaten.asdf",
                "de.invesdwin.finanzdaten.internal.importieren.ImportUtil"));
        assertTrue(check.isIllegalImport("de.invesdwin.webproxy.sammler",
                "de.invesdwin.webproxy.internal.ProxyVerifikation"));
        assertTrue(check.isIllegalImport("de.invesdwin.webproxy.sammler.internal.pruefung",
                "de.invesdwin.webproxy.internal.ProxyVerifikation"));
    }

    private static void assertTrue(final boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

}
