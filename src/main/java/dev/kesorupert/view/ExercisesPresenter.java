package dev.kesorupert.view;

import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.mvc.View;
import dev.kesorupert.UiResources;
import dev.kesorupert.WorkoutApplication;
import dev.kesorupert.cell.ExerciseCell;
import dev.kesorupert.cell.ExerciseHeaderCell;
import dev.kesorupert.model.Exercise;
import dev.kesorupert.model.ExerciseSelectionModel;
import dev.kesorupert.service.ExerciseService;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;

public class ExercisesPresenter extends GluonPresenter<WorkoutApplication> {

    @FXML
    private View exercisesView;

    @Inject
    private ExerciseService exerciseService;
    @Inject
    private ExerciseSelectionModel exerciseSelectionModel;

    private CharmListView<Exercise, String> charmListView = new CharmListView<>();

    public void initialize() {
        exercisesView.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                getApp().getAppBar().setVisible(false);
            }
        });

        Button addExerciseButton = new Button("Add new exercise");
        addExerciseButton.setOnAction(event -> AppViewManager.NEW_EXERCISE_VIEW.switchView());

        exerciseService.exercisesListProperty().addListener((ListChangeListener.Change<? extends Exercise> c) -> {
            ObservableList<Exercise> exercises = FXCollections.observableArrayList(new ArrayList<Exercise>(c.getList()));
            charmListView.setItems(exercises);
        });

        // Setting the subheader properties for the CharmListView
        charmListView.setHeadersFunction(Exercise::getExerciseCategory);
        charmListView.setHeaderCellFactory(cell -> new ExerciseHeaderCell());
        // Setting the CellFactory for the cells containing exercises
        charmListView.setCellFactory(cell -> new ExerciseCell(this::editExercise));
        charmListView.setComparator(Comparator.comparing(Exercise::getExerciseName));

        charmListView.setPlaceholder(new Label("There are no exercises yet"));

        exerciseService.retrieveExercises();

        VBox vBox = new VBox(addExerciseButton, charmListView);
        exercisesView.setCenter(vBox);
        exercisesView.setBottom(UiResources.createBottomNavigation());


    }

    private void editExercise(Exercise exercise) {
        exerciseSelectionModel.activeExercise().set(exercise);
        AppViewManager.NEW_EXERCISE_VIEW.switchView();
    }

    private void remove(Exercise exercise) {
        exerciseService.removeExercise(exercise);
    }

}
