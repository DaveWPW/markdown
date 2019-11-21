package common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	
	/**
	 * 图片上传工具类 
	 * 
	 * @param file
	 * @param path
	 * @return
	 */
    public static String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        fileName = UUID.randomUUID() + fileName.substring(fileName.indexOf("."), fileName.length());
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
     * 上传MarkDown文件
     * 
     * @param fileName
     * @param data
     * @return
     */
    public static boolean exportMarkDown(String fileName, String data) {
    	File file = new File("D:/markdown/"+fileName+".md");
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
     * 上传MarkDown文件输出流处理方法
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
	
	/**
	 * 获取文件数据
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileData(String fileName) {
		File file = new File("D:/markdown/"+fileName+".md");
        if(!file.isFile()) {
        	return null;
        }
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
        	inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
        	bufferedReader = new BufferedReader(inputStreamReader);
        	String text = "";
        	String line;
        	while((line = bufferedReader.readLine()) != null) {//包含该行内容的字符串，不包含任何行终止符，如果已到达流末尾，则返回 null
                text = text + line + "\r\n";
            }
        	return text;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
					bufferedReader = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
					inputStreamReader = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}