package dev.kesorupert.views;

import com.gluonhq.charm.glisten.control.CharmListCell;
import dev.kesorupert.model.Workout;
import javafx.scene.control.Label;

import java.time.format.DateTimeFormatter;

public class HeaderCell extends CharmListCell<Workout> {

    private final Label label;
    private Workout currentWorkoutItem;
    private final DateTimeFormatter dateTimeFormatter;

    public HeaderCell(){
        label = new Label();
        dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM");
    }

    @Override
    public void updateItem(Workout item, boolean empty) {
        super.updateItem(item, empty);
        currentWorkoutItem = item;
        if (!empty && item != null) {
            // setting the label with the month of the workout
            label.setText(dateTimeFormatter.format(currentWorkoutItem.getCreationDate()));
            setGraphic(label);
        } else {
            setGraphic(null);
        }
    }
}
