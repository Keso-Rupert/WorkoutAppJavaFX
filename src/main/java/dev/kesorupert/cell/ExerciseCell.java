package dev.kesorupert.cell;

import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import dev.kesorupert.model.Exercise;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ExerciseCell extends CharmListCell<Exercise> {
    private final ListTile listTile;
    private Exercise currentExerciseItem;

    public ExerciseCell(){
        listTile = new ListTile();

        Button editButton = MaterialDesignIcon.EDIT.button();
        editButton.setStyle("-fx-background-color:Darkgray; ");
        HBox buttonBar = new HBox(editButton);
        buttonBar.setAlignment(Pos.CENTER_RIGHT);

        listTile.setSecondaryGraphic(buttonBar);
    }

    @Override
    public void updateItem(Exercise item, boolean empty) {
        super.updateItem(item, empty);
        currentExerciseItem = item;
        if (!empty && item != null) {
            listTile.textProperty().setAll(currentExerciseItem.getExerciseName());
            setGraphic(listTile);
        } else {
            setGraphic(null);
        }
    }
}
