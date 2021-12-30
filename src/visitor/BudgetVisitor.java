package visitor;

import database.Database;
import entities.Child;

public final class BudgetVisitor implements Visitor {
    @Override
    public void visit(final Child child) {

        double budgetUnit;
        double budget;
        Double sumOfAvgScores = 0d;

        for (Child c: Database.getDatabase().getListOfChildren()) {
            sumOfAvgScores += c.getAverageScore();
        }
        budgetUnit = Database.getDatabase().getSantaBudget() / sumOfAvgScores;
        budget = budgetUnit * child.getAverageScore();
        child.setAssignedBudget(budget);
    }
}
