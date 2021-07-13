package dev.kesorupert.service;

import dev.kesorupert.model.Workout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.inject.Named;
import java.time.LocalDateTime;

@Named
public class WorkoutService {

    public ObservableList<Workout> getWorkoutList(){
        Workout workoutA = new Workout("Lowerbody", "another one in the books");
        workoutA.setCreationDate(LocalDateTime.of(2021,06,01,19,00));
        Workout workoutB = new Workout("Lowerbody2", "another one in the books");
        workoutB.setCreationDate(LocalDateTime.of(2021,06,01,19,00));
        Workout workoutC = new Workout("Lowerbody3", "another one in the books");
        workoutC.setCreationDate(LocalDateTime.of(2021,05,01,19,00));
        Workout workoutD = new Workout("Lowerbody4", "another one in the books");
        workoutD.setCreationDate(LocalDateTime.of(2021,05,01,19,00));

        return FXCollections.observableArrayList(
                workoutA, workoutB, workoutC, workoutD,
                new Workout("Upperbody1", "great session"),
                new Workout("Upperbody2", "great session"),
                new Workout("Upperbody3", "great session"),
                new Workout("Upperbody4", "great session"),
                new Workout("Upperbody5", "great session"),
                new Workout("FullBody1", "Very tiring session"),
                new Workout("FullBody3", "Very tiring session"),
                new Workout("FullBody4", "Very tiring session"),
                new Workout("FullBody5", "Very tiring session"),
                new Workout("FullBody6", "Very tiring session"));
    }

}
