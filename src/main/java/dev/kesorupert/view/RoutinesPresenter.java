package dev.kesorupert.view;

import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.mvc.View;
import dev.kesorupert.UiResources;
import dev.kesorupert.WorkoutApplication;
import javafx.fxml.FXML;

public class RoutinesPresenter extends GluonPresenter<WorkoutApplication> {

    @FXML
    private View routinesView;

    public void initialize() {
        routinesView.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                getApp().getAppBar().setVisible(false);
            }
        });

        routinesView.setBottom(UiResources.createBottomNavigation());
    }

}
