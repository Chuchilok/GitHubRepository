package com.webpublish.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;

public class ProcessTool {
	
	public static String getPID(){
		// get name representing the running Java virtual machine.  
		  String name = ManagementFactory.getRuntimeMXBean().getName();  
		  String pid = name.split("@")[0];  
		  return pid;
	}
	
	
	public static int exeCmd(String shell) throws IOException {  
		  int success = 0;  
		  StringBuffer sb = new StringBuffer();  
		  BufferedReader br = null;  
		  
		  // get name representing the running Java virtual machine.  
		  String name = ManagementFactory.getRuntimeMXBean().getName();  
		  String pid = name.split("@")[0];  
		  
		  try {  
		    System.out.println("Starting to exec{ " + shell + " }. PID is: " + pid);  
		    Process process = null;  
		    ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", shell);  
		    pb.environment();  
		    pb.redirectErrorStream(true); // merge error stream into standard stream  
		    process = pb.start();  
		    if (process != null) {  
		      br = new BufferedReader(  
		          new InputStreamReader(process.getInputStream()), 1024);  
		      process.waitFor();  
		    } else {  
		      System.out.println("There is no PID found.");  
		    }  
		    sb.append("Ending exec right now, the result is：\n");  
		    String line = null;  
		    while (br != null && (line = br.readLine()) != null) {  
		      sb.append(line).append("\n");  
		    }  
		  } catch (Exception ioe) {  
				sb.append("Error occured when exec cmd：\n").append(ioe.getMessage())  
		        .append("\n");  
		  } finally {   
			System.out.println(sb.toString()); 
		    success = 1;  
		  }  
		  
		    System.out.println("end to exec{ " + shell + " }. PID is: " + pid);
			System.out.println("");  
			System.out.println("");		   
		  return success;  
		}  
	 
	
	public static String exeCmd2(String shell) throws IOException {  
		  int success = 0;  
		  StringBuffer sb = new StringBuffer(); 
		  StringBuffer result = new StringBuffer();
		  BufferedReader br = null;  
		  
		  // get name representing the running Java virtual machine.  
		  String name = ManagementFactory.getRuntimeMXBean().getName();  
		  String pid = name.split("@")[0];  
		  
		  try {  
		    System.out.println("Starting to exec{ " + shell + " }. PID is: " + pid);  
		    Process process = null;  
		    ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", shell);  
		    pb.environment();  
		    pb.redirectErrorStream(true); // merge error stream into standard stream  
		    process = pb.start();  
		    if (process != null) {  
		      br = new BufferedReader(  
		          new InputStreamReader(process.getInputStream()), 1024);  
		      process.waitFor();  
		    } else {  
		      System.out.println("There is no PID found.");  
		    }  
		    sb.append("Ending exec right now, the result is：\n");  
		    String line = null;  
		    while (br != null && (line = br.readLine()) != null) {  
		      sb.append(line).append("\n");  
		      result.append(line).append("\n");
		    }  
		  } catch (Exception ioe) {  
				sb.append("Error occured when exec cmd：\n").append(ioe.getMessage())  
		        .append("\n");  
		  } finally {   
			System.out.println(sb.toString()); 
		    success = 1;  
		  }  
		  
		    System.out.println("end to exec{ " + shell + " }. PID is: " + pid);
			System.out.println("");  
			System.out.println("");	
			if(success==1){
				return result.toString();
			}else{
				return null;
			}
		}  
}
