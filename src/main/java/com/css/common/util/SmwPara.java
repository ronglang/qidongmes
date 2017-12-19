package com.css.common.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class SmwPara {
	private static int s2[] = new int[0];
	
	private static int[] getByte(int value){
		 ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.nativeOrder());
	     buffer.putInt(value);
	     byte[] sendkey = buffer.array();
	     int[] v = new int[sendkey.length];
	     for(int i = 0 ; i < sendkey.length ; i ++){
		    	v[i] = sendkey[i] & 0xff;
		 }
	     return v;
	}
	
	public static List<int[]> recv(byte[] sss1){
		//System.out.println("开始解析"+sss1.length+","+new Date());
		int[] ss1 = new int[sss1.length];
	     for(int i = 0 ; i < sss1.length ; i ++){
		    	ss1[i] = sss1[i] & 0xff;
		 }
		
		 List<int[]> listrst = new ArrayList<int[]>();
	     List<Integer> lsb = new ArrayList<Integer>();
	     lsb.clear();
	     int[] sendkey1 = getByte(543215);
	     int[] sendkey2 = getByte(567890);
	     int kx = 0;
	     // boolean nst = false;
	     
	     int rsidlen = 0;
	     if(s2.length > 0)
	    	 rsidlen = s2.length;
	     int[] s3 = new int[ss1.length + rsidlen];
	     if(s2.length > 0){
	    	 System.arraycopy(s2, 0, s3, 0,s2.length);
	     }
	     System.arraycopy(ss1,0,s3,rsidlen,s3.length-rsidlen);
    	 s2 = new int[0];
    	 listrst.clear();
    	 int j = s3.length;
    	 for(int i = 0 ; i < j ; i ++){
    		 if(i < s3.length - 3){
    			 if(s3[i] == sendkey1[0] && s3[i + 1] == sendkey1[1] && s3[i + 2] == sendkey1[2] && s3[i+3] == sendkey1[3]){
    				 // listrst.add(lsb.toArray(new int[lsb.size()]));
    				 int nn[] = new int[lsb.size()];
    				 for(int ii = 0 ; ii < lsb.size() ; ii ++){
    					 nn[ii] = lsb.get(ii);
    				 }
    				 listrst.add(nn);
    				 
    				 lsb.clear();
    				 i = i + 3;
    			 }
    			 else{
    				 if(s3[i] == sendkey2[0] && s3[i + 1] == sendkey2[1] && s3[i+2] == sendkey2[2] && s3[i+3] == sendkey2[3]){
    					 lsb.clear();
    					 i = i + 3;
    				 }
    				 else{
    					 lsb.add(s3[i]);
    					 kx = i;
    				 }
    			 }
    		 }
    		 else{
    			 if(s3.length - kx > 4)     lsb.add(s3[i]);
    		 }
    	 }
    	 if(lsb.size() > 5 && lsb.size() <  5000004){
    		 s2 = new int[lsb.size()];
    		 int[] temp = new int[s2.length];
    		 //temp = lsb.toArray(temp);
    		 for(int i = 0 ; i < temp.length ; i ++){
    			 s2[i] = lsb.get(i); //temp[i].intValue();
    		 }
    		 //System.arraycopy(lsb.toArray(temp), 0, s2, 0, lsb.size());
    	 }
    	 lsb.clear();
    	// System.out.println("解析结束,"+new Date());
	     return listrst;
	}
}
