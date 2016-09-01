/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author mmh
 */
public class PassWordCreator {

    public static void main(String[] args) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("123");
        String encrypt = encryptor.encrypt("8534@java");
        //String encrypt = encryptor.encrypt("123");
        System.out.println(encrypt);
    }
}
