module Sona {

    // Exports
    exports main.java;
    exports main.java.model.structure.note;


    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;

    // Jackson
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
}