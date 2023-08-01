package mypackage;

import java.io.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class MyEnc_Dec implements Enc_Dec{
      
       int len;
       int lent;
        public byte[] Encrypt(String passw,byte[] rand,byte[] text)
        {
             byte[] salt = rand;
             String password = passw;
             byte[] buff = text;   
             byte[] ctextret = new byte[2056];           
          
           try{
                char[] passwd=password.toCharArray( );
                PBEKeySpec pbks=new PBEKeySpec(passwd);
                SecretKeyFactory kf=
                      SecretKeyFactory.getInstance("PBEWithMD5AndDES");
                 SecretKey k=kf.generateSecret(pbks);
                   Cipher cp=Cipher.getInstance("PBEWithMD5AndDES");
                              PBEParameterSpec ps=new PBEParameterSpec(salt,1000);
               
                 cp.init(Cipher.ENCRYPT_MODE, k,ps);
                 byte ctext[]=cp.doFinal(buff);
                 len = ctext.length;
             for(int i=0; i<len; i++)
            {
             ctextret[i] = ctext[i];
             }        
  
              }catch(Exception e){
                      System.out.println("Exception::"+e);
            }

            byte[] ctextreturn = new byte[len];
             for(int i=0; i<len; i++)
            {
             ctextreturn[i] = ctextret[i];
             }           
                
             return ctextreturn;  
          }              


       public byte[] Decrypt(String passw,byte[] rand,byte[] ctext)  
         {  
          
           byte[] salte = rand;
           String password = passw;
           byte[] ctextbuff = ctext;
           byte[] ptextret = new byte[2056]; 
          
          try{
            char[] passwd=password.toCharArray( );
            PBEKeySpec pbks=new PBEKeySpec(passwd);
                   SecretKeyFactory kf=
SecretKeyFactory.getInstance("PBEWithMD5AndDES"); 
       SecretKey k=kf.generateSecret(pbks);
       Cipher cp=Cipher.getInstance("PBEWithMD5AndDES");
       PBEParameterSpec ps=new PBEParameterSpec(salte,1000);
       cp.init(Cipher.DECRYPT_MODE, k,ps);
       byte ptext[]=cp.doFinal(ctextbuff);
       lent = ptext.length;
             for(int i=0; i<lent; i++)
            {
             ptextret[i] = ptext[i];
             } 
         
       }catch(Exception e){
           System.out.println("Exception::"+e);
            }
          byte[] ptextreturn = new byte[lent];
             for(int i=0; i<lent; i++)
            {
             ptextreturn[i] = ptextret[i];
             } 

          return ptextreturn;
          }

}