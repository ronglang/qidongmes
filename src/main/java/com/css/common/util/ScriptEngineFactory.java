 package com.css.common.util;
 
 import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
 
 public class ScriptEngineFactory
 {
   static ScriptEngineManager engineManager = new ScriptEngineManager();
   
   public static ScriptEngine getJSScriptEngine()
   {
     ScriptEngine engine = engineManager.getEngineByName("js");
     return engine;
   }
 }






