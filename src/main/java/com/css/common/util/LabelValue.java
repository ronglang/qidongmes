 package com.css.common.util;
 
 import java.io.Serializable;
 
 public class LabelValue
   implements Serializable, Comparable<LabelValue>
 {
   private static final long serialVersionUID = -8265585932317636852L;
   private String label;
   private String value;
   
   public LabelValue(String label, String value)
   {
     this.label = label;
     this.value = value;
   }
   
   public String getLabel()
   {
     return this.label;
   }
   
   public void setLabel(String label)
   {
     this.label = label;
   }
   
   public String getValue()
   {
     return this.value;
   }
   
   public void setValue(String value)
   {
     this.value = value;
   }
   
   public int compareTo(LabelValue tag)
   {
     if (this == tag) {
       return 0;
     }
     int lbc = getLabel().compareToIgnoreCase(tag.getLabel());
     if (lbc == 0) {
       return getValue().compareTo(tag.getValue());
     }
     return lbc;
   }
   
   public boolean equals(Object obj)
   {
     if (this == obj) {
       return true;
     }
     if (hashCode() == obj.hashCode()) {
       return true;
     }
     if ((obj instanceof LabelValue))
     {
       LabelValue tag = (LabelValue)obj;
       if ((getLabel().equals(tag.getLabel())) && (getValue().equals(tag.getValue()))) {
         return true;
       }
     }
     return false;
   }
   
   public String toString()
   {
     return "LabelValue[" + this.label + "=" + this.value + "];";
   }
 }






