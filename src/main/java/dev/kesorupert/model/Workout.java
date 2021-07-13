package dev.kesorupert.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Workout {

    private final StringProperty workoutName = new SimpleStringProperty();
    private final StringProperty workoutDesc = new SimpleStringProperty();
    private long creationDate;

    public Workout(){
        this.creationDate = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }

    public Workout(String workoutName, String workoutDesc){
        this.workoutName.set(workoutName);
        this.workoutDesc.set(workoutDesc);
        this.creationDate = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }

    public String getWorkoutName() {
        return workoutName.get();
    }

    public StringProperty workoutNameProperty() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName.set(workoutName);
    }

    public String getWorkoutDesc() {
        return workoutDesc.get();
    }

    public StringProperty workoutDescProperty() {
        return workoutDesc;
    }

    public void setWorkoutDesc(String workoutDesc) {
        this.workoutDesc.set(workoutDesc);
    }

    public LocalDateTime getCreationDate() {
        return LocalDateTime.ofEpochSecond(creationDate, 0, ZoneOffset.UTC);
    }

    public void setCreationDate(LocalDateTime localDateTime) {
        this.creationDate = localDateTime.toEpochSecond(ZoneOffset.UTC);
    }

}
