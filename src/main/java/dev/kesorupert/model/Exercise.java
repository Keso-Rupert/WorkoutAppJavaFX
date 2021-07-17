package dev.kesorupert.model;

public class Exercise {

    String exerciseName;
    String exerciseCategory;

    public Exercise(){}

    public Exercise(String exerciseName, String exerciseCategory) {
        this.exerciseName = exerciseName;
        this.exerciseCategory = exerciseCategory;
    }

    public Exercise(String exerciseName) {
        this.exerciseName = exerciseName;
        this.exerciseCategory = "Other";
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseCategory() {
        return exerciseCategory;
    }

    public void setExerciseCategory(String exerciseCategory) {
        this.exerciseCategory = exerciseCategory;
    }
}
