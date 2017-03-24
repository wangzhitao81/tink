
package com.google.cloud.crypto.tink;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Signature;

/**
 * Interface and its implentations to make JCE Engines have a common parent. There's no expected
 * reason to directly import this for users of Tink, but it might be needed to implement it
 * (say, if someone wants a new type of engine).
 */
public interface EngineWrapper<T> {

  class TCipher implements EngineWrapper<Cipher> {
    @SuppressWarnings("InsecureCryptoUsage")
    public Cipher getInstance(String algorithm, Provider provide) throws GeneralSecurityException {
      if (provide == null) {
        return Cipher.getInstance(algorithm);
      } else {
        return Cipher.getInstance(algorithm, provide);
      }
    }
  }

  class TMac implements EngineWrapper<Mac> {
    @SuppressWarnings("InsecureCryptoUsage")
    public Mac getInstance(String algorithm, Provider provide) throws GeneralSecurityException {
      if (provide == null) {
        return Mac.getInstance(algorithm);
      } else {
        return Mac.getInstance(algorithm, provide);
      }
    }
  }

  class TKeyPairGenerator implements EngineWrapper<KeyPairGenerator> {
    @SuppressWarnings("InsecureCryptoUsage")
    public KeyPairGenerator getInstance(String algorithm, Provider provide)
        throws GeneralSecurityException {
      if (provide == null) {
        return KeyPairGenerator.getInstance(algorithm);
      } else {
        return KeyPairGenerator.getInstance(algorithm, provide);
      }
    }
  }

  class TMessageDigest implements EngineWrapper<MessageDigest> {
    @SuppressWarnings("InsecureCryptoUsage")
    public MessageDigest getInstance(String algorithm, Provider provide)
        throws GeneralSecurityException {
      if (provide == null) {
        return MessageDigest.getInstance(algorithm);
      } else {
        return MessageDigest.getInstance(algorithm, provide);
      }
    }
  }

  class TSignature implements EngineWrapper<Signature> {
    @SuppressWarnings("InsecureCryptoUsage")
    public Signature getInstance(String algorithm, Provider provide)
        throws GeneralSecurityException {
      if (provide == null) {
        return Signature.getInstance(algorithm);
      } else {
        return Signature.getInstance(algorithm, provide);
      }
    }
  }

  /**
   * Should call T.getInstance(...).
   */
  public T getInstance(String algorithm, Provider provider) throws GeneralSecurityException;

}
