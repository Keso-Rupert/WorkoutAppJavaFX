package dev.kesorupert.service;

import dev.kesorupert.model.Workout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class WorkoutService {

    public static ObservableList<Workout> workoutList = FXCollections.observableArrayList(
            new Workout("Lowerbody", "another one in the books"),
            new Workout("Lowerbody2", "another one in the books"),
            new Workout("Lowerbody3", "another one in the books"),
            new Workout("Lowerbody4", "another one in the books"));

    public ObservableList<Workout> getWorkoutList() {
        return workoutList;
    }

    public void addWorkout(Workout workout) {
        workoutList.add(workout);
    }

}
