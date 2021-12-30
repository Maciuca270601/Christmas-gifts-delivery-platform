package solver;

import common.Constants;
import database.Database;
import entities.Child;
import visitor.AverageScoreVisitor;
import visitor.BudgetVisitor;
import visitor.GiveGiftVisitor;
import visitor.Visitor;

public final class SolveFirstYear {

    /**
     * This method does all the tasks for the first step of the simulation.
     */
    public void solve() {
        Visitor averageScore = new AverageScoreVisitor();
        Visitor budget = new BudgetVisitor();
        Visitor giveGift = new GiveGiftVisitor();

        // Remove every child that is over 18 years old.
        Database.getDatabase().getListOfChildren().removeIf(c -> c.getAge() > Constants.MAX_AGE);

        // Sort the lists of gifts before sending them to children.
        Database.getDatabase().sortListOfGifts();

        // Get average score for every child.
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.accept(averageScore);
        }

        // Set Budget for every child.
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.accept(budget);
        }

        // Give gifts for every child.
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.accept(giveGift);
        }
    }
}
