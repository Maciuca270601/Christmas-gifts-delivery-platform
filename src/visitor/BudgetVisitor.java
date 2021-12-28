package visitor;

import database.Database;
import entities.Child;

public class BudgetVisitor implements Visitor{
    @Override
    public void visit(Child child) {

        double budgetUnit = 0d;
        double budget = 0d;
        Double sumOfAvgScores = 0d;

        for (Child c: Database.getDatabase().getListOfChildren()) {
            sumOfAvgScores += c.getAvgScore();
        }
        budgetUnit = Database.getDatabase().getSantaBudget() / sumOfAvgScores;
        budget = budgetUnit * child.getAvgScore();
        child.setBudget(budget);
    }
}
