/*
Encryption Decrption AES

Further information, source, issues with this algorithm / code:
* See readme.md


*/
import java.security.Key;
import javax.crypto.Cipher;
import java.util.Base64;
import java.util.Base64.Encoder;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionDecriptionAES
{

    private static final String ALGO = "AES";
  //private static final String KEY_STR = "s@IlIngkePPel";     // ERROR: Must be 16 bytes.
    private static final String KEY_STR = "s@IlIngkePPelasa";  // OK, 16 bytes.
  //private static final String KEY_STR = "TheRooKnatEnirin";

    //private static final byte[] keyValue = new byte[]{'T', 'h', 'e', 'R', 'o', 'o', 'K', 'n', 'a', 't','E','n', 'i', 'r','i','n'};
    private static       byte[] keyValue = KEY_STR.getBytes();

    public EncryptionDecriptionAES(){

    }

    public static boolean setKeyValue (String newKeyStr)
    {
       boolean result = false; // ERROR;

       if ((newKeyStr != null) && (newKeyStr.length() == 16) )
       {
          keyValue = newKeyStr.getBytes();
          result = true; // All OK.
       }
       else
       {
          System.out.println ("ERROR: Invalid key '" + newKeyStr + "' - must be 16 chars long.");
       }

       return result;
    }

    public static String encryptString (String dataStr) throws Exception
    {
        Key key = getKey();
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedValue = cipher.doFinal(dataStr.getBytes("UTF-8"));

        //return Base64.encodeToString(encryptedValue, Base64.DEFAULT);
        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    public static String decryptString (String dataStr) throws Exception
    {
        if(dataStr != null)
        {
            Key key = getKey();
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(Cipher.DECRYPT_MODE, key);
            //byte[] decodebyte = Base64.decode(data.getBytes("UTF-8"), Base64.DEFAULT);
            byte[] decodebyte = Base64.getDecoder().decode(dataStr.getBytes("UTF-8"));

            byte[] decValue = cipher.doFinal(decodebyte);

            return new String(decValue);
        }

        return null;
    }

    private static Key getKey() throws Exception
    {
        return new SecretKeySpec(keyValue, ALGO);
    }

    private static String getKeyString()
    {
       String resultStr = "";

       for (int k = 0; k < keyValue.length; k++)
       {
          resultStr += "" + (char) keyValue [k];
       }

       return resultStr;
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
          encryptedStr = encryptString (originalStr);
          decryptedStr = decryptString (encryptedStr);
       }
       catch (Exception err)
       {
          System.out.println ("Error: " + err.toString());
       }

       System.out.println ("Key           = " + getKeyString() );
       System.out.println ("Key length    = " + keyValue.length + " bytes (must be 16 bytes).");
       System.out.println ("originalStr   = " + originalStr);
       System.out.println ("encryptedStr  = " + encryptedStr);
       System.out.println ("decryptedStr  = " + decryptedStr);
    }
} // public class EncryptionDecriptionAES

