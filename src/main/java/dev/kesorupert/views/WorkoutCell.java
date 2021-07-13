package dev.kesorupert.views;

import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import dev.kesorupert.model.Workout;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.time.format.DateTimeFormatter;

public class WorkoutCell extends CharmListCell<Workout> {
    private final ListTile listTile;
    private Workout currentWorkoutItem;
    private final DateTimeFormatter dateTimeFormatter;

    public WorkoutCell(){
        listTile = new ListTile();
/*        listTile.setPrimaryGraphic(MaterialDesignIcon.DESCRIPTION.graphic());

        Button btnEdit = MaterialDesignIcon.EDIT.button();
        Button btnRemove = MaterialDesignIcon.DELETE.button();
        HBox buttonBar = new HBox(0, btnEdit, btnRemove);
        buttonBar.setAlignment(Pos.CENTER_RIGHT);

        listTile.setSecondaryGraphic(buttonBar);*/

        dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
    }

    @Override
    public void updateItem(Workout item, boolean empty) {
        super.updateItem(item, empty);
        currentWorkoutItem = item;
        if (!empty && item != null) {
            listTile.textProperty().setAll(currentWorkoutItem.getWorkoutName(),
                    currentWorkoutItem.getWorkoutDesc(),
                    dateTimeFormatter.format(currentWorkoutItem.getCreationDate()));
//            setText(null);
            setGraphic(listTile);
        } else {
//            setText(null);
            setGraphic(null);
        }
    }

    private void update() {
        if (currentWorkoutItem != null) {
            listTile.textProperty().setAll(currentWorkoutItem.getWorkoutName(),
                    currentWorkoutItem.getWorkoutDesc(),
                    dateTimeFormatter.format(currentWorkoutItem.getCreationDate()));
        } else {
            listTile.textProperty().clear();
        }
    }
}
