 package com.css.common.util;
 
 import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
 
 public class ZipCompress
 {
   private static boolean isCreateSrcDir = true;
   
   public static void main(String[] args)
     throws IOException
   {
     String src = "E:\\办公自动化系统解决方案.doc";
     String decompressDir = "E:\\解压测试路径";
     String archive = "E:\\中文压缩文件.zip";
     
     zipFileWidthApacheZip(src, archive, Boolean.valueOf(isCreateSrcDir));
     
 
 
 
 
     unZipApacheZipFile(archive, decompressDir);
   }
   
   public static void zipFileWidthApacheZip(String src, String archive, Boolean isCreateSrcDir)
     throws IOException
   {
     FileOutputStream f = new FileOutputStream(archive);
     
     CheckedOutputStream csum = new CheckedOutputStream(f, new CRC32());
     
     ZipOutputStream zos = new ZipOutputStream(csum);
     
     zos.setEncoding("GBK");
     BufferedOutputStream out = new BufferedOutputStream(zos);
     
     zos.setMethod(8);
     
     zos.setLevel(-1);
     
     File srcFile = new File(src);
     if ((!srcFile.exists()) || ((srcFile.isDirectory()) && (srcFile.list().length == 0)))
     {
       out.close();
       throw new FileNotFoundException("File must exist and  ZIP file must have at least one entry.");
     }
     src = src.replaceAll("\\\\", "/");
     String prefixDir = null;
     if (srcFile.isFile()) {
       prefixDir = src.substring(0, src.lastIndexOf("/") + 1);
     } else {
       prefixDir = src.replaceAll("/$", "") + "/";
     }
     if ((prefixDir.indexOf("/") != prefixDir.length() - 1) && (isCreateSrcDir.booleanValue())) {
       prefixDir = prefixDir.replaceAll("[^/]+/$", "");
     }
     writeRecursive(zos, out, srcFile, prefixDir);
     
     out.close();
   }
   
   public static void unZipApacheZipFile(String archive, String decompressDir)
     throws IOException, FileNotFoundException, ZipException
   {
     ZipFile zf = new ZipFile(archive, "GBK");
     
     Enumeration<?> e = zf.getEntries();
     while (e.hasMoreElements())
     {
       org.apache.tools.zip.ZipEntry ze2 = (org.apache.tools.zip.ZipEntry)e.nextElement();
       String entryName = ze2.getName();
       String path = decompressDir + "/" + entryName;
       if (ze2.isDirectory())
       {
         File decompressDirFile = new File(path);
         if (!decompressDirFile.exists()) {
           decompressDirFile.mkdirs();
         }
       }
       else
       {
         String fileDir = path.substring(0, path.lastIndexOf("/"));
         File fileDirFile = new File(fileDir);
         if (!fileDirFile.exists()) {
           fileDirFile.mkdirs();
         }
         BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(decompressDir + "/" + entryName));
         
 
         BufferedInputStream bi = new BufferedInputStream(zf.getInputStream(ze2));
         byte[] readContent = new byte[1024];
         int readCount = bi.read(readContent);
         while (readCount != -1)
         {
           bos.write(readContent, 0, readCount);
           readCount = bi.read(readContent);
         }
         bos.close();
       }
     }
     zf.close();
   }
   
   public static void unZipInputStream(String archive, String decompressDir)
     throws FileNotFoundException, IOException
   {
     FileInputStream fi = new FileInputStream(archive);
     CheckedInputStream csumi = new CheckedInputStream(fi, new CRC32());
     ZipInputStream in2 = new ZipInputStream(csumi);
     BufferedInputStream bi = new BufferedInputStream(in2);
     java.util.zip.ZipEntry ze;
     while ((ze = in2.getNextEntry()) != null)
     {
       String entryName = ze.getName();
       if (ze.isDirectory())
       {
         File decompressDirFile = new File(decompressDir + "/" + entryName);
         if (!decompressDirFile.exists()) {
           decompressDirFile.mkdirs();
         }
       }
       else
       {
         BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(decompressDir + "/" + entryName));
         
         byte[] buffer = new byte[1024];
         int readCount = bi.read(buffer);
         while (readCount != -1)
         {
           bos.write(buffer, 0, readCount);
           readCount = bi.read(buffer);
         }
         bos.close();
       }
     }
     bi.close();
   }
   
   private static void writeRecursive(ZipOutputStream zos, BufferedOutputStream bo, File srcFile, String prefixDir)
     throws IOException, FileNotFoundException
   {
     String filePath = srcFile.getAbsolutePath().replaceAll("\\\\", "/").replaceAll("//", "/");
     if (srcFile.isDirectory()) {
       filePath = filePath.replaceAll("/$", "") + "/";
     }
     String entryName = filePath.replace(prefixDir, "").replaceAll("/$", "");
     if (srcFile.isDirectory())
     {
       if (!"".equals(entryName))
       {
         org.apache.tools.zip.ZipEntry zipEntry = new org.apache.tools.zip.ZipEntry(entryName + "/");
         zos.putNextEntry(zipEntry);
       }
       File[] srcFiles = srcFile.listFiles();
       for (int i = 0; i < srcFiles.length; i++) {
         writeRecursive(zos, bo, srcFiles[i], prefixDir);
       }
     }
     else
     {
       BufferedInputStream bi = new BufferedInputStream(new FileInputStream(srcFile));
       
 
 
       org.apache.tools.zip.ZipEntry zipEntry = new org.apache.tools.zip.ZipEntry(entryName);
       zos.putNextEntry(zipEntry);
       byte[] buffer = new byte[1024];
       int readCount = bi.read(buffer);
       while (readCount != -1)
       {
         bo.write(buffer, 0, readCount);
         readCount = bi.read(buffer);
       }
       bo.flush();
       
       bi.close();
     }
   }
   
   public static boolean isCreateSrcDir()
   {
     return isCreateSrcDir;
   }
   
   public static void setCreateSrcDir(boolean isCreateSrcDir)
   {
	   ZipCompress.isCreateSrcDir = isCreateSrcDir;
   }
 }






