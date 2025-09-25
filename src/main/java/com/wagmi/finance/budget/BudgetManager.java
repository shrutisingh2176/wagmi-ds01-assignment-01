package main.java.com.wagmi.finance.budget;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import main.java.com.wagmi.finance.model.Transaction;

public class BudgetManager {
    private final Map<String, Double> categoryLimits = new HashMap<>();
    private final Map<String, Double> categorySpending = new HashMap<>();
    private final Set<String> validCategories = new HashSet<>();

    public void addValidCategory(String category) {
        // stub
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean isValidCategory(String category) {
        // stub
        throw new UnsupportedOperationException("Not implemented");
    }

    public void setBudgetLimit(String category, double limit) {
        // stub
        throw new UnsupportedOperationException("Not implemented");
    }

    public double getBudgetLimit(String category) {
        // stub
        throw new UnsupportedOperationException("Not implemented");
    }

    public double getSpending(String category) {
        // stub
        throw new UnsupportedOperationException("Not implemented");
    }

    public void applyTransaction(Transaction tx) {
        // stub
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean isApproachingLimit(String category) {
        // stub
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean isOverLimit(String category) {
        // stub
        throw new UnsupportedOperationException("Not implemented");
    }
}
