package dev.kesorupert.service;

import dev.kesorupert.model.Workout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.inject.Named;

@Named
public class WorkoutService {

    public ObservableList<Workout> getWorkoutList(){
        return FXCollections.observableArrayList(
                new Workout("Upperbody1", "great session"),
                new Workout("Upperbody2", "great session"),
                new Workout("Upperbody3", "great session"),
                new Workout("Upperbody4", "great session"),
                new Workout("Upperbody5", "great session"));
    }

}
