package ui.helper;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Tom Somerville Roberts on 30/03/2017.
 */
public class PropertyConverter {
    private static PropertyConverter ourInstance = new PropertyConverter();

    public static PropertyConverter getInstance() {
        return ourInstance;
    }

    private PropertyConverter() {
    }

    public StringProperty convert(String input){
        return new SimpleStringProperty(input);
    }

    public IntegerProperty convert(int input){
        return new SimpleIntegerProperty(input);
    }

}
