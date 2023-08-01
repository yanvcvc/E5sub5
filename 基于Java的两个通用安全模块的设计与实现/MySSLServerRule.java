import mypackage.*;
import java.net.*;
import java.io.*;
import javax.net.ssl.*;
import java.security.*;
import java.lang.*;
import java.util.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.io.IOException;

public class MySSLServerRule{
  public static void main(String args[ ]) throws Exception{

        String[] idandfile = new String[10];
       String passwor = "";
       String randpass= "";
           byte[] salt = new byte[8];
        System.setProperty("javax.net.ssl.keyStore","mykeystore");
        System.setProperty("javax.net.ssl.keyStorePassword","wshr.ut");
        SSLServerSocketFactory ssf= 
             (SSLServerSocketFactory) 
                    SSLServerSocketFactory.getDefault( );
        ServerSocket ss=ssf.createServerSocket(5432);

        Myserverfile ServerFile = new Myserverfile();
                     ServerFile.show();

        MysaveEnc_Decfile savfile = new MysaveEnc_Decfile();//保存文件实例

		System.out.println("Waiting for connection...");

        while(true){
	   		Socket s=ss.accept( );
               OutputStream outs=s.getOutputStream( );
	       PrintStream out = new PrintStream(outs);
               InputStream  ins = s.getInputStream( );
               BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
              String line=in.readLine( );
            System.out.println(line);
            if( line.startsWith("sent:")){  
               
                  ServerFile.saveButton.setEnabled(true);            
                
                  String IDandFILE = line.substring(5);
                  idandfile = IDandFILE.split(",");

               for(int i=0; i<8;i++){                                                
                salt[i] = Byte.parseByte(idandfile[i]);
                 }          //盐
           System.out.print("变后随机数salt[i]：");
      for(int i=0; i<8;i++){
         System.out.print(salt[i]+",");}

              passwor = idandfile[8];//传输口令                                                 
                  String recfile = idandfile[9];//获取文件名
                   ServerFile.RecevefileN = recfile;
                  System.out.println("文件名:recfile="+recfile);
System.out.println("打印口令密码文件名:IDandFILE="+IDandFILE);//打印口令密码文件名
System.out.println("打印口令密码:passwor="+passwor); 
               while(ServerFile.flag == 0){
                    ServerFile.saveButton.setEnabled(true);     
                   }
                  ServerFile.saveButton.setEnabled(false); 
                  String recfilepath = ServerFile.spathN;  
                  savfile.Creatsavefile(recfilepath , recfile);    
        

   }//完成IF
           savfile.SavsecLPR(passwor,  salt );

          int kk = ins.available();
          byte buffe[]=new byte[2056];
   while((kk=ins.read(buffe))>=0){
          System.out.println("kk="+kk+",");
           savfile.Saveseccont(buffe, kk) ;  

          }//完成buffWHILE
       
         ServerFile.flag = 0;
         ServerFile.message();
         in.close( );
          savfile.fouts.close();
          s.close();
        }//完成WHILE	     		  
   }
}    
    

       