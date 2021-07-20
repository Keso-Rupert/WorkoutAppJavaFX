package dev.kesorupert.service;

import com.gluonhq.cloudlink.client.data.DataClient;
import com.gluonhq.cloudlink.client.data.DataClientBuilder;
import com.gluonhq.cloudlink.client.data.OperationMode;
import com.gluonhq.cloudlink.client.data.SyncFlag;
import com.gluonhq.connect.ConnectState;
import com.gluonhq.connect.GluonObservableList;
import com.gluonhq.connect.provider.DataProvider;
import dev.kesorupert.model.Exercise;
import dev.kesorupert.model.ExerciseWrapper;
import dev.kesorupert.model.Workout;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;


public class WorkoutService {

    // Identifier for the DataClient
    private static final String WORKOUTS = "workouts-v1";

    // Wrapper of an observable list of workouts (gluonWorkouts)
    private final ListProperty<Workout> workouts = new SimpleListProperty<>(FXCollections.observableArrayList());

    private DataClient dataClient;

    private static Workout workout1, workout2, workout3, workout4, workout5;
    private static Exercise exercise1, exercise2, exercise3, exercise4;
    private static ExerciseWrapper exerciseWrapper1, exerciseWrapper2, exerciseWrapper3, exerciseWrapper4;

    static {
        workout1 = new Workout("LowerBody", "another one in the books");
        workout2 = new Workout("UpperBody", "Good session");
        workout3 = new Workout("Push", "Whatever");
        workout4 = new Workout("Legs", "Felt so tired");
        workout5 = new Workout("Pull", "Alright");

        workout3.setCreationDate(LocalDateTime.of(2021,06,29,19,00));
        workout5.setCreationDate(LocalDateTime.of(2021,05,29,19,00));

        exercise1 = new Exercise("Bench Press", "Chest");
        exercise2 = new Exercise("Pull up", "Back");
        exercise3 = new Exercise("Squat", "Legs");
        exercise4 = new Exercise("Overhead Press", "Shoulders");

        exerciseWrapper1 = new ExerciseWrapper(exercise1, "5 x 50 \n 5 x 60 \n 4 x 70");
        exerciseWrapper2 = new ExerciseWrapper(exercise2, "5 x 50 \n 5 x 60 \n 4 x 70");
        exerciseWrapper3 = new ExerciseWrapper(exercise3, "5 x 50 \n 5 x 60 \n 4 x 70");
        exerciseWrapper4 = new ExerciseWrapper(exercise4, "5 x 50 \n 5 x 60 \n 4 x 70");

        workout1.setExerciseWrapperList(List.of(exerciseWrapper1, exerciseWrapper2, exerciseWrapper3, exerciseWrapper4));
        workout2.setExerciseWrapperList(List.of(exerciseWrapper1, exerciseWrapper2, exerciseWrapper3, exerciseWrapper4));
        workout3.setExerciseWrapperList(List.of(exerciseWrapper1, exerciseWrapper2, exerciseWrapper3, exerciseWrapper4));
        workout4.setExerciseWrapperList(List.of(exerciseWrapper1, exerciseWrapper2, exerciseWrapper3, exerciseWrapper4));
        workout5.setExerciseWrapperList(List.of(exerciseWrapper1, exerciseWrapper2, exerciseWrapper3, exerciseWrapper4));

    }

    public static ObservableList<Workout> workoutList = FXCollections.observableArrayList(
            workout1, workout2, workout3, workout4, workout5);

    public ObservableList<Workout> getWorkoutList() {
        return workoutList;
    }

    public void addWorkout(Workout workout) {
        workoutList.add(workout);
    }

//    @PostConstruct
    public void postConstruct() {
        // Use OperationMode.LOCAL_ONLY to indicate that all data operations will be exclusively local.
        // To use CLOUD you need a CloudLink account
        dataClient = DataClientBuilder.create()
                .operationMode(OperationMode.LOCAL_ONLY)
                .build();
    }

    public void retrieveExercises() {
        // SyncFlag.LIST_WRITE_THROUGH, so changes in the list are automatically stored locally
        // SyncFlag.OBJECT_WRITE_THROUGH, so changes in the properties of the list are also stored locally
        GluonObservableList<Workout> gluonWorkouts = DataProvider.retrieveList(
                dataClient.createListDataReader(WORKOUTS, Workout.class,
                        SyncFlag.LIST_WRITE_THROUGH, SyncFlag.OBJECT_WRITE_THROUGH));

        gluonWorkouts.stateProperty().addListener((observable, oldValue, newValue) -> {
            if (ConnectState.SUCCEEDED.equals(newValue)) {
                workouts.set(gluonWorkouts);
            }
        });
    }

/*    public Workout addWorkout(Workout workout){
        workouts.get().add(workout);
        return workout;
    }*/

    public void removeWorkout(Workout workout){
        workouts.get().remove(workout);
    }

    public ListProperty<Workout> workoutsListProperty(){
        return workouts;
    }

}
