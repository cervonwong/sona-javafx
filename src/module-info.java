module Sona {

    // ============================================================================================
    // EXPORTS
    // ============================================================================================

    exports main.java;
    exports main.java.presentation.model.structure.note;
    exports main.java.data.dto.types.element;
    exports main.java.data.dto.types.enums;
    exports main.java.presentation.model.structure.card;


    // ============================================================================================
    // OPENS (JAVAFX)
    // ============================================================================================

    opens main.java.presentation.controller.custom_nodes.tabs.global.navigation_rail;


    // ============================================================================================
    // DEPENDENCIES
    // ============================================================================================

    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;

    // Jackson
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
}