package com.webpublish.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class RunCommand {
	
	public RunCommand(){
		
	}
	public static String exeCmd(String commandStr) {
		BufferedReader br = null;
		try {
			Process p = Runtime.getRuntime().exec(commandStr);
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			return new String(sb);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally
		{
			if (br != null)
			{
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}
	public String runCommand(String cmd,int tp){
	     StringBuffer buf = new StringBuffer(10000);
	     String rt="-1";
	  try {
	   Process pos = Runtime.getRuntime().exec(cmd);
	   pos.waitFor();
	   if(tp==1){
	    if(pos.exitValue()==0){
	     rt="1";
	    }
	   }else{
	    InputStreamReader ir = new InputStreamReader(pos.getInputStream());
	    LineNumberReader input = new LineNumberReader(ir);
	    String ln="";
	    while ((ln =input.readLine()) != null) {
	        buf.append(ln+"<br>");
	    }
	    rt = buf.toString();
	    input.close();
	    ir.close();
	   }
	  } catch (java.io.IOException e) {
	   rt=e.toString();
	  }catch (Exception e) {
	   rt=e.toString();
	  }
	     return rt;
	    }
}
