package dev.kesorupert.view;

import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.mvc.View;
import dev.kesorupert.UiResources;
import dev.kesorupert.WorkoutApplication;
import dev.kesorupert.model.Workout;
import dev.kesorupert.service.WorkoutService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.inject.Inject;

public class NewWorkoutPresenter extends GluonPresenter<WorkoutApplication> {

    @FXML
    private View newWorkoutView;

    @Inject
    private WorkoutService workoutService;

    public void initialize() {
        newWorkoutView.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                getApp().getAppBar().setVisible(false);
            }
        });

        TextField workoutNameTF = new TextField();
        workoutNameTF.setPromptText("Workout name");
        TextField workoutDescTF = new TextField();
        workoutDescTF.setPromptText("Notes");
        Button saveButton = new Button("Save workout!");
        Button addExerciseButton = new Button("Add Exercise");
        saveButton.setOnAction(event -> {
            String workoutName = workoutNameTF.getCharacters().toString();
            String workoutDesc = workoutDescTF.getCharacters().toString();
            if(workoutName != null && !workoutName.isEmpty() && workoutDesc != null && !workoutDesc.isEmpty()) {
                workoutService.addWorkout(new Workout(workoutName, workoutDesc));
            } else {
                System.out.println("Empty workout");
            }
        });

        VBox vBox = new VBox(5, workoutNameTF, workoutDescTF, new HBox(5, addExerciseButton, saveButton));

        addExerciseButton.setOnAction(event -> {
            vBox.getChildren().add(new TextField("Exercise name"));
            vBox.getChildren().add(new TextField("Sets and reps"));
        });

        newWorkoutView.setCenter(vBox);
        newWorkoutView.setBottom(UiResources.createBottomNavigation());
    }

}
