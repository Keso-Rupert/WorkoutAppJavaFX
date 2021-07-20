package dev.kesorupert.view;

import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.mvc.View;
import dev.kesorupert.UiResources;
import dev.kesorupert.WorkoutApplication;
import dev.kesorupert.model.ExerciseWrapper;
import dev.kesorupert.model.Workout;
import dev.kesorupert.model.WorkoutSelectionModel;
import dev.kesorupert.service.WorkoutService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class WorkoutPresenter extends GluonPresenter<WorkoutApplication> {

    @FXML
    private View workoutView;

    @Inject
    private WorkoutService workoutService;
    @Inject
    private WorkoutSelectionModel workoutSelectionModel;

    public void initialize() {
        workoutView.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                getApp().getAppBar().setVisible(false);
            }
        });

        TextField workoutNameTF = new TextField();
        workoutNameTF.setPromptText("Workout name");
        TextField workoutDescTF = new TextField();
        workoutDescTF.setPromptText("Notes");
        Button saveButton = new Button("Save workout!");
        saveButton.setOnAction(event -> {
            String workoutName = workoutNameTF.getCharacters().toString();
            String workoutDesc = workoutDescTF.getCharacters().toString();
            if(workoutName != null && !workoutName.isEmpty() && workoutDesc != null && !workoutDesc.isEmpty()) {
                workoutService.addWorkout(new Workout(workoutName, workoutDesc));
            } else {
                System.out.println("Empty workout");
            };});

        VBox vBox = new VBox(5, workoutNameTF, workoutDescTF, saveButton);

//        workoutView.setCenter(vBox);
        setUpWorkoutView();
        workoutView.setBottom(UiResources.createBottomNavigation());
    }

    private void setUpWorkoutView(){
        Workout workout = workoutSelectionModel.activeWorkout().get();
        VBox workoutVbox;
        HBox workoutName = new HBox(5, new Label("Name: "), new Label(workout.getWorkoutName()));
        workoutVbox = new VBox(5, workoutName, new Label("Notes: "), new Label(workout.getWorkoutDesc()));

        for(ExerciseWrapper wrapper : workout.getExerciseWrapperList()) {
            VBox exerciseVbox = new VBox(5, new Label(wrapper.getExercise().getExerciseName()), new Label(wrapper.getWeightsAndReps()));
            workoutVbox.getChildren().add(exerciseVbox);
        }
        workoutView.setCenter(workoutVbox);
    }

}
