package com.zhiwen.mybatis.plugin;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * @author weizhiwen
 */
public class KeyGenerator {

  public void generator()
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
    keygen.initialize(512);
    KeyPair kp = keygen.generateKeyPair();
    RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();
    RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
    System.out.println("KEY:\n" + bytesToHexString(publicKey.getEncoded()) + "\n");
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, privateKey);
    System.out.println("RESULT:\n" + bytesToHexString(cipher.doFinal("zhiwen".getBytes())) + "\n");
  }

  private static String bytesToHexString(byte[] src) {
    StringBuilder stringBuilder = new StringBuilder("");
    if (src == null || src.length <= 0) {
      return null;
    }
    for (byte aSrc : src) {
      int v = aSrc & 0xFF;
      String hv = Integer.toHexString(v);
      if (hv.length() < 2) {
        stringBuilder.append(0);
      }
      stringBuilder.append(hv);
    }
    return stringBuilder.toString();
  }
}
