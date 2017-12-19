 package com.css.common.util;
 import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
 
 public class EncryptDecrypt
 {
   static final byte[] KEYVALUE = "%^&*(*&#@@#$)".getBytes();
   static final int BUFFERLEN = 512;
   
   public static byte[] encryptFile(InputStream in)
     throws IOException
   {
     byte[] newFile = new byte[in.available()];
     
     int pos = 0;
     int keylen = KEYVALUE.length;
     byte[] buffer = new byte[512];
     int j = 0;
     int c;
     while ((c = in.read(buffer)) != -1) {
       for (int i = 0; i < c; i++)
       {
         int tmp50_48 = i; byte[] tmp50_46 = buffer;tmp50_46[tmp50_48] = ((byte)(tmp50_46[tmp50_48] ^ KEYVALUE[pos]));
         newFile[j] = buffer[i];
         pos++;
         j++;
         if (pos == keylen) {
           pos = 0;
         }
       }
     }
     in.close();
     return newFile;
   }
   
   public static byte[] decryptFile(InputStream in)
     throws IOException
   {
     byte[] newFile = new byte[in.available()];
     
     int pos = 0;
     int keylen = KEYVALUE.length;
     byte[] buffer = new byte[512];
     int j = 0;
     int c;
     while ((c = in.read(buffer)) != -1) {
       for (int i = 0; i < c; i++)
       {
         int tmp50_48 = i; byte[] tmp50_46 = buffer;tmp50_46[tmp50_48] = ((byte)(tmp50_46[tmp50_48] ^ KEYVALUE[pos]));
         newFile[j] = buffer[i];
         pos++;
         j++;
         if (pos == keylen) {
           pos = 0;
         }
       }
     }
     in.close();
     
     return newFile;
   }
   
   public static void main(String[] str)
   {
     File file = new File("D:\\upload\\用例图.png");
     File newFile = new File("D:\\upload\\加密.png");
     try
     {
       byte[] fileByte = encryptFile(new FileInputStream(file));
       
       FileOutputStream out = new FileOutputStream(newFile);
       out.write(fileByte);
       out.close();
       System.out.print("11111111");
       
       byte[] decryptFileByte = decryptFile(new FileInputStream(newFile));
       File backFile = new File("D:\\upload\\还原.png");
       FileOutputStream backout = new FileOutputStream(backFile);
       backout.write(decryptFileByte);
       backout.close();
       System.out.print("2222222222222222");
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
   }
 }






