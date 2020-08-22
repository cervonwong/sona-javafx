package main.java.data.dto.element;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DividerElementDto.class, name = "DIVIDER"),
        @JsonSubTypes.Type(value = RowElementDto.class, name = "ROW"),
        @JsonSubTypes.Type(value = TextElementDto.class, name = "TEXT")
})
// Reference -> https://programmerbruce.blogspot.com/2011/05/deserialize-json-with-jackson-into.html
public class AbstractElementDto {

    // FIELDS

    private String name;


    // CONSTRUCTOR

    public AbstractElementDto() {}


    // ACCESSORS

    public String getName() {
        return name;
    }


    // MUTATORS

    public void setName(String name) {
        this.name = name;
    }


    // OBJECT OVERRIDDEN METHODS

    @Override
    public String toString() {
        return "AbstractElementDto{" +
               "name='" + name + '\'' +
               '}';
    }
}
