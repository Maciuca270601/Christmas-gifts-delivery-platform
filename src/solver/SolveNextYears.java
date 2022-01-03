package solver;

import common.Constants;
import database.Database;
import entities.Child;
import fileio.YearDataInput;
import utils.Utils;
import visitor.AverageScoreVisitor;
import visitor.BudgetVisitor;
import visitor.GiveGiftVisitor;

public final class SolveNextYears {

    /**
     * This method does all the tasks for the next steps of the simulation.
     */
    public void solve(final int currentYear) {

        // Update every child age.
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.setAge(child.getAge() + 1);
        }

        // Remove every child that has become a young adult.
        Database.getDatabase().getListOfChildren().removeIf(c -> c.getAge() > Constants.MAX_AGE);

        // Get current changes for the year.
        YearDataInput currChanges = Database.getDatabase().getAnnualChanges().get(currentYear - 1);

        // Update Santa Budget if necessary.
        if (currChanges.getNewSantaBudget() != null) {
            Database.getDatabase().setSantaBudget(currChanges.getNewSantaBudget());
        }

        // Add new children if they are not young adults already.
        Utils.addNewChildren(currChanges);

        // Update existing children from the list if necessary.
        Utils.updateExistingChildren(currChanges);

        // Update gift list if necessary.
        Utils.addNewGifts(currChanges);

        // Sort the lists of gifts before sending them to children.
        Database.getDatabase().sortListOfGifts();

        // Update average Score for every child.
        AverageScoreVisitor averageScore = new AverageScoreVisitor();
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.accept(averageScore);
        }

        // Update budget for every child.
        BudgetVisitor budget = new BudgetVisitor();
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.accept(budget);
        }

        // Give gifts for every child.
        GiveGiftVisitor giveGift = new GiveGiftVisitor();
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.getReceivedGifts().clear();
            child.accept(giveGift);
        }
    }
}
