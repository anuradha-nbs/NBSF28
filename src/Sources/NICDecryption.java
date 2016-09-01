/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mmh
 */
public class NICDecryption {

    public static List<Object> decrypt(String text) {

        LocalDate bday = null;
        boolean gender = false;
        List<Object> data = new ArrayList<>();
        switch (text.length()) {
            case 10: {
                String dayCount = text.substring(2, 5);
                String year = text.substring(0, 2);
                int i = Integer.parseInt(dayCount);
                if (i > 500) {
                    i -= 500;
                    bday = getBDay(year, i, 1);
                    gender = false;
                } else {
                    gender = true;
                    bday = getBDay(year, i, 1);
                }
                break;
            }
            case 12: {
                String dayCount = text.substring(4, 7);
                String year = text.substring(0, 4);
                int i = Integer.parseInt(dayCount);
                if (i > 500) {
                    i -= 500;
                    bday = getBDay(year, i, 2);
                    gender = false;
                } else {
                    gender = true;
                    bday = getBDay(year, i, 2);
                }
                break;
            }
            default:
                JOptionPane.showMessageDialog(null, "NIC Format Is Wrong...!!!");
                break;
        }
        Date bDate = Date.from(bday.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        data.add(bDate);
        data.add(gender);
        return data;
    }

    private static LocalDate getBDay(String year, int dayCount, int type) {
        DateFormat df = new SimpleDateFormat("DD");
        int y = 0;
        if (type == 1) {
            y = Integer.parseInt("19" + year);
        } else if (type == 2) {
            y = Integer.parseInt(year);
        }
        LocalDate ofYearDay = null;
        if ((!Year.isLeap(y)) && dayCount == 61) {

        } else if ((!Year.isLeap(y)) && dayCount > 60) {
            dayCount--;
        }
        ofYearDay = LocalDate.ofYearDay(y, dayCount);

        return ofYearDay;
    }
}
