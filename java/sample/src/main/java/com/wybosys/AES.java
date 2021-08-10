package com.wybosys;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class AES {

    public static String KEY;
    public static String IV;
    public static String METHOD = "AES/CBC/NoPadding";

    public static String Encrypt(String plain) throws Exception {
        Cipher cipher = Cipher.getInstance(METHOD);
        SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
        byte[] result = cipher.doFinal(plain.getBytes());
        return Hex.encodeHexString(result);
    }

    public static String Decrypt(String plain) throws Exception {
        Cipher cipher = Cipher.getInstance(METHOD);
        SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
        byte[] result = cipher.doFinal(Hex.decodeHex(plain));
        return new String(result, StandardCharsets.UTF_8);
    }

}
