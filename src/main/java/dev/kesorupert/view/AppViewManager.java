package dev.kesorupert.view;

import com.gluonhq.charm.glisten.afterburner.AppView;
import com.gluonhq.charm.glisten.afterburner.AppViewRegistry;
import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.afterburner.Utils;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import dev.kesorupert.WorkoutApplication;
import javafx.scene.image.Image;
import java.util.Locale;

import static com.gluonhq.charm.glisten.afterburner.AppView.Flag.HOME_VIEW;
import static com.gluonhq.charm.glisten.afterburner.AppView.Flag.SHOW_IN_DRAWER;
import static com.gluonhq.charm.glisten.afterburner.AppView.Flag.SKIP_VIEW_STACK;

public class AppViewManager {

    public static final AppViewRegistry REGISTRY = new AppViewRegistry();

    public static final AppView LOG_VIEW = view("Workout history", LogPresenter.class, MaterialDesignIcon.LIST, HOME_VIEW, SKIP_VIEW_STACK);
    public static final AppView ROUTINES_VIEW = view("Routines", RoutinesPresenter.class, MaterialDesignIcon.VIEW_LIST);
    public static final AppView EXERCISES_VIEW = view("Exercises", ExercisesPresenter.class, MaterialDesignIcon.ROWING);
    public static final AppView NEW_WORKOUT_VIEW = view("New workout", NewWorkoutPresenter.class, MaterialDesignIcon.PLAYLIST_ADD);
    public static final AppView NEW_EXERCISE_VIEW = view("New exercise", NewWorkoutPresenter.class, MaterialDesignIcon.PLAYLIST_ADD);

    private static AppView view(String title, Class<? extends GluonPresenter<?>> presenterClass, MaterialDesignIcon menuIcon, AppView.Flag... flags ) {
        return REGISTRY.createView(name(presenterClass), title, presenterClass, menuIcon, flags);
    }

    private static String name(Class<? extends GluonPresenter<?>> presenterClass) {
        return presenterClass.getSimpleName().toUpperCase(Locale.ROOT).replace("PRESENTER", "");
    }
    
    public static void registerViewsAndDrawer(MobileApplication app) {
        for (AppView view : REGISTRY.getViews()) {
            view.registerView(app);
        }

        NavigationDrawer.Header header = new NavigationDrawer.Header("Workout app",
                "by Keso Rupert",
                new Avatar(25, new Image(WorkoutApplication.class.getResourceAsStream("/easy.png"))));

        Utils.buildDrawer(app.getDrawer(), header, REGISTRY.getViews()); 
    }
}
