package dev.kesorupert.view;

import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.mvc.View;
import dev.kesorupert.UiResources;
import dev.kesorupert.WorkoutApplication;
import dev.kesorupert.cell.ExerciseCell;
import dev.kesorupert.cell.WorkoutCell;
import dev.kesorupert.cell.WorkoutHeaderCell;
import dev.kesorupert.model.Exercise;
import dev.kesorupert.model.Workout;
import dev.kesorupert.service.ExerciseService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.inject.Inject;
import java.util.Comparator;

public class ExercisesPresenter extends GluonPresenter<WorkoutApplication> {

    @FXML
    private View exercisesView;

    @Inject
    private ExerciseService exerciseService;

    private CharmListView<Exercise, Integer> charmListView;

    public void initialize() {
        exercisesView.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                getApp().getAppBar().setVisible(false);
            }
        });

        // Creating a CharmListView from a ObservableList
        charmListView = new CharmListView<>(exerciseService.getExerciseList());
        // charmListView.setHeaderCellFactory(cell -> new WorkoutHeaderCell());
        // Setting the CellFactory for the cells containing exercises
        charmListView.setCellFactory(cell -> new ExerciseCell());

        charmListView.setPlaceholder(new Label("There are no exercises yet"));

        exercisesView.setCenter(charmListView);
        exercisesView.setBottom(UiResources.createBottomNavigation());
    }

}
