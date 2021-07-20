package dev.kesorupert.model;

public class ExerciseWrapper {
    private Exercise exercise;

    public ExerciseWrapper(Exercise exercise, String weightsAndReps) {
        this.exercise = exercise;
        this.weightsAndReps = weightsAndReps;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public String getWeightsAndReps() {
        return weightsAndReps;
    }

    public void setWeightsAndReps(String weightsAndReps) {
        this.weightsAndReps = weightsAndReps;
    }

    private String weightsAndReps;
//    private List<WeightAndReps> weightAndRepList;
}
