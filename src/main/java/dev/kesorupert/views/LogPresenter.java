package dev.kesorupert.views;

import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.mvc.View;
import dev.kesorupert.UiResources;
import dev.kesorupert.WorkoutApplication;

import javafx.fxml.FXML;

public class LogPresenter extends GluonPresenter<WorkoutApplication> {

    @FXML
    private View logView;


    public void initialize() {
        logView.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                getApp().getAppBar().setVisible(false);
            }
        });


        logView.setBottom(UiResources.createBottomNavigation());
    }

}
