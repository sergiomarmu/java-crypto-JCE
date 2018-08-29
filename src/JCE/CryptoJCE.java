package JCE;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;


/**
 *
 * @author sergio
 */
public class CryptoJCE {

    /* VARIABLES */
    String str;
    byte[] strCode;
    int key;
    
    SecretKey sKey = null;
    
    /* CONSTRUCTORS */
    public CryptoJCE(){
        
    }

    public CryptoJCE(String str) {
        this.str = str;
    }
    
    public CryptoJCE(int key) {
        this.key = key;
    }
    
    /* GETTERS AND SETTERS */
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    /* TOSTRING */
    @Override
    public String toString() {
        return "CryptoJCE {" + "str =" + str + '}';
    }
    
    /* METHODS */
    /* MD5 */
    public void vMD5(String text){
        byte[] hash = null;
            try{
                byte[] data = text.getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance("MD5");
                hash = md.digest(data);
                String myHash = DatatypeConverter.printHexBinary(hash);
                System.out.println(myHash);
            }catch (Exception ex) {
                System.out.println("Generador no disponible.");
            }   
    }
    
    /* SHA */
    public void vSHA1(String text){
          byte[] hash = null;
            try{
                byte[] data = text.getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                hash = md.digest(data);
                String myHash = DatatypeConverter.printHexBinary(hash);
                System.out.println(myHash);
            }catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                System.out.println("Generador no disponible.");
            }   
    }
    
    /* MD5 FROM FILE */
    public void vMD5FromFile(){  
        String filename="/home/sergio/Descargas/example";
        try{
                byte[] hash = null;
                MessageDigest md = MessageDigest.getInstance("MD5");
                hash = md.digest(Files.readAllBytes(Paths.get(filename)));
                String myHash = DatatypeConverter.printHexBinary(hash);
                System.out.println(myHash);
        }catch(NoSuchAlgorithmException | IOException ex){System.out.println(ex);}
    }
    
    // SYMMETRIC DES GENERATE A KEY
    public void vDESKeyGenerator(){
        try{
            KeyGenerator kgen = KeyGenerator.getInstance("DES");
            sKey= kgen.generateKey();
            System.out.println("key --> "+DatatypeConverter.printHexBinary(sKey.getEncoded()));
        }catch(Exception e){}
    }
    
    /* SYMMETRIC DES */
    // SYMMETRIC DES ENCRYPT
    public void vDESEncrypt(String strText) throws Exception{
        System.out.println("string --> "+strText);
        Cipher cipher;
        cipher = Cipher.getInstance("DES");
        byte[] strTextByte = strText.getBytes("UTF8");
        cipher.init(Cipher.ENCRYPT_MODE,sKey);
        byte[] encryptBytes = cipher.doFinal(strTextByte);
        strCode = encryptBytes;
        System.out.println("encrypt --> "+DatatypeConverter.printHexBinary(encryptBytes));
    }
    
    // SYMMETRIC DES DECRYPT
    public void vDESDecrypt() throws Exception{
        Cipher cipher;
        cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE,sKey);
        byte[] decryptBytes = cipher.doFinal(strCode);
        System.out.println("decrypt --> "+new String(decryptBytes,"UTF8"));
    }
    
    /* SYMMETRIC 3DES */
    // SYMMETRIC 3DES GENERATOR PRIVATE KEY BASED ON TEXT
    public SecretKey passwordKeyGenerationTripleDES(String text) {
        SecretKey sKey = null;
            try {
                byte[] data = text.getBytes("UTF8");
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(data);
                byte[] key = Arrays.copyOf(hash, 24);
                sKey = new SecretKeySpec(key, "DESede");
            } catch (Exception ex) {
            System.err.println("Error generant la clau:" + ex);
            }
        return sKey;
    } 
    
    // SYMMETRIC 3DES ENCRYPT
    public void vTripleDESEncrypt(String strText,SecretKey sKey) throws Exception{
        System.out.println("string --> "+strText);
        Cipher cipher;
        cipher = Cipher.getInstance("DESede");
        byte[] strTextByte = strText.getBytes("UTF8");
        cipher.init(Cipher.ENCRYPT_MODE,sKey);
        byte[] encryptBytes = cipher.doFinal(strTextByte);
        strCode = encryptBytes;
        System.out.print(Arrays.toString(strCode));
        System.out.println("encrypt --> "+DatatypeConverter.printHexBinary(encryptBytes));
    }
    
    // SYMMETRIC 3DES DECRYPT
    public void vTripleDESDecrypt(byte[] strTextByte, SecretKey sKey) throws Exception{
        Cipher cipher;
        cipher = Cipher.getInstance("DESede");
        //byte[] strTextByte = str.getBytes("UTF8");
        cipher.init(Cipher.DECRYPT_MODE,sKey);
        byte[] decryptBytes = cipher.doFinal(strTextByte);
        System.out.println("decrypt --> "+new String(decryptBytes,"UTF8"));
    }
    
    /* SYMMETRIC AES */  
    // SYMMETRIC AES GENERATOR PRIVATE KEY
   public SecretKey passwordKeyGenerationAES() {
        SecretKey sKey = null;
            try {
                KeyGenerator kgen = KeyGenerator.getInstance("AES");
                sKey= kgen.generateKey();
                System.out.println("key --> "+DatatypeConverter.printHexBinary(sKey.getEncoded()));
            } catch (Exception ex) {
            System.err.println("Error generant la clau:" + ex);
            }
        return sKey;
    } 
    
    // SYMMETRIC AES ENCRYPT
    public void vAESEncrypt(byte[] encryptBytes,String strText, SecretKey sKey,Cipher cipher) throws Exception{
        byte[] strTextByte;
        strTextByte = strText.getBytes("UTF8");
        encryptBytes = vAESEcnryptUpdate(strText,cipher);
        encryptBytes = cipher.doFinal(strTextByte);
        System.out.println("encrypt --> "+DatatypeConverter.printHexBinary(encryptBytes));
        vAESDecrypt(encryptBytes,cipher, sKey);
    }
    
    // SYMMETRIC AES ENCRYPTUPDATE
    public byte[] vAESEcnryptUpdate(String strText,Cipher cipher) throws Exception{
        byte[] strTextByte = strText.getBytes("UTF8");
        byte[] encryptBytes = cipher.update(strTextByte);
        return encryptBytes;
    }
    
    // SYMMETRIC AES CIPHER
    public Cipher vAESCipher(Cipher cipher,SecretKey sKey) throws Exception{
        cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,sKey);
        return cipher;
    }
    // SYMMETRIC AES DECRYPT
    public void vAESDecrypt(byte[] strTextByte,Cipher cipher, SecretKey sKey) throws Exception{
        cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE,sKey);
        byte[] decryptBytes = cipher.doFinal(strTextByte);
        System.out.println("decrypt --> "+new String(decryptBytes,"UTF8"));
    }
}
