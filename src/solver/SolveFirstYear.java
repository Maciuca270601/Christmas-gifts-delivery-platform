package solver;

import database.Database;
import entities.Child;
import visitor.*;

public class SolveFirstYear {

    public SolveFirstYear() {

    }

    public void solve() {
        Visitor averageScore = new AverageScoreVisitor();
        Visitor budget = new BudgetVisitor();
        Visitor giveGift = new GiveGiftVisitor();

        // Remove every child that is over 18 years old
        Database.getDatabase().getListOfChildren().removeIf(c -> c.getAge() > 18);
        // Sort the lists of gifts before sending them to children
        Database.getDatabase().sortListOfGifts();

        // Get average score for every child
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.accept(averageScore);
        }

        // Set Budget for every child
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.accept(budget);
        }

        // Give gifts for every child
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.accept(giveGift);
        }
    }
}
