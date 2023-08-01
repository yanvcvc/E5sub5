package mypackage;
import java.io.*;
public interface saveEnc_Decfile{
         public void Creatsavefile(String filepath , String filename);
         public void SavsecLPR(String pa,  byte[] sa );
         public void Saveseccont(byte[] buff, int k);
         public void readsecwenAndSaveminwen(File opensecwen, File saveminwen);
         }