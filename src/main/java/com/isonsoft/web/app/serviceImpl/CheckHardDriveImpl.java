package com.isonsoft.web.app.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.isonsoft.web.app.Beans.BasicFolderParamsBean;
import com.isonsoft.web.app.service.CheckHardDrive;

public class CheckHardDriveImpl implements CheckHardDrive{
	
	public List<BasicFolderParamsBean> findDrivesInSystem() throws IOException {
		List<BasicFolderParamsBean> drivesList=new ArrayList<BasicFolderParamsBean>();
		
		for (Path root : FileSystems.getDefault().getRootDirectories()) {
			 BasicFolderParamsBean basicFolderParamBean=new BasicFolderParamsBean();

			  FileStore fileStore = Files.getFileStore(root);
			  basicFolderParamBean.setFileName(root.toString());
          	  basicFolderParamBean.setFileSize(FileUtils.byteCountToDisplaySize(fileStore.getTotalSpace()));
          	 
       	   
          	  basicFolderParamBean=someParameterForFiles(root.toFile(),basicFolderParamBean);
          	
          	  
          	  basicFolderParamBean.setFileExtension("DIR");
          	  basicFolderParamBean.setFilePath(root.toString());
          	  basicFolderParamBean.setFreeSpaceOnDrive(FileUtils.byteCountToDisplaySize(fileStore.getTotalSpace()));

          	  
          	  drivesList.add(basicFolderParamBean);
			}
		System.out.println("Drives in the DIsk----"+drivesList.toString());
		return drivesList;
	}

	public static String convertBytesToMb(long v) {
		if (v < 1024) 
			return v + " B";
	    int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
	    return String.format("%.1f %sB", (double)v / (1L << (z*10)), " KMGTPE".charAt(z));
	}
	
	public static BasicFolderParamsBean someParameterForFiles(File file2, BasicFolderParamsBean basicFolderParamBean) throws IOException{
		Path file =file2.toPath() ;
		BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);

		System.out.println("creationTime: " + attr.creationTime());
		System.out.println("lastAccessTime: " + attr.lastAccessTime());
		System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
		basicFolderParamBean.setFileLastModified(attr.lastModifiedTime());
		basicFolderParamBean.setFileLastOpened(attr.lastAccessTime());
		basicFolderParamBean.setFileCreationDate(attr.creationTime());
		return basicFolderParamBean;
	}
	

}
