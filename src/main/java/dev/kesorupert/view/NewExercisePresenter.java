package dev.kesorupert.view;

import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.mvc.View;
import dev.kesorupert.UiResources;
import dev.kesorupert.WorkoutApplication;
import dev.kesorupert.model.Exercise;
import dev.kesorupert.model.ExerciseModel;
import dev.kesorupert.model.Workout;
import dev.kesorupert.service.ExerciseService;
import dev.kesorupert.service.WorkoutService;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.inject.Inject;

public class NewExercisePresenter extends GluonPresenter<WorkoutApplication> {

    @FXML
    private View newExerciseView;

    @Inject
    private ExerciseService exerciseService;
    @Inject
    private ExerciseModel exerciseModel;

    Button saveButton = new Button();
    Button cancelButton = new Button("Cancel");
    TextField exerciseNameTF = new TextField();
    TextField exerciseCategoryTF = new TextField();

    private boolean editMode;

    public void initialize() {
        newExerciseView.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                getApp().getAppBar().setVisible(false);

                saveButton.disableProperty().unbind();
                Exercise activeExercise = exerciseModel.activeExercise().get();
                if (activeExercise != null) {
                    saveButton.setText("Apply");
                    exerciseNameTF.setText(activeExercise.getExerciseName());
                    exerciseCategoryTF.setText(activeExercise.getExerciseCategory());

                    saveButton.disableProperty().bind(Bindings.createBooleanBinding(() -> {
                        if (exerciseNameTF == null || exerciseCategoryTF == null) {
                            return true;
                        }
                        return exerciseNameTF.textProperty().isEqualTo(activeExercise.getExerciseName()).and(exerciseCategoryTF.textProperty().isEqualTo(activeExercise.getExerciseCategory())).get();
                    }, exerciseNameTF.textProperty(), exerciseCategoryTF.textProperty()));
                    editMode = true;
                } else {
                    saveButton.setText("Submit");
                    saveButton.disableProperty().bind(Bindings.createBooleanBinding(() -> {
                        return exerciseNameTF.textProperty().isEmpty().or(exerciseCategoryTF.textProperty().isEmpty()).get();
                    }, exerciseNameTF.textProperty(), exerciseCategoryTF.textProperty()));
                    editMode = false;
                }
            } else {
                exerciseNameTF.clear();
                exerciseCategoryTF.clear();
            }
        });

        saveButton.setOnAction(event -> {
            System.out.println("Save button pressed");
            Exercise exercise = editMode ? exerciseModel.activeExercise().get() : new Exercise();
            exercise.setExerciseName(exerciseNameTF.getText());
            exercise.setExerciseCategory(exerciseCategoryTF.getText());
            close();
        });
        cancelButton.setOnAction(event -> close());

        HBox buttonHBox = new HBox(5, saveButton, cancelButton);
        VBox vBox = new VBox(5, exerciseNameTF, exerciseCategoryTF, buttonHBox);

        newExerciseView.setCenter(vBox);
        // newExerciseView.setBottom(UiResources.createBottomNavigation());
    }

    public void close(){
        exerciseNameTF.clear();
        exerciseCategoryTF.clear();
        exerciseModel.activeExercise().set(null);
        AppViewManager.EXERCISES_VIEW.switchView();
    }

}
