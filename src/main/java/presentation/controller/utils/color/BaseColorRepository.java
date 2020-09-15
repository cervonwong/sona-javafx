/*
 * Sona is an educational SRS application written in JavaFX.
 * Copyright (C) 2020 Cervon Wong
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package main.java.presentation.controller.utils.color;

import javafx.scene.paint.Color;

public class BaseColorRepository {

    /*
		SONA-SPECIFIC COLOR PALETTE
		Taken from https://codelabs.developers.google.com/codelabs/design-material-darktheme/#0
	*/

    // ORANGE SWATCH

    final static Color ORANGE_050 = Color.rgb(255, 253, 232); // #FFFDE8
    final static Color ORANGE_100 = Color.rgb(255, 249, 198); // #FFF9C6
    final static Color ORANGE_200 = Color.rgb(255, 245, 160); // #FFF5A0
    final static Color ORANGE_300 = Color.rgb(255, 240, 123); // #FFF07B
    final static Color ORANGE_400 = Color.rgb(253, 235, 93);  // #FDEB5D
    final static Color ORANGE_500 = Color.rgb(251, 230, 66);  // #FBE642
    final static Color ORANGE_600 = Color.rgb(255, 217, 66);  // #FFD942
    final static Color ORANGE_700 = Color.rgb(252, 193, 59);  // #FCC13B
    final static Color ORANGE_800 = Color.rgb(249, 170, 51);  // #F9AA33 (Brand Color)
    final static Color ORANGE_900 = Color.rgb(244, 130, 38);  // #F48226


    // BLUE-GREY SWATCH

    final static Color BLUE_GREY_050 = Color.rgb(232, 240, 246); // #E8F0F6
    final static Color BLUE_GREY_100 = Color.rgb(203, 217, 225); // #CBD9E1
    final static Color BLUE_GREY_200 = Color.rgb(173, 192, 203); // #ADC0CB
    final static Color BLUE_GREY_300 = Color.rgb(141, 166, 181); // #8DA6B5
    final static Color BLUE_GREY_400 = Color.rgb(117, 146, 163); // #7592A3
    final static Color BLUE_GREY_500 = Color.rgb(93, 127, 146);  // #5D7F92
    final static Color BLUE_GREY_600 = Color.rgb(81, 112, 129);  // #517081
    final static Color BLUE_GREY_700 = Color.rgb(66, 92, 106);   // #425C6A
    final static Color BLUE_GREY_800 = Color.rgb(52, 73, 85);    // #344955
    final static Color BLUE_GREY_900 = Color.rgb(35, 52, 62);    // #23343E


    // ADDITIONAL COLORS

    // A more desaturated color compared to BLUE_GREY_050.
    final static Color LIGHT_BACKGROUND = Color.rgb(238, 241, 243); // #EEF1F3

    final static Color NEARLY_WHITE = Color.rgb(254, 254, 254); // #FEFEFE
    final static Color ALMOST_WHITE = Color.rgb(242, 242, 242); // #F2F2F2

    final static Color NEARLY_BLACK = BLUE_GREY_900;

    final static Color DARKEST_TEXT = Color.rgb(23, 38, 42); // #17262A (For headings)
    final static Color DARKER_TEXT = Color.rgb(23, 38, 42); // #253840 (For body, etc.)
}
