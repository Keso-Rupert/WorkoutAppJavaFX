package dev.kesorupert.service;

import dev.kesorupert.model.Exercise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.inject.Named;

@Named
public class ExerciseService {

    public static ObservableList<Exercise> exerciseList = FXCollections.observableArrayList(
            new Exercise("Push ups"),
            new Exercise("Pull ups"),
            new Exercise("Bench press"),
            new Exercise("Squat"));

    public ObservableList<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void addExercise(Exercise exercise) {
        exerciseList.add(exercise);
    }

}
