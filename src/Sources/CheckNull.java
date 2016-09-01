/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sources;

import java.util.Optional;

/**
 *
 * @author mmh
 */
public class CheckNull {
    private static boolean nullable;
    /**
     *
     * @param ob object to check  whether it is null 
     * @return true if null
     */

    public static boolean isNullable(Object ob){
        Optional<Object> op = Optional.ofNullable(ob);
        nullable = !op.isPresent();
        return nullable;
    }
}
