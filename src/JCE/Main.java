package JCE;

import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

/**
 *
 * @author sergio
 */
public class Main {

    public static void main(String [] args) throws Exception{
        
        Scanner scan = new Scanner(System.in);
        CryptoJCE crypto = new CryptoJCE(123456);
        SecretKey sKey;
        String pass,str="",strScan="";
        byte[] bytes = new byte[8];
        int x;
        boolean check=true;
        /* MD5 */
        crypto.vMD5("hola");
        /* SHA1 */
        //crypto.vSHA1("hola");
        /* MD5 FROM FILE */
        //crypto.vMD5FromFile();
        /* DES SYMMETRIC*/
        //crypto.vDESKeyGenerator();
        //crypto.vDESEncrypt("holaaaaa");
        //crypto.vDESDecrypt();
        /* 3DES SYMMETRIC */
        //System.out.println("password?");
        //pass = scan.next();
        //sKey = crypto.passwordKeyGenerationTripleDES(pass);
        //crypto.vTripleDESEncrypt("hola",sKey);       
        //
        //System.out.println("bytes?");
        //for(x=0;x<bytes.length;x++) bytes[x]=scan.nextByte();
        //crypto.vTripleDESDecrypt(bytes,sKey);
        /* AES */
        //Cipher cipher = null;
        //byte[] encryptBytes = null;
        //sKey = crypto.passwordKeyGenerationAES();
        //cipher= crypto.vAESCipher(cipher,sKey);
        //crypto.vAESCipher(cipher, sKey);
        //while(check){
        //    strScan="";
        //    strScan+=scan.nextLine();
        //    if(strScan.equals("exit"))break;
        //    else{
        //        str+=strScan;
        //        encryptBytes=crypto.vAESEcnryptUpdate(str, cipher);
        //    } 
        //    crypto.vAESEncrypt(encryptBytes, str, sKey, cipher);
        //}
    }
}
