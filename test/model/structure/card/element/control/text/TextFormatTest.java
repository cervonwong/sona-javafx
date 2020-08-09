package model.structure.card.element.control.text;

import main.java.model.structure.card.element.control.text.TextFormat;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TextFormatTest {

    TextFormat textFormat;


    // INITIALISATION

    @BeforeEach
    void init() {
        textFormat = new TextFormat.TextFormatBuilder().build();
    }


    // TESTS

    @Nested
    @DisplayName("Tests for fontSizeFactor")
    class FontSizeFactorTest {

        @Test
        void withFontSizeFactor_WhenPassedLegalValues_shouldReturnTextFormat() {
            assertEquals(new TextFormat.TextFormatBuilder().fontSizeFactor(1.0).build(),
                         textFormat.withFontSizeFactor(1.0));

            assertEquals(new TextFormat.TextFormatBuilder().fontSizeFactor(200.0).build(),
                         textFormat.withFontSizeFactor(200.0));
        }

        @Test
        void withFontSizeFactor_WhenPassedValuesOutOfBounds_shouldThrowException() {
            final IllegalArgumentException illegalArgumentException = assertThrows(
                    IllegalArgumentException.class,
                    () -> textFormat.withFontSizeFactor(0.0)
            );

            assertEquals("Illegal fontSizeFactor (is lesser than 1.0): 0.0",
                         illegalArgumentException.getMessage());
        }
    }

}