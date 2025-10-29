package main.java.com.wagmi.finance.budget;

/*
 TODO[Student]: Budget management with hash-based structures
 - Manage valid categories, budget limits, and spending.
 - `applyTransaction`: ignore income and non-positive amounts; update spending for valid categories.
 - `isApproachingLimit`: threshold around 40% (see tests), strictly less than limit.
 - `isOverLimit`: at or over limit for positive limits.
 - See `BudgetManagerTest` for edge cases and exact expectations.
*/

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
        if (category == null) {
            throw new UnsupportedOperationException("Category cannot be null or left blank");
        }
        validCategories.add(category);
    }

    public boolean isValidCategory(String category) {
        // stub
        return
                validCategories.contains(category);
    }

    public void setBudgetLimit(String category, double limit) {
        // stub
        if(!isValidCategory(category)){System.out.println("Category is Invalid!");
            return;
        }
        if (limit<=0)
        {System.out.println("Limit must be positive");
            return;
        }
        categoryLimits.put(category,limit);
    }

    public double getBudgetLimit(String category) {
        // stub
       return
               categoryLimits.getOrDefault(category,0.0);
    }

    public double getSpending(String category) {
        // stub
       return
               categorySpending.getOrDefault(category,0.0);
               
    }

    public void applyTransaction(Transaction tx) {
        // stub
        String category = tx.getCategory();
        double amount = tx.getAmount();
        if (! isValidCategory(category)){
            return;
        }
        if (amount<=0){
            return;


        }
        double currentSpending = getSpending(category);
        double newSpending = currentSpending + amount;
        categorySpending.put(category, newSpending);
    }

    public boolean isApproachingLimit(String category) {
        // stub
      double limit = getBudgetLimit(category);
      double spent = getSpending (category);
      if (limit ==0) return false;
      double remaining = limit - spent;
      return remaining<= ( limit * 0.4);
    }

    public boolean isOverLimit(String category) {
        // stub
        double limit = getBudgetLimit(category);
        double spent = getSpending (category);
        getSpending(category);
        return spent >= limit&& limit>0;
    }
}
