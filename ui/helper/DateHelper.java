package ui.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Written by Laure Marchal
 * Format date
 */
public class DateHelper {
    private static DateHelper ourInstance = new DateHelper();

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    private DateHelper() {
    }

    public static DateHelper getInstance() {
        return ourInstance;
    }

    public String formatter(Date date){
        return formatter.format(date);
    }

}
