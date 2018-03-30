package com.isonsoft.web.app.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.isonsoft.web.app.Beans.BasicFolderParamsBean;
import com.isonsoft.web.app.service.CheckHardDrive;
import com.isonsoft.web.app.service.SelectedLocalDrivesFolders;
import com.isonsoft.web.app.serviceImpl.CheckHardDriveImpl;
import com.isonsoft.web.app.serviceImpl.SelectedDriveFolderImpl;

@Controller
public class MainController {
	
	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) throws IOException {
		System.out.println("in controller----"+name);
			CheckHardDrive hardDriveList=new CheckHardDriveImpl();
			
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", hardDriveList.findDrivesInSystem());
		
		return mv;
	}
	
	@RequestMapping(value="/cello/{id}",method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String showMessages(@PathVariable("id") String name) throws IOException {
		System.out.println("in showMessages----"+name);
			SelectedLocalDrivesFolders selectDrvFlList=new SelectedDriveFolderImpl();
			
		ModelAndView mv = new ModelAndView("helloworld");
		//mv.addObject("message", selectDrvFlList.listFilesInDrive(name));
		Gson gson = new Gson();
        String jsonStudents = gson.toJson(selectDrvFlList.listFilesInDrive(name));
        System.out.println(jsonStudents);
		return jsonStudents;
	}
	
	
	@RequestMapping(value="/hello/{id}",method=RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	
	public ModelAndView showFoldersList(@PathVariable("id") String name) throws IOException {
		System.out.println("in showFoldersList----"+name);
			SelectedLocalDrivesFolders selectDrvFlList=new SelectedDriveFolderImpl();
			
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", selectDrvFlList.listFilesInDrive(name));
		
		return mv;
	}
	
	@RequestMapping(value="/sello",method=RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	
	public ModelAndView showSubFoldersList(@RequestParam(value = "name", required = false, defaultValue = "World") String name) throws IOException {
		System.out.println("in showSubFoldersList----"+name);
			SelectedLocalDrivesFolders selectDrvFlList=new SelectedDriveFolderImpl();
			
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", selectDrvFlList.listFilesInDrive(name));
		
		return mv;
	}

}
