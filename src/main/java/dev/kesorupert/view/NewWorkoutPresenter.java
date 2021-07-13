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
        TextField workoutDescTF = new TextField();

        Button saveButton = new Button("Save!");
        saveButton.setOnAction(event -> {
            String workoutName = workoutNameTF.getCharacters().toString();
            String workoutDesc = workoutDescTF.getCharacters().toString();
            if(workoutName != null && !workoutName.isEmpty() && workoutDesc != null && !workoutDesc.isEmpty()) {
                workoutService.addWorkout(new Workout(workoutName, workoutDesc));
            } else {
                System.out.println("Empty workout");
            };});

        VBox vBox = new VBox(workoutNameTF, workoutDescTF, saveButton);

        newWorkoutView.setCenter(vBox);
        newWorkoutView.setBottom(UiResources.createBottomNavigation());
    }

}
