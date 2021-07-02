package dev.kesorupert.views;

import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.mvc.View;
import dev.kesorupert.UiResources;
import dev.kesorupert.WorkoutApplication;
import javafx.fxml.FXML;

public class ExercisesPresenter extends GluonPresenter<WorkoutApplication> {

    @FXML
    private View exercisesView;

    public void initialize() {
        exercisesView.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                getApp().getAppBar().setVisible(false);
            }
        });

        exercisesView.setBottom(UiResources.createBottomNavigation());
    }

}
