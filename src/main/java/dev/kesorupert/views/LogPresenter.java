package dev.kesorupert.views;

import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.mvc.View;
import dev.kesorupert.UiResources;
import dev.kesorupert.WorkoutApplication;

import dev.kesorupert.model.Workout;
import dev.kesorupert.service.WorkoutService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LogPresenter extends GluonPresenter<WorkoutApplication> {

    @FXML
    private View logView;

    private CharmListView<Workout, LocalDateTime> charmListView;

    @Inject
    private WorkoutService workoutService;


    public void initialize() {
        logView.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                getApp().getAppBar().setVisible(false);
            }
        });

        charmListView = new CharmListView<>(workoutService.getWorkoutList());
        charmListView.setHeadersFunction(workout -> workout.getCreationDate());
        charmListView.setCellFactory(cell -> new WorkoutCell());
        charmListView.setHeaderCellFactory(cell -> new HeaderCell());
        charmListView.setPlaceholder(new Label("There are no workouts yet"));


        logView.setCenter(charmListView);
        logView.setBottom(UiResources.createBottomNavigation());
    }

}
