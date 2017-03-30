package ui.helper;

import javafx.beans.property.*;

/**
 * Created by Tom Somerville Roberts on 30/03/2017.
 */
public class PropertyConverter {
    private static PropertyConverter ourInstance = new PropertyConverter();

    private PropertyConverter() {
    }

    public static PropertyConverter getInstance() {
        return ourInstance;
    }

    public StringProperty convert(String input){
        return new SimpleStringProperty(input);
    }

    public IntegerProperty convert(Integer input){
        return new SimpleIntegerProperty(input);
    }

    public FloatProperty convert(Float input){
        return new SimpleFloatProperty(input);
    }

}
