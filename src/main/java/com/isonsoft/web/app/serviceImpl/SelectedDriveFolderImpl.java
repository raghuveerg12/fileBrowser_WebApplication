package com.isonsoft.web.app.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.isonsoft.web.app.Beans.BasicFolderParamsBean;
import com.isonsoft.web.app.service.SelectedLocalDrivesFolders;

public class SelectedDriveFolderImpl implements SelectedLocalDrivesFolders {

	
	
	public List<BasicFolderParamsBean> listFilesInDrive(String directoryName) throws IOException {
		System.out.println("directory name--"+directoryName);
		List<BasicFolderParamsBean> listOfFolders=new ArrayList<BasicFolderParamsBean>();
		directoryName=directoryName+"\\";
        File directory = new File(directoryName);
        
        //get all the files from a directory
        File[] fList = directory.listFiles();  
      
        for (File file : fList){   
        	
        	 BasicFolderParamsBean basicFolderParamBean=new BasicFolderParamsBean();
       	
            if (file.isDirectory()){
            	basicFolderParamBean.setFileName(file.getName());
            	basicFolderParamBean.setFileSize(FileUtils.byteCountToDisplaySize(file.length()));
            	
            	 
          	   
          	   
          	   basicFolderParamBean=someParameterForFile(file,basicFolderParamBean);
             	
             	  
             	  basicFolderParamBean.setFileExtension("DIR");
             	  basicFolderParamBean.setFilePath(file.getAbsolutePath());
             	  basicFolderParamBean.setFreeSpaceOnDrive(FileUtils.byteCountToDisplaySize(file.getTotalSpace()));
         	   listOfFolders.add(basicFolderParamBean);

           } else if(file.isFile()){
			   basicFolderParamBean.setFileName(file.getName());
			   
			   basicFolderParamBean.setFileSize(FileUtils.byteCountToDisplaySize(file.length()));
			   
			   basicFolderParamBean=someParameterForFile(file,basicFolderParamBean);
			
			  
			  basicFolderParamBean.setFileExtension(FilenameUtils.getExtension(file.getAbsolutePath()));
			  
			  basicFolderParamBean.setFilePath(file.getAbsolutePath());
			  basicFolderParamBean.setFreeSpaceOnDrive(FileUtils.byteCountToDisplaySize(file.getTotalSpace()));

        	   listOfFolders.add(basicFolderParamBean);

           }
            
            	
            	
            }
        
	
		return listOfFolders;
	}
	

public static String dateFormat = "dd-MM-yyyy hh:mm";
private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
	
	public static String ConvertMilliSecondsToFormattedDate(Long milliSeconds){
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTimeInMillis(milliSeconds);
	    return simpleDateFormat.format(calendar.getTime());
	}

	public static String convertBytesToGB(Long bytes){
		Long bytesConverted=(bytes/(1024*1024*1024));
		String convertedToGb=bytesConverted.toString()+"-GB";
		if(bytesConverted<=0){
			bytesConverted=(bytes/(1024*1024));
			convertedToGb=bytesConverted.toString()+"-MB";
		}
		return convertedToGb;
		
	}
	
	public static BasicFolderParamsBean someParameterForFile(File file2, BasicFolderParamsBean basicFolderParamBean) throws IOException{
		Path file =file2.toPath() ;
		BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);

		
		basicFolderParamBean.setFileLastModified(attr.lastModifiedTime());
		basicFolderParamBean.setFileLastOpened(attr.lastAccessTime());
		basicFolderParamBean.setFileCreationDate(attr.creationTime());
		return basicFolderParamBean;
	}

}
