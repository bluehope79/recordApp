package com.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class FileManager {

	public static boolean doFileDownload(HttpServletResponse resp, String sFileName, String oFileName, String path) {

		try{
			
			String filePath = path + File.separator + sFileName;
			
			System.out.println(filePath);
			
			if(oFileName == null || oFileName.equals("")){
				oFileName = sFileName;
			}
			
			File f = new File(filePath);
			
			if(!f.exists()){
				return false;
			}
			
			resp.setContentType("application/octet-stream"); //.txt에 .
			resp.setHeader("Content-disposition", "attachment;fileName=" + oFileName);
			
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
			
			OutputStream out = resp.getOutputStream();
			
			int data;
			byte bytes[] = new byte[4096];
			
			while ((data = bis.read(bytes,0,4096)) != -1) {
				
				out.write(bytes,0,data);
				
			}
			
			out.flush();
			out.close();
			bis.close();
					
		}catch(Exception e) {
			System.out.println("FileManager - doFileDownload : " + e.toString());
			return false;
		}
		
		return true;
	}
	
	public static boolean doFileDelete(String fileName, String path) {
		
		try{
			
			String filePath = path + File.separator + fileName;
			File f = new File(filePath);
			
			if(f.exists()){
				f.delete();
			}
			
			
		} catch (Exception e) {
			System.out.println("FileManager - doFileDelete : " + e.toString());
			return false;
		}
		
		return true;
		
	}

}
