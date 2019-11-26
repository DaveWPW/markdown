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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	
	/**
	 * 图片上传
	 * 
	 * @param file
	 * @param path
	 * @return
	 */
    public static String uploadImage(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
    	response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取文件名
        String fileName = file.getOriginalFilename();
        //使用UUID重命名
        fileName = UUID.randomUUID() + fileName.substring(fileName.indexOf("."), fileName.length());
        //文件存放的路径
        File targetFile = new File("D:/markdown/upload/images/"+fileName);
        if(!targetFile.exists()){//是否存在
            targetFile.mkdirs();//创建目录
        }
        try {  
            file.transferTo(targetFile);//保存
        } catch (Exception e) {
            e.printStackTrace();
        }
        //全路径（协议类型://域名/项目名/命名空间/文件名）
        String url = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/doGetImageFile.do?name="+ fileName;
        return url;
    }
    /**
     * 获取图片
     * 
     * @param response
     * @param fileName
     */
    public static void getImageFile(HttpServletResponse response, String fileName) {
    	String filePath = "D:/markdown/upload/images/"+fileName;
        File file = new File(filePath);
        if (file.exists()) {
            FileInputStream fileInputStream = null;
            OutputStream outputStream = null;
            try {
            	fileInputStream = new FileInputStream(file);
            	outputStream = response.getOutputStream();
                int count = 0;
                byte[] buffer = new byte[1024 * 8];
                while ((count = fileInputStream.read(buffer)) != -1) {
                	outputStream.write(buffer, 0, count);
                	outputStream.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                	fileInputStream.close();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    
    /**
     * 上传MarkDown文件
     * 
     * @param fileName
     * @param data
     * @return
     */
    public static boolean uploadMarkDown(String fileName, String data) {
    	File file = new File("D:/markdown/upload/markdown/"+fileName+".md");
		try {
			if(!file.exists()){//是否存在
				file.createNewFile();//创建文件
			}
			FileOutputStream fileOutputStream = null;
			fileOutputStream = new FileOutputStream(file);
			return uploadMarkDownByOutputStream(fileOutputStream, data);
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
    public static boolean uploadMarkDownByOutputStream(OutputStream outputStream, String data) {
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
	public static String getMarkDownData(String fileName) {
		File file = new File("D:/markdown/upload/markdown/"+fileName+".md");
        if(!file.isFile()) {
        	return null;
        }
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
        	inputStreamReader = new InputStreamReader(new FileInputStream(file), "utf-8");
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
