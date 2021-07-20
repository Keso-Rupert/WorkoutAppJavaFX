package dev.kesorupert.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * We create a model class that keeps track of the selected workout, if any, from the WorkoutPresenter.
 * We'll use this to either create a new workout or edit an existing one.
 */
public class WorkoutSelectionModel {
    private final ObjectProperty<Workout> activeWorkout = new SimpleObjectProperty<>();

    public ObjectProperty<Workout> activeWorkout() {
        return activeWorkout;
    }

}
