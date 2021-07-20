package dev.kesorupert.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Exercise {

    StringProperty exerciseName = new SimpleStringProperty();
    StringProperty exerciseCategory = new SimpleStringProperty();

    public Exercise(){}

    public Exercise(String exerciseName, String exerciseCategory) {
        this.exerciseName.set(exerciseName);
        this.exerciseCategory.set(exerciseCategory);
    }

    public Exercise(String exerciseName) {
        this.exerciseName.set(exerciseName);
        this.exerciseCategory.set("Other");
    }

    public String getExerciseName() {
        return exerciseName.get();
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName.set(exerciseName);
    }

    public String getExerciseCategory() {
        return exerciseCategory.get();
    }

    public void setExerciseCategory(String exerciseCategory) {
        this.exerciseCategory.set(exerciseCategory);
    }
}
