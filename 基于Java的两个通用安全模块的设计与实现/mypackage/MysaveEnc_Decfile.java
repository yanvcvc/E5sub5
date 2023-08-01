package mypackage;


import java.io.*;
import java.lang.*;
//import java.util.*;

public class MysaveEnc_Decfile implements saveEnc_Decfile
 {  
   public FileOutputStream fouts = null;
   public InputStream filsecwen = null;
   public FileOutputStream filminwem = null;
   String LenStr;
   int le;
   byte[] LenStrtoByte = new byte[2];
   byte[] LenAndSalt = new byte[10];//用于保存读取的口令长度跟盐
   byte[] LenByte = new byte[2];
   byte[] saltsave = new byte[8];
   String passstr;
//创保存文件
  public void Creatsavefile(String filepath , String filename){
        try{fouts = new FileOutputStream(new File(filepath , filename ));}
        catch(Exception e){}
           }
//保存口令、随机数、口令长度
   public void SavsecLPR(String pa,  byte[] sa )
   {
     le = pa.length();
     if(le <= 9)
     {
       
      String str1 = String.valueOf(le);
      String tian = "0";
      LenStr = tian.concat(str1);
      }
      else
      {LenStr = String.valueOf(le);}
      try{ LenStrtoByte = LenStr.getBytes("UTF8");
      
           byte[] pas = new byte[le];
           pas = pa.getBytes("UTF8");

           fouts.write( LenStrtoByte);
           fouts.write(sa);
           fouts.write(pas);}catch(Exception e){}
    }
//保存密文
    public void Saveseccont(byte[] buff, int k){
    try{ fouts.write(buff, 0, k);}catch(Exception e){}
           
     }

public void readsecwenAndSaveminwen(File opensecwen, File saveminwen){
    try{
        filsecwen = new FileInputStream(opensecwen);
        filminwem = new FileOutputStream(saveminwen);
        filsecwen.read(LenAndSalt);
        for(int i = 0; i<2; i++)
        {LenByte[i] = LenAndSalt[i];}
            String lenstr = new String(LenByte,"UTF8");

            String lenstr1 = lenstr.substring(0,1);
             String lenstr2 = lenstr.substring(1,2);
            String lenfull;
            if(lenstr1 == "0") 
            {lenfull = lenstr2;}
            else
            { lenfull = lenstr;}

            int Lenint = Integer.parseInt(lenfull);

            for(int i=2; i<10;i++)
             {saltsave[i-2] = LenAndSalt[i];}

            byte[] pass = new byte[Lenint];
            filsecwen.read(pass);
            passstr = new String(pass,"UTF8");

            int num=filsecwen.available();
            byte buf[]=new byte[1032];  
                        while ((num=filsecwen.read(buf))>=0){
                         System.out.println("num="+num+",");
                          byte buffse[] = new byte[num];
                          for(int i=0; i<num; i++){
                           buffse[i] = buf[i];
                          }
                         MyEnc_Dec MyEnc = new MyEnc_Dec();
                         byte[] buffptext = MyEnc.Decrypt(passstr,saltsave,buffse);
                           int v = buffptext.length;  
                            System.out.println("v="+v+",");
                           filminwem.write(buffptext,0,v);
                           String temp =new String(buffptext,0,v);
                           } //完成while
             }catch(Exception e){}       
    
    }


}