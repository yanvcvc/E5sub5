package mypackage;
public interface Enc_Dec{
        public byte[] Encrypt(String passw,byte[] rand,byte[] text);
        public byte[] Decrypt(String passw,byte[] rand,byte[] ctext);
         }