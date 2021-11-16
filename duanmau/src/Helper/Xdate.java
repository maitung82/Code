/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Xdate {

    static SimpleDateFormat fm = new SimpleDateFormat();

    public static Date toDate(String date, String pattern) {
        try {
            fm.applyPattern(pattern);
            return fm.parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toString(Date date, String pattern) {
        fm.applyPattern(pattern);
        if (date == null) {
            date = new Date();

        }
        return fm.format(date);
    }

    public static Date addDate(Date date, long days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }

    public static Date now() {
        return new Date();
    }
}
