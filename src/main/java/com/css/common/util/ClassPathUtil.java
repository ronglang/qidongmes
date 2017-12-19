 package com.css.common.util;
 
 import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang.ClassUtils;
 
 @Deprecated
 public class ClassPathUtil
 {
   @SuppressWarnings({ "unchecked", "rawtypes" })
public static List<String> subPackagesAndClasses(String packageName)
   {
     List<String> packageClassList = new ArrayList();
     if (StringUtil.isEmpty(packageName)) {
       return packageClassList;
     }
     String rootPath = ClassPathUtil.class.getClassLoader().getResource("/").getPath().substring(1);
     String classFile = packageName.replace(".", "/");
     String newFile = rootPath + classFile;
     File file = new File(newFile);
     if (!file.exists()) {
       return packageClassList;
     }
     packageName = packageName + ".";
     if (file.isFile())
     {
       packageClassList.add(packageName);
     }
     else
     {
       String[] fiels = file.list();
       for (String filename : fiels) {
         if (filename.endsWith(".class")) {
           packageClassList.add(packageName + filename.substring(0, filename.length() - 6));
         } else {
           packageClassList.add(packageName + filename);
         }
       }
     }
     return packageClassList;
   }
   
public static Set<LabelValue> subPackagesClasses(String pack)
   {
     Set<LabelValue> packageClassList = new TreeSet<LabelValue>();
     String packageName = pack;
     String packageDirName = packageName.replace('.', '/');
     try
     {
       Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
       while (dirs.hasMoreElements())
       {
         URL url = (URL)dirs.nextElement();
         
         String protocol = url.getProtocol();
         if ("file".equals(protocol))
         {
           String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
           
 
           File dir = new File(filePath);
           
           File[] dirfiles = dir.listFiles(new FileFilter()
           {
             public boolean accept(File file)
             {
               return (file.isDirectory()) || (file.getName().endsWith(".class"));
             }
           });
           for (File file : dirfiles) {
             packageClassList.add(new LabelValue("data", pack + "." + file.getName().replaceAll(".class", "")));
           }
         }
         else if ("jar".equals(protocol))
         {
           JarFile jar = ((JarURLConnection)url.openConnection()).getJarFile();
           
           Enumeration<JarEntry> entries = jar.entries();
           while (entries.hasMoreElements())
           {
             JarEntry entry = (JarEntry)entries.nextElement();
             String name = entry.getName();
             if (name.charAt(0) == '/') {
               name = name.substring(1);
             }
             if (name.startsWith(packageDirName))
             {
               String pname = name.replace(packageDirName + "/", "");
               if ((name.endsWith(".class")) && (pname.indexOf('/') == -1)) {
                 packageClassList.add(new LabelValue("data", name.replaceAll(".class", "").replaceAll("/", ".")));
               } else if ((name.endsWith("/")) && (pname.lastIndexOf('/') == pname.indexOf('/'))) {
                 packageClassList.add(new LabelValue("data", name.replaceAll("/", ".").substring(0, name.length() - 1)));
               }
             }
           }
         }
       }
     }
     catch (IOException e) {}
     return packageClassList;
   }
   
   public static List<LabelValue> getClassMethods(String className)
   {
     List<LabelValue> clsMthd = new ArrayList<LabelValue>();
     try
     {
       Class<?> objectClass = Class.forName(className);
       Method[] methodsArr = objectClass.getDeclaredMethods();
       for (Method method : methodsArr) {
         clsMthd.add(new LabelValue("data", generateMethodString(method.getName(), method.getReturnType(), method.getParameterTypes())));
       }
     }
     catch (ClassNotFoundException e) {}
     Collections.sort(clsMthd);
     return clsMthd;
   }
   
   @SuppressWarnings("rawtypes")
protected static String generateMethodString(String methodName,  Class returnType, Class[] params)
   {
     StringBuilder methodStr = new StringBuilder();
     for (Class paramClass : params)
     {
       String shortName = paramClass.getSimpleName();
       methodStr.append(",").append(shortName);
     }
     String returnTypeName = "void";
     if (returnType != null) {
       returnTypeName = ClassUtils.getShortClassName(returnType).replace(";", "[]");
     }
     methodStr.replace(0, 1, returnTypeName + " " + methodName + "(").append(")");
     
     return methodStr.toString();
   }
   
   public static List<LabelValue> getSubPackages(String pkg)
   {
     List<LabelValue> pkgs = new ArrayList<LabelValue>();
     Package[] ps = Package.getPackages();
     for (Package p : ps)
     {
       String pn = p.getName();
       if (pn.startsWith(pkg)) {
         pkgs.add(new LabelValue("data", pn));
       }
     }
     Collections.sort(pkgs);
     return pkgs;
   }
 }






