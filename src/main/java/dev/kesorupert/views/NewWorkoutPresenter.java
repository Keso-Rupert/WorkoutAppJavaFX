package dev.kesorupert.views;

import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.mvc.View;
import dev.kesorupert.UiResources;
import dev.kesorupert.WorkoutApplication;
import javafx.fxml.FXML;

public class NewWorkoutPresenter extends GluonPresenter<WorkoutApplication> {

    @FXML
    private View newWorkoutView;

    public void initialize() {
        newWorkoutView.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                getApp().getAppBar().setVisible(false);
            }
        });

        newWorkoutView.setBottom(UiResources.createBottomNavigation());
    }

}
