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

                // Disable the save button
                saveButton.disableProperty().unbind();
                // Try to get a selected exercise from the model
                Exercise activeExercise = exerciseModel.activeExercise().get();
                // If it is not null, a exercise was selected to be edited
                if (activeExercise != null) {
                    editMode = true;
                    saveButton.setText("Apply");
                    exerciseNameTF.setText(activeExercise.getExerciseName());
                    exerciseCategoryTF.setText(activeExercise.getExerciseCategory());

                    // The save button remains disabled...
                    saveButton.disableProperty().bind(Bindings.createBooleanBinding(() -> {
                        // ...till both the name and category are not null
                        if (exerciseNameTF == null || exerciseCategoryTF == null) {
                            return true;
                        }
                        // ...and while there is no change to the name or category values
                        return exerciseNameTF.textProperty().isEqualTo(activeExercise.getExerciseName()).and(exerciseCategoryTF.textProperty().isEqualTo(activeExercise.getExerciseCategory())).get();
                    }, exerciseNameTF.textProperty(), exerciseCategoryTF.textProperty())); // These last two paramaters are the properties to which the savebutton is synchronized by the binding API.
                } else { // if it is null, it involves the creation of a new exercise
                    editMode = false;
                    saveButton.setText("Submit");
                    saveButton.disableProperty().bind(Bindings.createBooleanBinding(() -> {
                        return exerciseNameTF.textProperty().isEmpty().or(exerciseCategoryTF.textProperty().isEmpty()).get();
                    }, exerciseNameTF.textProperty(), exerciseCategoryTF.textProperty()));
                }
            } else {
                exerciseNameTF.clear();
                exerciseCategoryTF.clear();
            }
        });

        saveButton.setOnAction(event -> {
            System.out.println("Save button pressed");
            // If we're in editMode, retrieve the to be saved exercise from the model, otherwise create a new exercise
            Exercise exercise = editMode ? exerciseModel.activeExercise().get() : new Exercise();
            exercise.setExerciseName(exerciseNameTF.getText());
            exercise.setExerciseCategory(exerciseCategoryTF.getText());
            if(!editMode){
                // If it is a new Exercise object, it needs to be added to the Observable collection in the ExerciseService
                exerciseService.addExercise(exercise);
            }
            close();
        });
        cancelButton.setOnAction(event -> close());

        HBox buttonHBox = new HBox(5, saveButton, cancelButton);
        VBox vBox = new VBox(5, exerciseNameTF, exerciseCategoryTF, buttonHBox);

        newExerciseView.setCenter(vBox);
    }

    public void close(){
        exerciseNameTF.clear();
        exerciseCategoryTF.clear();
        exerciseModel.activeExercise().set(null);
        AppViewManager.EXERCISES_VIEW.switchView();
    }

}
