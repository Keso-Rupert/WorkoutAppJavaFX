package dev.kesorupert;

import com.gluonhq.charm.glisten.control.BottomNavigation;
import com.gluonhq.charm.glisten.control.BottomNavigationButton;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import dev.kesorupert.view.AppViewManager;

public class UiResources {

    public static BottomNavigation createBottomNavigation() {
        BottomNavigation bottomNavigation = new BottomNavigation();
        BottomNavigationButton logButton = new BottomNavigationButton("Log", MaterialDesignIcon.LIST.graphic(), event -> AppViewManager.LOG_VIEW.switchView());
//        BottomNavigationButton routinesButton = new BottomNavigationButton("Routines", MaterialDesignIcon.VIEW_LIST.graphic(), event -> AppViewManager.ROUTINES_VIEW.switchView());
        BottomNavigationButton exercisesButton = new BottomNavigationButton("Exercises", MaterialDesignIcon.ROWING.graphic(), event -> AppViewManager.EXERCISES_VIEW.switchView());
        BottomNavigationButton workoutButton = new BottomNavigationButton("Start workout", MaterialDesignIcon.ADD.graphic(), event -> AppViewManager.NEW_WORKOUT_VIEW.switchView());

        bottomNavigation.getActionItems().addAll(logButton, /*routinesButton,*/ exercisesButton, workoutButton);
        return bottomNavigation;
    }
}
