package visitor;

import entities.Child;

public class AverageScoreVisitor implements Visitor{
    @Override
    public void visit(Child child) {

        if (child.getAge() < 5) {
            child.setAverageScore(10d);
        }
        if (child.getAge() >= 5 && child.getAge() <12) {
            Double sum = 0d;
            for (Double x: child.getNiceScoreHistory()) {
                sum += x;
            }
            sum = sum / child.getNiceScoreHistory().size();
            child.setAverageScore(sum);
        }
        if (child.getAge() >= 12 && child.getAge() <= 18) {
            double sumOfScores = 0d;
            double sumOfIndexes = 0d;
            int index = 1;
            for (Double x: child.getNiceScoreHistory()) {
                sumOfScores += x * index;
                sumOfIndexes += index;
                index += 1;
            }
            sumOfScores = sumOfScores / sumOfIndexes;
            child.setAverageScore(sumOfScores);
        }
    }
}
