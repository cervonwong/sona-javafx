module Sona {

    // Exports
    exports main.java;
    exports main.java.presentation.model.structure.note;


    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;

    // Jackson
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
}