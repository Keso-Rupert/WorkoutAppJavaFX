package dev.kesorupert.cell;

import com.gluonhq.charm.glisten.control.CharmListCell;
import dev.kesorupert.model.Exercise;
import dev.kesorupert.model.Workout;
import javafx.scene.control.Label;

import java.time.format.DateTimeFormatter;

public class ExerciseHeaderCell extends CharmListCell<Exercise> {

    private final Label label;
    private Exercise currentExerciseItem;

    public ExerciseHeaderCell(){
        label = new Label();
    }

    @Override
    public void updateItem(Exercise item, boolean empty) {
        super.updateItem(item, empty);
        currentExerciseItem = item;
        if (!empty && item != null) {
            // setting the label with the month of the workout
            label.setText(currentExerciseItem.getExerciseCategory());
            setGraphic(label);
        } else {
            setGraphic(null);
        }
    }
}
