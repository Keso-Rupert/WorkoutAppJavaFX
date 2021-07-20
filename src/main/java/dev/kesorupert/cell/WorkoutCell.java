package dev.kesorupert.cell;

import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import dev.kesorupert.model.Workout;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

public class WorkoutCell extends CharmListCell<Workout> {
    private final ListTile listTile;
    private Workout currentWorkoutItem;
    private final DateTimeFormatter dateTimeFormatter;

    public WorkoutCell(Consumer<Workout> view){
        listTile = new ListTile();

        Button viewButton = MaterialDesignIcon.VIEW_QUILT.button();
        viewButton.setStyle("-fx-background-color:Darkgray; ");
        viewButton.setOnAction(e -> view.accept(currentWorkoutItem));
        HBox buttonBar = new HBox(viewButton);
        buttonBar.setAlignment(Pos.CENTER_RIGHT);

        listTile.setSecondaryGraphic(buttonBar);

        dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
    }

    @Override
    public void updateItem(Workout item, boolean empty) {
        super.updateItem(item, empty);
        currentWorkoutItem = item;
        if (!empty && item != null) {
            // Setting the listTile with properties from the workout item
            listTile.textProperty().setAll(currentWorkoutItem.getWorkoutName(),
                    currentWorkoutItem.getWorkoutDesc(),
                    dateTimeFormatter.format(currentWorkoutItem.getCreationDate()));
            setGraphic(listTile);
        } else {
            setGraphic(null);
        }
    }
}
