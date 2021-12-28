package visitor;

import entities.Child;

public class AverageScoreVisitor implements Visitor{
    @Override
    public void visit(Child child) {

        if (child.getCategory().equals("Baby")) {
            child.setAvgScore(10d);
        }
        if (child.getCategory().equals("Kid")) {
            Double sum = 0d;
            for (Double x: child.getArrayNiceScore()) {
                sum += x;
            }
            sum = sum / child.getArrayNiceScore().size();
            child.setAvgScore(sum);
        }
        if (child.getCategory().equals("Teen")) {
            double sumOfScores = 0d;
            double sumOfIndexes = 0d;
            int index = 1;
            for (Double x: child.getArrayNiceScore()) {
                sumOfScores += x * index;
                sumOfIndexes += index;
                index += 1;
            }
            sumOfScores = sumOfScores / sumOfIndexes;
            child.setAvgScore(sumOfScores);
        }
    }
}
