package dev.kesorupert.views;

import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.control.*;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import dev.kesorupert.WorkoutApplication;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PrimaryPresenter extends GluonPresenter<WorkoutApplication> {

    @FXML
    private View primary;


    public void initialize() {
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = getApp().getAppBar();
                appBar.setVisible(false);
            }
        });


        primary.setBottom(createBottomNavigation());
        primary.setTop(createAppBar());
    }

    public AppBar createAppBar(){
        AppBar appBar = new AppBar();
        appBar.setTitleText("Workout App");
        appBar.getActionItems().add(MaterialDesignIcon.MORE_VERT.button(event -> System.out.println("Show settings")));
        return appBar;
    }

    public BottomNavigation createBottomNavigation() {
        BottomNavigation bottomNavigation = new BottomNavigation();
        BottomNavigationButton logButton = new BottomNavigationButton("Log", MaterialDesignIcon.LIST.graphic(), event -> System.out.println("Go to home/log view"));
        BottomNavigationButton routinesButton = new BottomNavigationButton("Routines", MaterialDesignIcon.VIEW_LIST.graphic(), event -> System.out.println("Go to routines view"));
        BottomNavigationButton exercisesButton = new BottomNavigationButton("Exercises", MaterialDesignIcon.ROWING.graphic(), event -> System.out.println("Go to exercises view"));
        BottomNavigationButton workoutButton = new BottomNavigationButton("Start workout", MaterialDesignIcon.ADD.graphic(), event -> System.out.println("Go to new workout view"));

        bottomNavigation.getActionItems().addAll(logButton, routinesButton, exercisesButton, workoutButton);
        return bottomNavigation;
    }

}
