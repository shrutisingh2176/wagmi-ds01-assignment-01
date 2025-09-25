package test.java.com.wagmi.finance.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.com.wagmi.finance.strings.StringUtils;

public class StringUtilsTest {

    @Test
    void sanitizeDescription_trims_and_collapses_spaces() {
        assertEquals("Hello World", StringUtils.sanitizeDescription("  Hello    World  "));
        assertEquals("", StringUtils.sanitizeDescription("   "));
    }

    @Test
    void matchesDatePattern_checks_basic_format() {
        assertTrue(StringUtils.matchesDatePattern("2024-01-31"));
        assertFalse(StringUtils.matchesDatePattern("2024/01/31"));
        assertFalse(StringUtils.matchesDatePattern("24-01-31"));
    }

    @Test
    void sanitizeDescription_handles_null_input() {
        assertThrows(Exception.class, () -> StringUtils.sanitizeDescription(null));
    }

    @Test
    void sanitizeDescription_handles_empty_string() {
        assertEquals("", StringUtils.sanitizeDescription(""));
    }

    @Test
    void sanitizeDescription_removes_special_characters() {
        assertEquals("Hello World", StringUtils.sanitizeDescription("Hello@#$%World"));
        assertEquals("Clean Text", StringUtils.sanitizeDescription("Clean!@#$%^&*()Text"));
    }

    @Test
    void sanitizeDescription_handles_multiple_spaces() {
        assertEquals("A B C", StringUtils.sanitizeDescription("A    B     C"));
        assertEquals("Single", StringUtils.sanitizeDescription("Single"));
    }

    @Test
    void sanitizeDescription_handles_tabs_and_newlines() {
        assertEquals("Hello World", StringUtils.sanitizeDescription("Hello\t\nWorld"));
        assertEquals("Clean Text", StringUtils.sanitizeDescription("Clean\r\nText"));
    }

    @Test
    void matchesDatePattern_handles_null_input() {
        assertFalse(StringUtils.matchesDatePattern(null));
    }

    @Test
    void matchesDatePattern_handles_empty_string() {
        assertFalse(StringUtils.matchesDatePattern(""));
    }

    @Test
    void matchesDatePattern_validates_month_range() {
        assertTrue(StringUtils.matchesDatePattern("2024-01-01"));
        assertTrue(StringUtils.matchesDatePattern("2024-12-31"));
        assertFalse(StringUtils.matchesDatePattern("2024-13-01"));
        assertFalse(StringUtils.matchesDatePattern("2024-00-01"));
    }

    @Test
    void matchesDatePattern_validates_day_range() {
        assertTrue(StringUtils.matchesDatePattern("2024-01-01"));
        assertTrue(StringUtils.matchesDatePattern("2024-01-31"));
        assertFalse(StringUtils.matchesDatePattern("2024-01-32"));
        assertFalse(StringUtils.matchesDatePattern("2024-01-00"));
    }

    @Test
    void matchesDatePattern_handles_leap_year() {
        assertTrue(StringUtils.matchesDatePattern("2024-02-29")); // Leap year
        assertFalse(StringUtils.matchesDatePattern("2023-02-29")); // Non-leap year
    }

    @Test
    void matchesDatePattern_handles_month_day_combinations() {
        assertTrue(StringUtils.matchesDatePattern("2024-04-30")); // April has 30 days
        assertFalse(StringUtils.matchesDatePattern("2024-04-31")); // April doesn't have 31 days
        assertTrue(StringUtils.matchesDatePattern("2024-06-30")); // June has 30 days
        assertFalse(StringUtils.matchesDatePattern("2024-06-31")); // June doesn't have 31 days
    }

    @Test
    void matchesDatePattern_handles_year_boundaries() {
        assertTrue(StringUtils.matchesDatePattern("0001-01-01"));
        assertTrue(StringUtils.matchesDatePattern("9999-12-31"));
        assertFalse(StringUtils.matchesDatePattern("0000-01-01"));
        assertFalse(StringUtils.matchesDatePattern("10000-01-01"));
    }

    @Test
    void matchesDatePattern_rejects_invalid_formats() {
        assertFalse(StringUtils.matchesDatePattern("2024/01/01"));
        assertFalse(StringUtils.matchesDatePattern("01-01-2024"));
        assertFalse(StringUtils.matchesDatePattern("2024-1-1"));
        assertFalse(StringUtils.matchesDatePattern("24-01-01"));
        assertFalse(StringUtils.matchesDatePattern("2024-01-1"));
        assertFalse(StringUtils.matchesDatePattern("2024-1-01"));
    }

    @Test
    void matchesDatePattern_handles_whitespace() {
        assertFalse(StringUtils.matchesDatePattern(" 2024-01-01 "));
        assertFalse(StringUtils.matchesDatePattern("2024-01-01 "));
        assertFalse(StringUtils.matchesDatePattern(" 2024-01-01"));
    }
}
