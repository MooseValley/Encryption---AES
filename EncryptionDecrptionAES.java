// EncryptionDecrptionAES

// https://stackoverflow.com/questions/13109588/base64-encoding-in-java
// Had MANY errors / issues - but all have been fixed by Moose.

import java.security.Key;
import javax.crypto.Cipher;
import java.util.Base64;
import java.util.Base64.Encoder;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionDecrptionAES {

    private static final String ALGO = "AES";
  //private static final String KEY_STR = "S@1lingK3pp3L";
    private static final String KEY_STR = "sAIlIngkEppElasa";  // Must be 16 bytes.
  //private static final String KEY_STR = "TheRooKnatEnirin";

    //private static final byte[] keyValue = new byte[]{'T', 'h', 'e', 'R', 'o', 'o', 'K', 'n', 'a', 't','E','n', 'i', 'r','i','n'};
    private static final byte[] keyValue = KEY_STR.getBytes();

    public EncryptionDecrptionAES(){

    }

    public static String setEncryptedString(String data) throws Exception
    {
        Key key = getKey();
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedValue = cipher.doFinal(data.getBytes("UTF-8"));

        //return Base64.encodeToString(encryptedValue, Base64.DEFAULT);
        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    public static String getDecryptedValue(String data) throws Exception
    {
        if(data != null)
        {
            Key key = getKey();
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(Cipher.DECRYPT_MODE, key);
            //byte[] decodebyte = Base64.decode(data.getBytes("UTF-8"), Base64.DEFAULT);
            byte[] decodebyte = Base64.getDecoder().decode(data.getBytes("UTF-8"));

            byte[] decValue = cipher.doFinal(decodebyte);

            return new String(decValue);
        }

        return null;
    }

    private static Key getKey() throws Exception
    {
        return new SecretKeySpec(keyValue, ALGO);
    }

    public static void main (String[] args)
    {
      if (keyValue.length != 16)
      {
         System.out.println ("ERROR: key must be 16 characters - not " + keyValue.length);
         System.exit (-1);
      }

       String originalStr  = "Moose Valley Rules !!!";
       String encryptedStr = "";
       String decryptedStr = "";

       try
       {
          encryptedStr = setEncryptedString (originalStr);
          decryptedStr = getDecryptedValue  (encryptedStr);
       }
       catch (Exception err)
       {
          System.out.println ("Error: " + err.toString());
       }

       System.out.println ("Key length    = " + keyValue.length + " bytes (must be 16 bytes).");
       System.out.println ("originalStr   = " + originalStr);
       System.out.println ("encryptedStr  = " + encryptedStr);
       System.out.println ("decryptedStr  = " + decryptedStr);


    }


}
