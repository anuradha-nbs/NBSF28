/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sources;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

/**
 * created on Nov 20, 2015
 *
 * @author mmh
 */
public class Dates {

    /**
     *
     * @param dob Date of birth to be processed
     * @return age denoted by given dob
     */
    public static Period getAge(Date dob) {
        String formatedDate = new SimpleDateFormat("yyyy-MM-dd").format(dob);
        LocalDate dobirth = LocalDate.parse(formatedDate);
        LocalDate now = LocalDate.now();
        Period between = Period.between(dobirth, now);
        return between;
    }

}
