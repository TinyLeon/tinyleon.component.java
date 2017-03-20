package utility;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;

/**
 * Created by LeonChen
 */
public class SecurityHelper {
    private static byte[] iVValue = new byte[]{12, 34, 56, 78, 90, 91, 92, 93, 12, 34, 56, 78, 90, 91, 92, 93};
    private static String key = "tinyleon.utility";


    private static byte[] aesEncrypt(byte[] plainText, byte[] rijnKey, byte[] rijnIV) throws Exception {
        try {
            SecretKeySpec e = new SecretKeySpec(rijnKey, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(rijnIV);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, e, ivSpec);
            byte[] decrypt = cipher.doFinal(plainText);
            return decrypt;
        } catch (GeneralSecurityException var7) {
            return null;
        }
    }

    private static byte[] aesEncrypt(String plainText, byte[] rijnKey, byte[] rijnIV) throws Exception {
        byte[] byteContent = plainText.getBytes("utf-8");
        return aesEncrypt(byteContent, rijnKey, rijnIV);
    }


    private static String aesDecrypt(byte[] cipherText, byte[] rijnKey, byte[] rijnIV) throws Exception {
        try {
            SecretKeySpec e = new SecretKeySpec(rijnKey, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(rijnIV);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, e, ivSpec);
            byte[] decrypt = cipher.doFinal(cipherText);
            return new String(decrypt, "UTF-8");
        } catch (GeneralSecurityException var7) {
            return "";
        }
    }

    private static String asHex(byte[] buf) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);

        for (int i = 0; i < buf.length; ++i) {
            if ((buf[i] & 255) < 16) {
                strbuf.append("0");
            }

            strbuf.append(Long.toString((long) (buf[i] & 255), 16));
        }

        return strbuf.toString();
    }

    private static byte[] asBin(String src) {
        if (src.length() < 1) {
            return null;
        } else {
            byte[] encrypted = new byte[src.length() / 2];

            for (int i = 0; i < src.length() / 2; ++i) {
                int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);
                encrypted[i] = (byte) (high * 16 + low);
            }

            return encrypted;
        }
    }

    public static String Encrypt(String plainText) {
        String encryString = "";
        try {
            byte[] encryptByte = aesEncrypt(plainText, key.getBytes("utf-8"), iVValue);
            encryString = String.valueOf(asHex(encryptByte)).replace("-", "").toLowerCase();
            return encryString;
        } catch (Exception e) {
            return encryString;
        }
    }

    public static String Dencrypt(String cipherText) {
        String memberId = "";
        try {
            byte[] data = asBin(cipherText);
            byte[] keyBytes = key.getBytes("UTF-8");
            memberId = aesDecrypt(data, keyBytes, iVValue);
            return memberId;
        } catch (Exception e) {
            return memberId;
        }
    }

    public static long getMemberId(String sipherText) {
        long memberId = 0;
        if (sipherText == null || sipherText.isEmpty())
            return memberId;
        String memberIdStr = Dencrypt(sipherText);
        if (memberIdStr == null || memberIdStr.isEmpty())
            return memberId;
        try {
            memberId = Long.parseLong(memberIdStr);
            return memberId;
        } catch (Exception e) {
            return memberId;
        }
    }
}
