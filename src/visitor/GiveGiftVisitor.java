package visitor;

import entities.Child;
import entities.Gift;
import utils.Utils;
import java.util.ArrayList;

public final class GiveGiftVisitor implements Visitor {
    @Override
    public void visit(final Child child) {

        // Gives gift to every child based on some given criteria.
        Double currentSpendings = 0d;
        ArrayList<String> strings = child.getGiftsPreferences();
        if (child.getAssignedBudget() != 0d) {
            for (int i = 0; i < strings.size()
                    && currentSpendings < child.getAssignedBudget(); i++)  {
                // ok == 0 -> gift has not been assigned yet.
                int ok = 0;
                ArrayList<Gift> gifts;
                gifts = Utils.pickArray(strings.get(i));
                for (int j = 0; j < gifts.size() && ok == 0; j++) {
                    if (gifts.get(j).getPrice() < child.getAssignedBudget() - currentSpendings) {
                        child.getReceivedGifts().add(gifts.get(j));
                        currentSpendings += gifts.get(j).getPrice();
                        ok = 1;
                    }
                }
            }
        }
    }
}
