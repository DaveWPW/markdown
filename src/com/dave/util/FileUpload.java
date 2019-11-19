package com.dave.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

    /**
     *图片上传工具类 
     */
    public static String upload(HttpServletRequest request, MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        fileName=UUID.randomUUID()+fileName.substring(fileName.indexOf("."),fileName.length());
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }
        try {  
            file.transferTo(targetFile);//保存
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        return fileName;
    }

    /**
     * 导出MarkDown文件
     */
    public static boolean exportMarkDown(String fileName, String data) {
    	File file = new File("D:/"+fileName+".md");
		try {
			if(!file.exists()){//是否存在
				file.createNewFile();//创建文件
			}
			FileOutputStream fileOutputStream = null;
			fileOutputStream = new FileOutputStream(file);
			return exportMarkDownByOutputStream(fileOutputStream, data);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
    
    /**
     * 导出MarkDown输出流方法
     * 
     * @param outputStream
     * @param data
     * @return
     */
    public static boolean exportMarkDownByOutputStream(OutputStream outputStream, String data) {
		boolean isSucess = false;
		OutputStreamWriter outputStreamWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			outputStreamWriter = new OutputStreamWriter(outputStream);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
			
			bufferedWriter.append(data);
			isSucess = true;
		} catch (Exception e) {
			e.printStackTrace();
			isSucess = false;
		} finally {
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
					bufferedWriter = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStreamWriter != null) {
				try {
					outputStreamWriter.close();
					outputStreamWriter = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
					outputStream = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return isSucess;
	}
    
}