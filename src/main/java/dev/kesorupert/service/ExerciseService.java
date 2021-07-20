package dev.kesorupert.service;

import com.gluonhq.cloudlink.client.data.DataClient;
import com.gluonhq.cloudlink.client.data.DataClientBuilder;
import com.gluonhq.cloudlink.client.data.OperationMode;
import com.gluonhq.cloudlink.client.data.SyncFlag;
import com.gluonhq.connect.ConnectState;
import com.gluonhq.connect.GluonObservableList;
import com.gluonhq.connect.provider.DataProvider;
import dev.kesorupert.model.Exercise;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import javax.annotation.PostConstruct;

public class ExerciseService {

    // Identifier for the DataClient
    private static final String EXERCISES = "exercises-v2";

    // Wrapper of an observable list of exercises (gluonExercises)
    private final ListProperty<Exercise> exercises = new SimpleListProperty<>(FXCollections.observableArrayList());

    private DataClient dataClient;

    @PostConstruct
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
        GluonObservableList<Exercise> gluonExercises = DataProvider.retrieveList(
                dataClient.createListDataReader(EXERCISES, Exercise.class,
                        SyncFlag.LIST_WRITE_THROUGH, SyncFlag.OBJECT_WRITE_THROUGH));

        gluonExercises.stateProperty().addListener((observable, oldValue, newValue) -> {
            if (ConnectState.SUCCEEDED.equals(newValue)) {
                exercises.set(gluonExercises);
            }
        });
    }

    public Exercise addExercise(Exercise exercise){
        exercises.get().add(exercise);
        return exercise;
    }

    public void removeExercise(Exercise exercise){
        exercises.get().remove(exercise);
    }

    public ListProperty<Exercise> exercisesListProperty(){
        return  exercises;
    }

}
