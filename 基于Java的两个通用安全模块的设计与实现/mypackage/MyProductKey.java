 package mypackage;
 import java.lang.*;
import java.security.*; 
public class MyProductKey implements ProductKey{
                                    
           String x; 
           String fir;
           String sec;
           String tir;
           String c;
           String v;    
           int len;
           int num;
       
   public String[] DigestKey(String str){
           x=str;          
          String[] p=new String[1];
     try{      
          MessageDigest m=MessageDigest.getInstance("MD5");
               m.update(x.getBytes("UTF8"));
               byte s[ ]=m.digest( );
               String result="";
               for (int i=0; i<s.length; i++){
                     result+=Integer.toHexString((0x000000ff & s[i]) | 
				0xffffff00).substring(6); 
                      }
                p[0]=result;             	     
          }catch(Exception e){
           System.out.println("Exception::"+e);
            }
           return p;
         }

   public String[] DigestKey(String str,int a,int b){
          x=str;
          len=a-6;
          num=b;
          String[] p=new String[num];

     try{       for(int j=0; j<num; j++){ 
          MessageDigest m=MessageDigest.getInstance("MD5");
               m.update(x.getBytes("UTF8"));
               byte s[ ]=m.digest( );
               String result="";
               for (int i=0; i<s.length; i++){
                     result+=Integer.toHexString((0x000000ff & s[i]) | 
				0xffffff00).substring(6); 
                      }
                fir = result.substring(0, 3);
                sec = result.substring(29, 32);
                tir = result.substring(14, 14+len);
                 c=fir.concat(sec);
                 v=c.concat(tir);
                
                p[j]=v;
                x=p[j];
	     }
          }catch(Exception e){
           System.out.println("Exception::"+e);}
           return p;
         }
}