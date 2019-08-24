package cn.lz.hospital.utils.file;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
/**
* @Description:    文件压缩工具
* @Author:         zhuwc
* @CreateDate:     2019/1/23 16:34
* @Version:        1.0
*/
public class ZipUtils {
	private static final int BUFFER = 8192; 
	private File zipFile;
	
	public ZipUtils(String pathName) {
		this.zipFile = new File(pathName);
	}
	
	public void compress(String srcPathName) {
		File srcdir = new File(srcPathName);
		if (!srcdir.exists()) {
			throw new RuntimeException(srcPathName + "不存在！");
		}
		/* 判断是目录还是文件 */  
        if (srcdir.isDirectory()) {  
            this.compressDirectory(srcdir);  
        } else {  
            this.compressFile(srcdir);  
        } 
    } 
	
	/** 压缩一个文件 */  
    private void compressFile(File file) {
    	OutputStream outputStream = null;
    	CheckedOutputStream cos = null;
    	ZipOutputStream out = null;
    	BufferedInputStream bis = null;
    	try {
    		outputStream = new FileOutputStream(zipFile);
    		cos = new CheckedOutputStream(outputStream, new CRC32());
    		out = new ZipOutputStream(cos);
    		bis = new BufferedInputStream(new FileInputStream(file));
    		ZipEntry entry = new ZipEntry(file.getName());  
            out.putNextEntry(entry);  
            int count;  
            byte data[] = new byte[BUFFER];  
            while ((count = bis.read(data, 0, BUFFER)) != -1) {  
                out.write(data, 0, count);  
            }
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(bis!=null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out!=null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(cos!=null) {
				try {
					cos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(outputStream!=null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    }
	
	/** 压缩一个目录 */  
    private void compressDirectory(File srcdir) {
    	Project prj = new Project();  
        Zip zip = new Zip();  
        zip.setProject(prj);  
        zip.setDestFile(zipFile);  
        FileSet fileSet = new FileSet();  
        fileSet.setProject(prj);  
        fileSet.setDir(srcdir);  
        zip.addFileset(fileSet);  
        zip.execute(); 
    }
    
    public static void deleteFile(File file) {
		if (file.isFile()) {// 判断是否是文件
			file.delete();// 删除文件
		} else if (file.isDirectory()) {// 否则如果它是一个目录
			File[] files = file.listFiles();// 声明目录下所有的文件 files[];
			for (int i = 0; i < files.length; i++) {// 遍历目录下所有的文件
				deleteFile(files[i]);// 把每个文件用这个方法进行迭代
			}
			file.delete();// 删除文件夹
		}
	}

}
