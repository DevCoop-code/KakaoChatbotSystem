package com.kakaochatbot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kakaochatbot.utils.MediaUtils;

@Controller
@RequestMapping("/image")
public class ImageUploadController 
{
	private static final Logger logger = LoggerFactory.getLogger(ImageUploadController.class);
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception
	{
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		logger.info("FILE NAME: " + fileName);
		
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		MediaType mType = MediaUtils.getMediaType(formatName);
		
		HttpHeaders headers = new HttpHeaders();
		
		in = new FileInputStream(uploadPath+"/"+fileName);
		
		if(mType != null)
		{
			logger.info("image file");
			
			headers.setContentType(mType);
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		}
		
		in.close();
		
		return entity;
	}
	
	@RequestMapping(value = "/uploadImageForm", method = RequestMethod.GET)
	public void uploadForm(Model model) throws Exception
	{
		List<String> folderList = showDirectoryList();
		
		if(folderList != null)
		{
			model.addAttribute("folderList", folderList);
		}else
		{
			model.addAttribute("folderList", folderList);
		}
	}
	
	@RequestMapping(value = "/uploadImageForm", method = RequestMethod.POST)
	public void uploadForm(MultipartFile file, Model model) throws Exception
	{
		logger.info("originalName: " + file.getOriginalFilename());
		logger.info("size: " + file.getSize());
		logger.info("contenttype: " + file.getContentType());
		
		String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
		
		model.addAttribute("savedName", savedName);
	}
	
	private String uploadFile(String originalName, byte[] fileData) throws Exception
	{	
		String savedName = null;
		
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		
		if(MediaUtils.getMediaType(formatName) != null)
		{
			UUID uid = UUID.randomUUID();
			
			savedName = uid.toString() + "_" + originalName;
			
			File target = new File(uploadPath, savedName);
			
			FileCopyUtils.copy(fileData, target);
		}
		
		return savedName;
	}
	
	private List<String> showDirectoryList() throws Exception
	{
		List<String> dirList = null;
		
		File dir = new File(uploadPath);
		File[] fileList = dir.listFiles();
		
		if(fileList.length != 0)
		{
			dirList = new ArrayList<String>();
			
			for(int i = 0; i < fileList.length; i++)
			{
				File file = fileList[i];
				if(file.isDirectory())
				{
					dirList.add(file.getName());
				}
			}	
		}
		return dirList;
	}
}
