package model.structure.card.element.control.text;

import main.java.model.structure.card.element.control.text.TextFormat;
import main.java.model.structure.card.element.control.text.enums.ElementColor;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TextFormatTest {

    TextFormat textFormat;


    // INITIALISATION

    @BeforeEach
    void init() {
        textFormat = new TextFormat.TextFormatBuilder().build(); // Builds default
    }


    // TESTS

    @Nested
    @DisplayName("Tests for fontSizeFactor")
    class FontSizeFactorTest {
        @Test
        void withFontSizeFactor_WhenPassedLegalValues_ShouldReturnTextFormat() {
            assertEquals(new TextFormat.TextFormatBuilder().fontSizeFactor(1.0).build(),
                         textFormat.withFontSizeFactor(1.0));

            assertEquals(new TextFormat.TextFormatBuilder().fontSizeFactor(200.0).build(),
                         textFormat.withFontSizeFactor(200.0));
        }

        @Test
        void withFontSizeFactor_WhenPassedValuesOutOfBounds_ShouldThrowException() {
            assertThrows(IllegalArgumentException.class,
                         () -> textFormat.withFontSizeFactor(0.0));

            assertThrows(IllegalArgumentException.class,
                         () -> textFormat.withFontSizeFactor(200.01));

            assertThrows(IllegalArgumentException.class,
                         () -> textFormat.withFontSizeFactor(99999999));
        }
    }

    @Nested
    @DisplayName("Tests for isUnderlined")
    class IsUnderlinedTest {
        @Test
        void withIsUnderlined_WhenPassedAnyBoolean_ShouldReturnTextFormat() {
            assertEquals(new TextFormat.TextFormatBuilder().isUnderlined(true).build(),
                         textFormat.withIsUnderlined(true));

            assertEquals(new TextFormat.TextFormatBuilder().isUnderlined(false).build(),
                         textFormat.withIsUnderlined(false));
        }
    }

    @Nested
    @DisplayName("Tests for elementColor")
    class ElementColorTest {
        @Test
        void withElementColor_WhenPassedAnyElementColor_ShouldReturnTextFormat() {
            assertEquals(new TextFormat.TextFormatBuilder().elementColor(ElementColor.AMBER)
                                                           .build(),
                         textFormat.withElementColor(ElementColor.AMBER));

            assertEquals(new TextFormat.TextFormatBuilder().elementColor(ElementColor.GREEN)
                                                           .build(),
                         textFormat.withElementColor(ElementColor.GREEN));

            assertEquals(new TextFormat.TextFormatBuilder().elementColor(ElementColor.BLUE_GRAY)
                                                           .build(),
                         textFormat.withElementColor(ElementColor.BLUE_GRAY));
        }

        @Test
        void withElementColor_WhenPassedNull_ShouldThrowException() {
            assertThrows(IllegalArgumentException.class,
                         () -> textFormat.withElementColor(null));
        }
    }

    @Nested
    @DisplayName("Tests for font (fontFamily, hasSerif, fontWeight, isItalicized, isCondensed)")
    class FontTest {
        @Test
        void withIsItalics_WhenIsSupportedToSet_ShouldReturnTextFormatWithCorrectItalics() {

        }

        @Test
        void withFontFamily_WhenPassedAnyNonNullValue_ShouldReturnTextFormatWithSupportedFont() {

        }
    }
}