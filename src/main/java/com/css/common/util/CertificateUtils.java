 package com.css.common.util;
 
 import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.crypto.Cipher;
 
 public class CertificateUtils
 {
   public static final String KEY_STORE = "JKS";
   public static final String X509 = "X.509";
   //private static final int CACHE_SIZE = 2048;
   //private static final int MAX_ENCRYPT_BLOCK = 117;
   //private static final int MAX_DECRYPT_BLOCK = 128;
   
   public static PrivateKey getPrivateKey(String keyStorePath, String alias, String password)
     throws Exception
   {
     KeyStore keyStore = getKeyStore(keyStorePath, password);
     PrivateKey privateKey = (PrivateKey)keyStore.getKey(alias, password.toCharArray());
     return privateKey;
   }
   
   public static KeyStore getKeyStore(String keyStorePath, String password)
     throws Exception
   {
     FileInputStream in = new FileInputStream(keyStorePath);
     KeyStore keyStore = KeyStore.getInstance("JKS");
     keyStore.load(in, password.toCharArray());
     in.close();
     return keyStore;
   }
   
   public static PublicKey getPublicKey(String certificatePath)
     throws Exception
   {
     Certificate certificate = getCertificate(certificatePath);
     PublicKey publicKey = certificate.getPublicKey();
     return publicKey;
   }
   
   public static Certificate getCertificate(String certificatePath)
     throws Exception
   {
     CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
     FileInputStream in = new FileInputStream(certificatePath);
     Certificate certificate = certificateFactory.generateCertificate(in);
     in.close();
     return certificate;
   }
   
   public static Certificate getCertificate(String keyStorePath, String alias, String password)
     throws Exception
   {
     KeyStore keyStore = getKeyStore(keyStorePath, password);
     Certificate certificate = keyStore.getCertificate(alias);
     return certificate;
   }
   
   public static byte[] encryptByPrivateKey(byte[] data, String keyStorePath, String alias, String password)
     throws Exception
   {
     PrivateKey privateKey = getPrivateKey(keyStorePath, alias, password);
     Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
     cipher.init(1, privateKey);
     int inputLen = data.length;
     ByteArrayOutputStream out = new ByteArrayOutputStream();
     int offSet = 0;
     
     int i = 0;
     while (inputLen - offSet > 0)
     {
       byte[] cache;
       if (inputLen - offSet > 117) {
         cache = cipher.doFinal(data, offSet, 117);
       } else {
         cache = cipher.doFinal(data, offSet, inputLen - offSet);
       }
       out.write(cache, 0, cache.length);
       i++;
       offSet = i * 117;
     }
     byte[] encryptedData = out.toByteArray();
     out.close();
     return encryptedData;
   }
   
   public static byte[] encryptFileByPrivateKey(String filePath, String keyStorePath, String alias, String password)
     throws Exception
   {
     byte[] data = fileToByte(filePath);
     return encryptByPrivateKey(data, keyStorePath, alias, password);
   }
   
   public static void encryptFileByPrivateKey(String srcFilePath, String destFilePath, String keyStorePath, String alias, String password)
     throws Exception
   {
     PrivateKey privateKey = getPrivateKey(keyStorePath, alias, password);
     Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
     cipher.init(1, privateKey);
     File srcFile = new File(srcFilePath);
     FileInputStream in = new FileInputStream(srcFile);
     File destFile = new File(destFilePath);
     if (!destFile.getParentFile().exists()) {
       destFile.getParentFile().mkdirs();
     }
     destFile.createNewFile();
     OutputStream out = new FileOutputStream(destFile);
     byte[] data = new byte[117];
     while (in.read(data) != -1)
     {
       byte[] encryptedData = cipher.doFinal(data);
       out.write(encryptedData, 0, encryptedData.length);
       out.flush();
     }
     out.close();
     in.close();
   }
   
   public static String encryptFileToBase64ByPrivateKey(String filePath, String keyStorePath, String alias, String password)
     throws Exception
   {
     byte[] encryptedData = encryptFileByPrivateKey(filePath, keyStorePath, alias, password);
     return Base64Utils.encode(encryptedData);
   }
   
   public static byte[] decryptByPrivateKey(byte[] encryptedData, String keyStorePath, String alias, String password)
     throws Exception
   {
     PrivateKey privateKey = getPrivateKey(keyStorePath, alias, password);
     Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
     cipher.init(2, privateKey);
     
     int inputLen = encryptedData.length;
     ByteArrayOutputStream out = new ByteArrayOutputStream();
     int offSet = 0;
     
     int i = 0;
     while (inputLen - offSet > 0)
     {
       byte[] cache;
       if (inputLen - offSet > 128) {
         cache = cipher.doFinal(encryptedData, offSet, 128);
       } else {
         cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
       }
       out.write(cache, 0, cache.length);
       i++;
       offSet = i * 128;
     }
     byte[] decryptedData = out.toByteArray();
     out.close();
     return decryptedData;
   }
   
   public static byte[] encryptByPublicKey(byte[] data, String certificatePath)
     throws Exception
   {
     PublicKey publicKey = getPublicKey(certificatePath);
     Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
     cipher.init(1, publicKey);
     int inputLen = data.length;
     ByteArrayOutputStream out = new ByteArrayOutputStream();
     int offSet = 0;
     
     int i = 0;
     while (inputLen - offSet > 0)
     {
       byte[] cache;
       if (inputLen - offSet > 117) {
         cache = cipher.doFinal(data, offSet, 117);
       } else {
         cache = cipher.doFinal(data, offSet, inputLen - offSet);
       }
       out.write(cache, 0, cache.length);
       i++;
       offSet = i * 117;
     }
     byte[] encryptedData = out.toByteArray();
     out.close();
     return encryptedData;
   }
   
   public static byte[] decryptByPublicKey(byte[] encryptedData, String certificatePath)
     throws Exception
   {
     PublicKey publicKey = getPublicKey(certificatePath);
     Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
     cipher.init(2, publicKey);
     int inputLen = encryptedData.length;
     ByteArrayOutputStream out = new ByteArrayOutputStream();
     int offSet = 0;
     
     int i = 0;
     while (inputLen - offSet > 0)
     {
       byte[] cache;
       if (inputLen - offSet > 128) {
         cache = cipher.doFinal(encryptedData, offSet, 128);
       } else {
         cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
       }
       out.write(cache, 0, cache.length);
       i++;
       offSet = i * 128;
     }
     byte[] decryptedData = out.toByteArray();
     out.close();
     return decryptedData;
   }
   
   public static void decryptFileByPublicKey(String srcFilePath, String destFilePath, String certificatePath)
     throws Exception
   {
     PublicKey publicKey = getPublicKey(certificatePath);
     Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
     cipher.init(2, publicKey);
     File srcFile = new File(srcFilePath);
     FileInputStream in = new FileInputStream(srcFile);
     File destFile = new File(destFilePath);
     if (!destFile.getParentFile().exists()) {
       destFile.getParentFile().mkdirs();
     }
     destFile.createNewFile();
     OutputStream out = new FileOutputStream(destFile);
     byte[] data = new byte[''];
     while (in.read(data) != -1)
     {
       byte[] decryptedData = cipher.doFinal(data);
       out.write(decryptedData, 0, decryptedData.length);
       out.flush();
     }
     out.close();
     in.close();
   }
   
   public static byte[] sign(byte[] data, String keyStorePath, String alias, String password)
     throws Exception
   {
     X509Certificate x509Certificate = (X509Certificate)getCertificate(keyStorePath, alias, password);
     
     KeyStore keyStore = getKeyStore(keyStorePath, password);
     
     PrivateKey privateKey = (PrivateKey)keyStore.getKey(alias, password.toCharArray());
     
     Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
     signature.initSign(privateKey);
     signature.update(data);
     return signature.sign();
   }
   
   public static String signToBase64(byte[] data, String keyStorePath, String alias, String password)
     throws Exception
   {
     return Base64Utils.encode(sign(data, keyStorePath, alias, password));
   }
   
   public static String signFileToBase64WithEncrypt(String filePath, String keyStorePath, String alias, String password)
     throws Exception
   {
     byte[] encryptedData = encryptFileByPrivateKey(filePath, keyStorePath, alias, password);
     return signToBase64(encryptedData, keyStorePath, alias, password);
   }
   
   @Deprecated
   public static byte[] signFile(String filePath, String keyStorePath, String alias, String password)
     throws Exception
   {
     byte[] sign = new byte[0];
     
     X509Certificate x509Certificate = (X509Certificate)getCertificate(keyStorePath, alias, password);
     
     KeyStore keyStore = getKeyStore(keyStorePath, password);
     
     PrivateKey privateKey = (PrivateKey)keyStore.getKey(alias, password.toCharArray());
     
     Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
     signature.initSign(privateKey);
     File file = new File(filePath);
     if (file.exists())
     {
       FileInputStream in = new FileInputStream(file);
       FileChannel fileChannel = in.getChannel();
       MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
       signature.update(byteBuffer);
       fileChannel.close();
       in.close();
       sign = signature.sign();
     }
     return sign;
   }
   
   public static byte[] generateFileSign(String filePath, String keyStorePath, String alias, String password)
     throws Exception
   {
     byte[] sign = new byte[0];
     
     X509Certificate x509Certificate = (X509Certificate)getCertificate(keyStorePath, alias, password);
     
     KeyStore keyStore = getKeyStore(keyStorePath, password);
     
     PrivateKey privateKey = (PrivateKey)keyStore.getKey(alias, password.toCharArray());
     
     Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
     signature.initSign(privateKey);
     File file = new File(filePath);
     if (file.exists())
     {
       FileInputStream in = new FileInputStream(file);
       byte[] cache = new byte[2048];
       int nRead = 0;
       while ((nRead = in.read(cache)) != -1) {
         signature.update(cache, 0, nRead);
       }
       in.close();
       sign = signature.sign();
     }
     return sign;
   }
   
   public static String signFileToBase64(String filePath, String keyStorePath, String alias, String password)
     throws Exception
   {
     return Base64Utils.encode(generateFileSign(filePath, keyStorePath, alias, password));
   }
   
   public static boolean verifySign(byte[] data, String sign, String certificatePath)
     throws Exception
   {
     X509Certificate x509Certificate = (X509Certificate)getCertificate(certificatePath);
     
     PublicKey publicKey = x509Certificate.getPublicKey();
     
     Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
     signature.initVerify(publicKey);
     signature.update(data);
     return signature.verify(Base64Utils.decode(sign));
   }
   
   @Deprecated
   public static boolean verifyFileSign(String filePath, String sign, String certificatePath)
     throws Exception
   {
     boolean result = false;
     
     X509Certificate x509Certificate = (X509Certificate)getCertificate(certificatePath);
     
     PublicKey publicKey = x509Certificate.getPublicKey();
     
     Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
     signature.initVerify(publicKey);
     File file = new File(filePath);
     if (file.exists())
     {
       byte[] decodedSign = Base64Utils.decode(sign);
       FileInputStream in = new FileInputStream(file);
       FileChannel fileChannel = in.getChannel();
       MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
       signature.update(byteBuffer);
       in.close();
       result = signature.verify(decodedSign);
     }
     return result;
   }
   
   public static boolean validateFileSign(String filePath, String sign, String certificatePath)
     throws Exception
   {
     boolean result = false;
     
     X509Certificate x509Certificate = (X509Certificate)getCertificate(certificatePath);
     
     PublicKey publicKey = x509Certificate.getPublicKey();
     
     Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
     signature.initVerify(publicKey);
     File file = new File(filePath);
     if (file.exists())
     {
       byte[] decodedSign = Base64Utils.decode(sign);
       FileInputStream in = new FileInputStream(file);
       byte[] cache = new byte[2048];
       int nRead = 0;
       while ((nRead = in.read(cache)) != -1) {
         signature.update(cache, 0, nRead);
       }
       in.close();
       result = signature.verify(decodedSign);
     }
     return result;
   }
   
   public static boolean verifyBase64Sign(String base64String, String sign, String certificatePath)
     throws Exception
   {
     byte[] data = Base64Utils.decode(base64String);
     return verifySign(data, sign, certificatePath);
   }
   
   public static boolean verifyBase64SignWithDecrypt(String base64String, String sign, String certificatePath)
     throws Exception
   {
     byte[] encryptedData = Base64Utils.decode(base64String);
     byte[] data = decryptByPublicKey(encryptedData, certificatePath);
     return verifySign(data, sign, certificatePath);
   }
   
   public static boolean verifyFileSignWithDecrypt(String encryptedFilePath, String sign, String certificatePath)
     throws Exception
   {
     byte[] encryptedData = fileToByte(encryptedFilePath);
     byte[] data = decryptByPublicKey(encryptedData, certificatePath);
     return verifySign(data, sign, certificatePath);
   }
   
   public static boolean verifyCertificate(Certificate certificate)
   {
     return verifyCertificate(new Date(), certificate);
   }
   
   public static boolean verifyCertificate(Date date, Certificate certificate)
   {
     boolean isValid = true;
     try
     {
       X509Certificate x509Certificate = (X509Certificate)certificate;
       x509Certificate.checkValidity(date);
     }
     catch (Exception e)
     {
       isValid = false;
     }
     return isValid;
   }
   
   public static boolean verifyCertificate(Date date, String certificatePath)
   {
     try
     {
       Certificate certificate = getCertificate(certificatePath);
       return verifyCertificate(certificate);
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     return false;
   }
   
   public static boolean verifyCertificate(Date date, String keyStorePath, String alias, String password)
   {
     try
     {
       Certificate certificate = getCertificate(keyStorePath, alias, password);
       return verifyCertificate(certificate);
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     return false;
   }
   
   public static boolean verifyCertificate(String keyStorePath, String alias, String password)
   {
     return verifyCertificate(new Date(), keyStorePath, alias, password);
   }
   
   public static boolean verifyCertificate(String certificatePath)
   {
     return verifyCertificate(new Date(), certificatePath);
   }
   
   public static byte[] fileToByte(String filePath)
     throws Exception
   {
     byte[] data = new byte[0];
     File file = new File(filePath);
     if (file.exists())
     {
       FileInputStream in = new FileInputStream(file);
       ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
       byte[] cache = new byte[2048];
       int nRead = 0;
       while ((nRead = in.read(cache)) != -1)
       {
         out.write(cache, 0, nRead);
         out.flush();
       }
       out.close();
       in.close();
       data = out.toByteArray();
     }
     return data;
   }
   
   public static void byteArrayToFile(byte[] bytes, String filePath)
     throws Exception
   {
     InputStream in = new ByteArrayInputStream(bytes);
     File destFile = new File(filePath);
     if (!destFile.getParentFile().exists()) {
       destFile.getParentFile().mkdirs();
     }
     destFile.createNewFile();
     OutputStream out = new FileOutputStream(destFile);
     byte[] cache = new byte[2048];
     int nRead = 0;
     while ((nRead = in.read(cache)) != -1)
     {
       out.write(cache, 0, nRead);
       out.flush();
     }
     out.close();
     in.close();
   }
 }






