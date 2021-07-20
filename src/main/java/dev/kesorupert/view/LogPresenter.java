package dev.kesorupert.view;

import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.mvc.View;
import dev.kesorupert.UiResources;
import dev.kesorupert.WorkoutApplication;

import dev.kesorupert.cell.WorkoutHeaderCell;
import dev.kesorupert.cell.WorkoutCell;
import dev.kesorupert.model.Workout;
import dev.kesorupert.model.WorkoutSelectionModel;
import dev.kesorupert.service.WorkoutService;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class LogPresenter extends GluonPresenter<WorkoutApplication> {

    @FXML
    private View logView;

    private CharmListView<Workout, LocalDateTime> charmListView = new CharmListView<>();

    @Inject
    private WorkoutService workoutService;
    @Inject
    private WorkoutSelectionModel workoutSelectionModel;

    public void initialize() {
        logView.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                // Setting the default AppBar in the GlassPane to be invisible
                getApp().getAppBar().setVisible(false);
            }
        });

       // Creating an ObservableList that updates when its contents are changed and then putting it in the CharmListView
       workoutService.workoutsListProperty().addListener((ListChangeListener.Change<? extends Workout> c) -> {
           ObservableList<Workout> workouts = FXCollections.observableArrayList(new ArrayList<Workout>(c.getList()));
           charmListView.setItems(workouts);
       });

       charmListView.setItems(workoutService.getWorkoutList());

        // Setting the subheader properties for the CharmListView
        charmListView.setHeadersFunction(Workout::getCreationDate);
        charmListView.setHeaderComparator(Comparator.reverseOrder());
        charmListView.setHeaderCellFactory(cell -> new WorkoutHeaderCell());
        // Setting the CellFactory for the cells containing workouts
        charmListView.setCellFactory(cell -> new WorkoutCell(this::viewWorkout));
        charmListView.setComparator(Comparator.comparing(Workout::getCreationDate).reversed());

        charmListView.setPlaceholder(new Label("There are no workouts yet"));

        logView.setCenter(charmListView);
        logView.setBottom(UiResources.createBottomNavigation());
    }

    private void editWorkout(Workout workout) {
        workoutSelectionModel.activeWorkout().set(workout);
        AppViewManager.NEW_WORKOUT_VIEW.switchView();
    }

    private void viewWorkout(Workout workout) {
        workoutSelectionModel.activeWorkout().set(workout);
        AppViewManager.WORKOUT_VIEW.switchView();
    }

    private void removeWorkout(Workout workout) {
        workoutService.removeWorkout(workout);
    }
}
