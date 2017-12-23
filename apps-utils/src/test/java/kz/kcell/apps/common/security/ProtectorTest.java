package kz.kcell.apps.common.security;

import org.junit.Test;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 24 02 2015
 */
public class ProtectorTest {

    @Test
    public void test() throws Exception {
        String password = "j3mhnhkt";
//        String salt = "this is a simple clear salt";
        String salt = "kasdjv9334kjn34f";
        String passwordEnc = Protector.encrypt(password, salt);
        String passwordDec = Protector.decrypt(passwordEnc, salt);
        System.out.println("Salt Text : " + salt);
        System.out.println("Plain Text : " + password);
        System.out.println("Encrypted : " + passwordEnc);
        System.out.println("Decrypted : " + passwordDec);
    }
}
