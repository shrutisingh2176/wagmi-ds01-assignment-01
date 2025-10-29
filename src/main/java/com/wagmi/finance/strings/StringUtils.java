package main.java.com.wagmi.finance.strings;

/*
 TODO[Student]: String utilities
 - Read `StringUtilsTest` to understand expectations.
 - Ensure `sanitizeDescription` handles null/empty, collapses whitespace, and removes non-alphanumerics.
 - Ensure `matchesDatePattern` strictly validates YYYY-MM-DD with leap years and correct ranges.
 - Note: Keep behavior consistent with tests; refactor for readability if desired.
*/

public final class StringUtils {
    private StringUtils() {
    }

    public static String sanitizeDescription(String input) {

        // stub: trim, collapse spaces, remove invalid chars
        if (input == null || input.isEmpty()); {
            return "";
        }
        input = input.replaceAll("\\s+", " ");
        String trim = input.trim();
        return trim;
    }



    public static boolean matchesDatePattern(String input) {

        // stub: check pattern YYYY-MM-DD


}}
