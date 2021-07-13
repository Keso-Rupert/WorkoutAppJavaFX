package dev.kesorupert.view;

import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.mvc.View;
import dev.kesorupert.UiResources;
import dev.kesorupert.WorkoutApplication;

import dev.kesorupert.cell.WorkoutHeaderCell;
import dev.kesorupert.cell.WorkoutCell;
import dev.kesorupert.model.Workout;
import dev.kesorupert.service.WorkoutService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Comparator;

public class LogPresenter extends GluonPresenter<WorkoutApplication> {

    @FXML
    private View logView;

    private CharmListView<Workout, LocalDateTime> charmListView;

    @Inject
    private WorkoutService workoutService;

    public void initialize() {
        logView.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                // Setting the default AppBar in the GlassPane to be invisible
                getApp().getAppBar().setVisible(false);
            }
        });

        // Creating a CharmListView from a ObservableList
        charmListView = new CharmListView<>(workoutService.getWorkoutList());
        // Setting the subheader properties for the CharmListView
        charmListView.setHeadersFunction(Workout::getCreationDate);
        charmListView.setHeaderComparator(Comparator.reverseOrder());
        charmListView.setHeaderCellFactory(cell -> new WorkoutHeaderCell());
        // Setting the CellFactory for the cells containing workouts
        charmListView.setCellFactory(cell -> new WorkoutCell());
        charmListView.setComparator(Comparator.comparing(Workout::getCreationDate).reversed());

        charmListView.setPlaceholder(new Label("There are no workouts yet"));

        logView.setCenter(charmListView);
        logView.setBottom(UiResources.createBottomNavigation());
    }

}
