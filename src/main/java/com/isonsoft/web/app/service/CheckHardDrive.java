package com.isonsoft.web.app.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.isonsoft.web.app.Beans.BasicFolderParamsBean;


public interface CheckHardDrive {

	public List<BasicFolderParamsBean> findDrivesInSystem() throws IOException;
}
