 package com.css.common.util;
 
 import java.io.File;
 
 public class Zip
 {
   public static void zip(String zipFileName, String inputFile)
     throws Exception
   {
     ZipCompress.zipFileWidthApacheZip(inputFile, zipFileName, Boolean.valueOf(true));
   }
   
   public static File unzip(String zipFileName, String outputDirectory)
     throws Exception
   {
     ZipCompress.unZipApacheZipFile(zipFileName, outputDirectory);
     return new File(outputDirectory);
   }
 }






