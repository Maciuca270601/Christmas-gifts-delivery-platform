package visitor;

import entities.Child;
import entities.Gift;
import utils.Utils;

import java.util.ArrayList;

public class GiveGiftVisitor implements Visitor{
    @Override
    public void visit(Child child) {

        // calculates the amount of money spent on the gifts for this child
        Double currentSpendings = 0d;
        ArrayList<String> strings = child.getGiftsPreferences();
        if (child.getAssignedBudget() != 0d) {
            for (int i = 0; i < strings.size() && currentSpendings < child.getAssignedBudget(); i++)  {
                int sem = 0;
                ArrayList<Gift> gifts = new ArrayList<>();
                gifts = Utils.pickArray(strings.get(i));
                for (int j = 0; j < gifts.size() && sem == 0; j++) {
                    if (gifts.get(j).getPrice() < child.getAssignedBudget() - currentSpendings) {
                        child.getReceivedGifts().add(gifts.get(j));
                        currentSpendings += gifts.get(j).getPrice();
                        sem = 1;
                    }
                }
            }
        }
    }
}
