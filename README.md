# Workout tracking application - Gluon Mobile

This is a application that allows you to track your workouts. Its built using Gluon Mobile and JavaFX.
The application uses Glisten Afterburner framework, built on top of [afterburner.fx](http://afterburner.adam-bien.com/), to inject
models and services into Presenter.

### Views
There are 3 main views reachable from the BottomNavigationBar: the log view (history of workouts), the exercises view (where a list of exercises can be managed), and a view where you can add a new workout. From the exercises view you can also move to the "new exercise" view, where exercises are added,edited or deleted.

### Persistence
I've built in persistence in the ExerciseService. I used Gluons DataClient, with which you can use Gluon Cloudlink to store data in the cloud. However, I used the local configuration, to store the data on the device itself. 

## Pre-requisites
Built using:
* Java 16
* JavaFX 16
* Maven

Please checkout the prerequisites to run this application [here](https://github.com/gluonhq/gluonfx-maven-plugin#requirements).

## Instructions

These application can run on the JVM on desktop platforms. To run the application, execute the following command:

```
mvn gluonfx:run
```

The same application can also run natively for on any targeted OS, including Android, iOS, Linux, Mac and Windows.

To create a native image, execute the following command:

```
mvn gluonfx:build gluonfx:nativerun
```

> **Note**: The above commands are target-platform dependent and might change depending on the platform.
For more details, please check
    [GluonFX Maven Goals](https://github.com/gluonhq/gluonfx-maven-plugin#2-goals).
