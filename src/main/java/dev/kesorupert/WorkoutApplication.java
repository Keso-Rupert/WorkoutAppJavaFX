package dev.kesorupert;

import com.gluonhq.charm.glisten.visual.Theme;
import dev.kesorupert.views.DrawerManager;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class WorkoutApplication extends MobileApplication {

    @Override
    public void init() {
        DrawerManager.registerViewsAndDrawer(this);
    }

    @Override
    public void postInit(Scene scene) {
        Swatch.BLUE_GREY.assignTo(scene);
//        Theme.DARK.assignTo(scene);
//        Theme.LIGHT.assignTo(scene);
        scene.getStylesheets().forEach(System.out::println);



        scene.getStylesheets().add(WorkoutApplication.class.getResource("style.css").toExternalForm());
        ((Stage) scene.getWindow()).getIcons().add(new Image(WorkoutApplication.class.getResourceAsStream("/easy.png")));
    }

    public static void main(String args[]) {
        launch(args);
    }
}
