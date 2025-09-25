package test.java.com.wagmi.finance.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FinanceAppSmokeTest {
    @Test
    void main_runs_without_throwing() {
        assertDoesNotThrow(() -> main.java.com.wagmi.finance.app.FinanceApp.main(new String[] {}));
    }
}
