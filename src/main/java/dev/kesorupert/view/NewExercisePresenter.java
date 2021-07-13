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
import javafx.scene.layout.VBox;

import javax.inject.Inject;

public class NewExercisePresenter extends GluonPresenter<WorkoutApplication> {

    @FXML
    private View newExerciseView;

    @Inject
    private WorkoutService exerciseService;

    public void initialize() {
        newExerciseView.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                getApp().getAppBar().setVisible(false);
            }
        });

        TextField exerciseNameTF = new TextField();
        TextField exerciseCategoryTF = new TextField();

        Button saveButton = new Button("Save!");
        saveButton.setOnAction(event -> {
            System.out.println("Save button pressed");
            /*String workoutName = exerciseNameTF.getCharacters().toString();
            String workoutDesc = exerciseCategoryTF.getCharacters().toString();
            if(workoutName != null && !workoutName.isEmpty() && workoutDesc != null && !workoutDesc.isEmpty()) {
                workoutService.addWorkout(new Workout(workoutName, workoutDesc));
            } else {
                System.out.println("Empty workout");
            };*/
        });

        VBox vBox = new VBox(exerciseNameTF, exerciseCategoryTF, saveButton);

        newExerciseView.setCenter(vBox);
        // newExerciseView.setBottom(UiResources.createBottomNavigation());
    }

}
