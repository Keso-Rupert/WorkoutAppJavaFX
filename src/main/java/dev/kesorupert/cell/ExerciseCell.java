package dev.kesorupert.cell;

import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import dev.kesorupert.model.Exercise;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.function.Consumer;

public class ExerciseCell extends CharmListCell<Exercise> {
    private final ListTile listTile;
    private Exercise currentExerciseItem;

    public ExerciseCell(Consumer<Exercise> edit){
        listTile = new ListTile();

        Button editButton = MaterialDesignIcon.EDIT.button();
        editButton.setStyle("-fx-background-color:Darkgray; ");
        editButton.setOnAction(e -> edit.accept(currentExerciseItem));
        HBox buttonBar = new HBox(editButton);
        buttonBar.setAlignment(Pos.CENTER_RIGHT);

        listTile.setSecondaryGraphic(buttonBar);
    }

    @Override
    public void updateItem(Exercise item, boolean empty) {
        super.updateItem(item, empty);
        currentExerciseItem = item;
        if (!empty && item != null) {
            listTile.textProperty().setAll(currentExerciseItem.getExerciseName(), currentExerciseItem.getExerciseCategory());
            setGraphic(listTile);
        } else {
            setGraphic(null);
        }
    }
}
