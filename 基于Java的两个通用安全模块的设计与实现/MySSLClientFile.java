import java.net.*;
import java.io.*;
import javax.net.ssl.*;
import java.util.*;
import java.sql.*;
import mypackage.*;
public class MySSLClientFile{
        private Connection loginconnection = null;
        private Statement loginstatement; 	
        private ResultSet loginresultSet; 

        PrintStream out;
        BufferedReader in;
        public int sentf = 0;
        public int ID;
        public String passwo;
        public String fileName;
        public String filePath;
        byte[] salt = new byte[8];
        String intsalt;

        SSLSocketFactory ssf;
        Socket s;

public MySSLClientFile(int userID,String passWo,String filepath,String filename)
    {
           ID = userID;
           filePath = filepath;
           fileName = filename;
           passwo = passWo;      
        System.setProperty("javax.net.ssl.trustStore",
                   "clienttrust");
               ssf= 
             (SSLSocketFactory) SSLSocketFactory.getDefault( );
         try{
             s = ssf.createSocket("127.0.0.1", 5432);
      
            out = new PrintStream(s.getOutputStream( ));
            in = new BufferedReader(
                                   new InputStreamReader(s.getInputStream( )));
            }catch(Exception e){
                System.err.println("Exception::"+e);
            }
//生成随机数
      Random r = new Random();
      r.nextBytes(salt);
        StringBuffer sb=new StringBuffer();
        int num = salt.length-1;
        for(int i=0;i<salt.length-1;i++)
        {
         sb.append(salt[i]+",");
        }
       sb.append(salt[num]);
       String sal = new String(sb);
           intsalt = sal;       
//联数据库
     String url="jdbc:odbc:Person";
    try 
      	{ 
        	Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" ); 
        	loginconnection = DriverManager.getConnection( url); 
      	}
//捕获加载驱动程序异常 
      	catch ( ClassNotFoundException cnfex ) 
      	{ 
        	System.err.println("装载 JDBC/ODBC 驱动程序失败。" ); 
        	cnfex.printStackTrace(); 
         	System.exit( 1 );  // terminate program 
      	} 
//捕获连接数据库异常
      	catch ( SQLException sqlex ) 
      	{ 
         	System.err.println( "无法连接数据库" ); 
         	sqlex.printStackTrace(); 
         	System.exit( 1 );  // terminate program 
      	} 
     	try
     	{      
         String loginupdatequery;
         loginupdatequery = "Update Person set RAND ='"+intsalt+"'Where ID = "+ID+"";    
         loginstatement = loginconnection.createStatement(); 
         int insert = loginstatement.executeUpdate( loginupdatequery );
         loginconnection.close();
        }
     	catch(SQLException sqlex)
     	{
     		sqlex.printStackTrace();
     	}

        out.println("sent:"+intsalt+","+passwo+","+fileName);//传输口令跟盐值

      System.out.println("intsalt:"+intsalt+"passwo:"+passwo);
        
                    try{
                        File fi=new File(filePath,fileName);
                        InputStream fs=new FileInputStream(fi);
                        int n=fs.available();
                        byte buf[]=new byte[1024];
                        while ((n=fs.read(buf))>=0){
                         System.out.println("n="+n+",");
                          byte buffse[] = new byte[n];
                          for(int i=0; i<n; i++){
                           buffse[i] = buf[i];
                          }
                         MyEnc_Dec MyEnc = new MyEnc_Dec();
                         byte[] buffctext = MyEnc.Encrypt(passwo,salt,buffse);
                           int v = buffctext.length;  
                            System.out.println("v="+v+",");
                            out.write(buffctext,0,v);
                        }
                        }catch(Exception e){
                           System.out.println(e);
                        }
                 try{
                      out.close();
                      in.close();
                      }catch(Exception e){
                System.err.println("Exception::"+e);
            }
             sentf = 1;
           }//构造方法
}