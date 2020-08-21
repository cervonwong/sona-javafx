package main.java.presentation.model.structure.card.element.control.text.enums;

public enum FontFamily {
    CHINESE_SIMPLIFIED(true,
                       true,
                       true,
                       true,
                       true,
                       true,
                       true,
                       true,
                       true,
                       true,
                       false,
                       false),
    CHINESE_TRADITIONAL(true,
                        true,
                        true,
                        true,
                        true,
                        true,
                        true,
                        true,
                        true,
                        true,
                        false,
                        false),
    CYRILLIC(true,
             true,
             true,
             true,
             true,
             true,
             true,
             true,
             true,
             true,
             true,
             true),
    DEVANAGARI(true,
               true,
               true,
               true,
               false,
               false,
               false,
               false,
               true,
               false,
               false,
               true),
    GEORGIAN(true,
             true,
             true,
             true,
             true,
             true,
             true,
             true,
             true,
             true,
             false,
             true),
    GREEK(true,
          true,
          true,
          true,
          true,
          true,
          true,
          true,
          true,
          true,
          true,
          true),
    JAPANESE(true,
             true,
             true,
             true,
             true,
             true,
             true,
             true,
             true,
             true,
             false,
             false),
    KOREAN(true,
           true,
           true,
           true,
           true,
           true,
           true,
           true,
           true,
           true,
           false,
           false),
    LATIN(true,
          true,
          true,
          true,
          true,
          true,
          true,
          true,
          true,
          true,
          true,
          true),
    MONO(true,
         true,
         true,
         true,
         false,
         false,
         false,
         false,
         true,
         false,
         false,
         true),
    TAMIL(true,
          true,
          true,
          true,
          true,
          true,
          true,
          true,
          true,
          true,
          false,
          true),
    THAI(true,
         true,
         true,
         true,
         true,
         true,
         true,
         true,
         true,
         true,
         false,
         true);


    // INSTANCE VARIABLES

    private final boolean SUPPORTS_SANS_SERIF_LIGHT_WEIGHT;

    private final boolean SUPPORTS_SANS_SERIF_NORMAL_WEIGHT;

    private final boolean SUPPORTS_SANS_SERIF_SEMI_BOLD_WEIGHT;

    private final boolean SUPPORTS_SANS_SERIF_BLACK_WEIGHT;

    private final boolean SUPPORTS_SERIF_LIGHT_WEIGHT;

    private final boolean SUPPORTS_SERIF_NORMAL_WEIGHT;

    private final boolean SUPPORTS_SERIF_SEMI_BOLD_WEIGHT;

    private final boolean SUPPORTS_SERIF_BLACK_WEIGHT;

    private final boolean SUPPORTS_SANS_SERIF;

    private final boolean SUPPORTS_SERIF;

    private final boolean SUPPORTS_ITALIC_STYLE;

    private final boolean SUPPORTS_CONDENSED_STYLE;


    // CONSTRUCTOR

    FontFamily(boolean SUPPORTS_SANS_SERIF_LIGHT_WEIGHT,
               boolean SUPPORTS_SANS_SERIF_NORMAL_WEIGHT,
               boolean SUPPORTS_SANS_SERIF_SEMI_BOLD_WEIGHT,
               boolean SUPPORTS_SANS_SERIF_BLACK_WEIGHT,
               boolean SUPPORTS_SERIF_LIGHT_WEIGHT,
               boolean SUPPORTS_SERIF_NORMAL_WEIGHT,
               boolean SUPPORTS_SERIF_SEMI_BOLD_WEIGHT,
               boolean SUPPORTS_SERIF_BLACK_WEIGHT,
               boolean SUPPORTS_SANS_SERIF,
               boolean SUPPORTS_SERIF,
               boolean SUPPORTS_ITALIC_STYLE,
               boolean SUPPORTS_CONDENSED_STYLE) {
        this.SUPPORTS_SANS_SERIF_LIGHT_WEIGHT = SUPPORTS_SANS_SERIF_LIGHT_WEIGHT;
        this.SUPPORTS_SANS_SERIF_NORMAL_WEIGHT = SUPPORTS_SANS_SERIF_NORMAL_WEIGHT;
        this.SUPPORTS_SANS_SERIF_SEMI_BOLD_WEIGHT = SUPPORTS_SANS_SERIF_SEMI_BOLD_WEIGHT;
        this.SUPPORTS_SANS_SERIF_BLACK_WEIGHT = SUPPORTS_SANS_SERIF_BLACK_WEIGHT;
        this.SUPPORTS_SERIF_LIGHT_WEIGHT = SUPPORTS_SERIF_LIGHT_WEIGHT;
        this.SUPPORTS_SERIF_NORMAL_WEIGHT = SUPPORTS_SERIF_NORMAL_WEIGHT;
        this.SUPPORTS_SERIF_SEMI_BOLD_WEIGHT = SUPPORTS_SERIF_SEMI_BOLD_WEIGHT;
        this.SUPPORTS_SERIF_BLACK_WEIGHT = SUPPORTS_SERIF_BLACK_WEIGHT;
        this.SUPPORTS_SANS_SERIF = SUPPORTS_SANS_SERIF;
        this.SUPPORTS_SERIF = SUPPORTS_SERIF;
        this.SUPPORTS_ITALIC_STYLE = SUPPORTS_ITALIC_STYLE;
        this.SUPPORTS_CONDENSED_STYLE = SUPPORTS_CONDENSED_STYLE;
    }


    // ACCESSORS

    public boolean supportsSansSerifLightWeight() {
        return SUPPORTS_SANS_SERIF_LIGHT_WEIGHT;
    }

    public boolean supportsSansSerifNormalWeight() {
        return SUPPORTS_SANS_SERIF_NORMAL_WEIGHT;
    }

    public boolean supportsSansSerifSemiBoldWeight() {
        return SUPPORTS_SANS_SERIF_SEMI_BOLD_WEIGHT;
    }

    public boolean supportsSansSerifBlackWeight() {
        return SUPPORTS_SANS_SERIF_BLACK_WEIGHT;
    }

    public boolean supportsSerifLightWeight() {
        return SUPPORTS_SERIF_LIGHT_WEIGHT;
    }

    public boolean supportsSerifNormalWeight() {
        return SUPPORTS_SERIF_NORMAL_WEIGHT;
    }

    public boolean supportsSerifSemiBoldWeight() {
        return SUPPORTS_SERIF_SEMI_BOLD_WEIGHT;
    }

    public boolean supportsSerifBlackWeight() {
        return SUPPORTS_SERIF_BLACK_WEIGHT;
    }

    public boolean supportsSansSerif() {
        return SUPPORTS_SANS_SERIF;
    }

    public boolean supportsSerif() {
        return SUPPORTS_SERIF;
    }

    public boolean supportsItalicStyle() {
        return SUPPORTS_ITALIC_STYLE;
    }

    public boolean supportsCondensedStyle() {
        return SUPPORTS_CONDENSED_STYLE;
    }


    // CONVERTERS

    public static FontFamily toFontFamily(String fontFamilyString) {
        switch (fontFamilyString) {
            case "CHINESE_SIMPLIFIED":
                return FontFamily.CHINESE_SIMPLIFIED;
            case "CHINESE_TRADITIONAL":
                return FontFamily.CHINESE_TRADITIONAL;
            case "CYRILLIC":
                return FontFamily.CYRILLIC;
            case "DEVANAGARI":
                return FontFamily.DEVANAGARI;
            case "GEORGIAN":
                return FontFamily.GEORGIAN;
            case "GREEK":
                return FontFamily.GREEK;
            case "JAPANESE":
                return FontFamily.JAPANESE;
            case "KOREAN":
                return FontFamily.KOREAN;
            case "LATIN":
                return FontFamily.LATIN;
            case "MONO":
                return FontFamily.MONO;
            case "TAMIL":
                return FontFamily.TAMIL;
            case "THAI":
                return FontFamily.THAI;
            default:
                throw new IllegalArgumentException("Illegal fontFamilyString: " + fontFamilyString);
        }
    }
}
