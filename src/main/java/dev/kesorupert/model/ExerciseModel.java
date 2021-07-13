package dev.kesorupert.model;

import dev.kesorupert.model.Exercise;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * We create a model class that keeps track of the selected exercise, if any, from the ExercisePresenter.
 * We'll use this to either create a new exercise or edit an existing one.
 */
public class ExerciseModel {
    private final ObjectProperty<Exercise> activeExercise = new SimpleObjectProperty<>();

    public ObjectProperty<Exercise> activeExercise() {
        return activeExercise;
    }

}
