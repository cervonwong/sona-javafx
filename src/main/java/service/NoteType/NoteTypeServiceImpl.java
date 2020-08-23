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

package main.java.service.NoteType;

import main.java.data.dao.GenericDao;
import main.java.data.dao.NoteTypeProperties.NoteTypePropertiesDao;
import main.java.data.dao.NoteTypeProperties.NoteTypePropertiesDaoImpl;
import main.java.data.dao.noteType.NoteTypeDaoImpl;
import main.java.data.dto.CardTypeDto;
import main.java.data.dto.NoteTypeDto;
import main.java.data.dto.element.*;
import main.java.data.dto.enums.*;
import main.java.presentation.model.structure.card.CardType;
import main.java.presentation.model.structure.card.element.AbstractElement;
import main.java.presentation.model.structure.card.element.control.divider.DividerElement;
import main.java.presentation.model.structure.card.element.control.text.TextElement;
import main.java.presentation.model.structure.card.element.control.text.TextFormat;
import main.java.presentation.model.structure.card.element.control.text.enums.*;
import main.java.presentation.model.structure.note.NoteType;
import main.java.presentation.model.structure.note.NoteTypeProperties;

import java.util.*;

public class NoteTypeServiceImpl implements NoteTypeService {

    // OVERRIDDEN METHODS

    @Override
    public List<NoteType> getAll() {
        final GenericDao<NoteTypeDto> DAO = new NoteTypeDaoImpl();

        final List<NoteTypeDto> NOTE_TYPE_DTO_LIST = DAO.getAll();
        final List<NoteType> NOTE_TYPE_LIST = new ArrayList<>();

        for (NoteTypeDto noteTypeDto : NOTE_TYPE_DTO_LIST)
            NOTE_TYPE_LIST.add(toNoteType(noteTypeDto));

        return NOTE_TYPE_LIST;
    }

    @Override
    public Optional<NoteType> get(int id) {
        final GenericDao<NoteTypeDto> DAO = new NoteTypeDaoImpl();

        final Optional<NoteTypeDto> OPTIONAL_DTO = DAO.get(id);

        return OPTIONAL_DTO.map(this::toNoteType);
    }

    @Override
    public void update(NoteType noteType) {
        // FIXME: 22/08/2020 Unimplemented.
    }

    @Override
    public NoteType create() {
        // Gets the next NoteType id from NoteTypeProperties.
        final NoteTypePropertiesDao NOTE_TYPE_PROPERTIES_DAO = new NoteTypePropertiesDaoImpl();
        final NoteTypeProperties NOTE_TYPE_PROPERTIES = NOTE_TYPE_PROPERTIES_DAO.get();
        final int NEXT_ID = NOTE_TYPE_PROPERTIES.getNextId();

        // Update NoteTypeProperties for the future next NoteType id.
        NOTE_TYPE_PROPERTIES.setNextId(NEXT_ID + 1);
        NOTE_TYPE_PROPERTIES_DAO.update(NOTE_TYPE_PROPERTIES);

        // Creates the new NoteType.
        final NoteType NOTE_TYPE = new NoteType.NoteTypeBuilder(NEXT_ID, 1).build();

        // Passes the new NoteType to DAO to update database.
        final GenericDao<NoteTypeDto> DAO = new NoteTypeDaoImpl();
        DAO.create(toDto(NOTE_TYPE));

        // Returns caller the new NoteType.
        return NOTE_TYPE;
    }


    @Override
    public void delete(NoteType noteType) {

    }


    // CONVERTERS (DTO -> Model Object)

    private NoteType toNoteType(NoteTypeDto noteTypeDto) {
        final int ID = noteTypeDto.getId();
        final String NAME = noteTypeDto.getName();
        final int NEXT_CARD_TYPE_ID = noteTypeDto.getNextCardTypeId();
        final List<CardTypeDto> CARD_TYPE_LIST = noteTypeDto.getCardTypes();
        final List<String> FIELDS = noteTypeDto.getFields();

        final Map<Integer, CardType> CARD_TYPE_MAP = new HashMap<>();

        for (CardTypeDto cardTypeDto : CARD_TYPE_LIST) {
            final int CARD_TYPE_ID = cardTypeDto.getId();
            final CardType CARD_TYPE = toCardType(cardTypeDto);

            CARD_TYPE_MAP.put(CARD_TYPE_ID, CARD_TYPE);
        }

        return new NoteType.NoteTypeBuilder(ID, NEXT_CARD_TYPE_ID).name(NAME)
                                                                  .cardTypes(CARD_TYPE_MAP)
                                                                  .fields(FIELDS)
                                                                  .build();
    }

    private CardType toCardType(CardTypeDto cardTypeDto) {
        final String NAME = cardTypeDto.getName();

        final List<AbstractElementDto> FRONT_DTO = cardTypeDto.getFront();
        final List<AbstractElementDto> BACK_DTO = cardTypeDto.getBack();

        final List<AbstractElement> FRONT = new ArrayList<>();
        for (AbstractElementDto abstractElementDto : FRONT_DTO)
            FRONT.add(toAbstractElement(abstractElementDto));

        final List<AbstractElement> BACK = new ArrayList<>();
        for (AbstractElementDto abstractElementDto : BACK_DTO)
            BACK.add(toAbstractElement(abstractElementDto));

        return new CardType.CardTypeBuilder().name(NAME)
                                             .front(FRONT)
                                             .back(BACK)
                                             .build();
    }

    private AbstractElement toAbstractElement(AbstractElementDto abstractElementDto) {
        // TODO: 22/08/2020 Refactor code smell using instanceof.
        if (abstractElementDto instanceof TextElementDto)
            return toTextElement(((TextElementDto) abstractElementDto));

        else if (abstractElementDto instanceof DividerElementDto)
            return toDividerElement(((DividerElementDto) abstractElementDto));

        else if (abstractElementDto instanceof RowElementDto)
            // FIXME: 22/08/2020 Not yet implemented.
            return null;

        else
            throw new IllegalArgumentException("Illegal abstractElementDto: Unknown subclass");
    }

    private TextElement toTextElement(TextElementDto textElementDto) {
        final String NAME = textElementDto.getName();
        final String VALUE = textElementDto.getValue();
        final double FONT_SIZE_FACTOR = textElementDto.getFontSizeFactor();
        final FontFamilyDto FONT_FAMILY_DTO = textElementDto.getFontFamily();
        final boolean HAS_SERIF = textElementDto.isHasSerif();
        final FontWeightDto FONT_WEIGHT_DTO = textElementDto.getFontWeight();
        final boolean IS_ITALICIZED = textElementDto.isItalicized();
        final boolean IS_UNDERLINED = textElementDto.isUnderlined();
        final boolean IS_CONDENSED = textElementDto.isCondensed();
        final ElementColorDto ELEMENT_COLOR_DTO = textElementDto.getElementColor();

        final FontFamily FONT_FAMILY = toFontFamily(FONT_FAMILY_DTO);
        final FontWeight FONT_WEIGHT = toFontWeight(FONT_WEIGHT_DTO);
        final ElementColor ELEMENT_COLOR = toElementColor(ELEMENT_COLOR_DTO);

        return new TextElement.TextElementBuilder()
                .name(NAME)
                .value(VALUE)
                .textFormat(new TextFormat.TextFormatBuilder()
                                    .fontSizeFactor(FONT_SIZE_FACTOR)
                                    .fontFamily(FONT_FAMILY)
                                    .hasSerif(HAS_SERIF)
                                    .fontWeight(FONT_WEIGHT)
                                    .isItalicized(IS_ITALICIZED)
                                    .isUnderlined(IS_UNDERLINED)
                                    .isCondensed(IS_CONDENSED)
                                    .elementColor(ELEMENT_COLOR)
                                    .build())
                .build();
    }

    private DividerElement toDividerElement(DividerElementDto dividerElementDto) {
        final String NAME = dividerElementDto.getName();
        final double SIZE = dividerElementDto.getSize();
        final double THICKNESS = dividerElementDto.getThickness();
        final boolean IS_SYMMETRICAL = dividerElementDto.isSymmetrical();
        final double LEADING_INDENT = dividerElementDto.getLeadingIndent();
        final double TRAILING_INDENT = dividerElementDto.getTrailingIndent();
        final ElementColorDto ELEMENT_COLOR_DTO = dividerElementDto.getElementColor();

        final ElementColor ELEMENT_COLOR = toElementColor(ELEMENT_COLOR_DTO);

        return new DividerElement.DividerElementBuilder().size(SIZE)
                                                         .thickness(THICKNESS)
                                                         .isSymmetrical(IS_SYMMETRICAL)
                                                         .leadingIndent(LEADING_INDENT)
                                                         .trailingIndent(TRAILING_INDENT)
                                                         .elementColor(ELEMENT_COLOR)
                                                         .build();
    }

    private FontFamily toFontFamily(FontFamilyDto fontFamilyDto) {
        return FontFamily.valueOf(fontFamilyDto.name());
    }

    private FontWeight toFontWeight(FontWeightDto fontWeightDto) {
        return FontWeight.valueOf(fontWeightDto.name());
    }

    private ElementColor toElementColor(ElementColorDto elementColorDto) {
        return ElementColor.valueOf(elementColorDto.name());
    }


    // CONVERTERS (Model Object -> DTO)

    private NoteTypeDto toDto(NoteType noteType) {
        return null;
    }
}
