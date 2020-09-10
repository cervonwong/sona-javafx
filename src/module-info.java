module Sona {

    // ============================================================================================
    // EXPORTS
    // ============================================================================================

    exports main.java;
    exports main.java.presentation.model.structure.note;
    exports main.java.data.dto.types.element;
    exports main.java.data.dto.types.enums;
    exports main.java.data.dto.structure.card;
    exports main.java.data.dto.structure.deck;
    exports main.java.data.dto.structure.deck.enums;
    exports main.java.data.dto.structure.note;
    exports main.java.data.dto.types;
    exports main.java.presentation.model.structure.card;


    // ============================================================================================
    // OPENS (JAVAFX)
    // ============================================================================================

    opens main.java.presentation.controller.custom_nodes.main.navigation_rail;
    opens main.java.presentation.controller.custom_nodes.main.destinations.about;


    // ============================================================================================
    // DEPENDENCIES
    // ============================================================================================

    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;

    // Jackson
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    // Desktop
    requires java.desktop;
}