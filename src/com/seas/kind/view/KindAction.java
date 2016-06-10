package com.seas.kind.view;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.seas.kind.bean.Kind;
import com.seas.kind.service.KindService;

@Controller
@RequestMapping("/kind")
public class KindAction {

	@Resource
	private KindService kindService;

	@ResponseBody
	@RequestMapping("/list")
	public List<Kind> selectKind(@RequestParam(name="title",required=false) String title) {
		return kindService.selectKind(title);
	}

	@ResponseBody
	@RequestMapping("/query")
	public Kind queryKind(@RequestParam("id") Integer id) {
		return kindService.queryKind(id);
	}

	@RequestMapping("/delete")
	public ResponseEntity<Map<String, Boolean>> deleteKind(Integer id) {
		Map<String, Boolean> map = kindService.deleteKind(id);
		return new ResponseEntity<Map<String, Boolean>>(map, HttpStatus.OK);

	}

	@RequestMapping("/insert")
	public ResponseEntity<Map<String, Boolean>> insertKind(@Valid Kind kind, BindingResult result) {
		if (result.hasErrors()) {
			Map<String,Boolean> map = new HashMap<String,Boolean>();
			map.put("status", false);
			return new ResponseEntity<Map<String, Boolean>>(map, HttpStatus.OK);
		}
		Map<String, Boolean> map = kindService.insertKind(kind);
		return new ResponseEntity<Map<String, Boolean>>(map, HttpStatus.OK);
	}

	@RequestMapping("/update")
	public ResponseEntity<Map<String, Boolean>> updateKind(Kind kind) {
		Map<String, Boolean> map = kindService.updateKind(kind);
		return new ResponseEntity<Map<String, Boolean>>(map, HttpStatus.OK);

	}
	
	/**
	 * 
	 * 上传文件
	 * @param kind
	 * @param files
	 * @return
	 */
	@RequestMapping("/uploadImg")
	public ResponseEntity<Map<String,Object>> uploadImage(MultipartHttpServletRequest files,HttpServletRequest request){
		Map<String,Object> map =new HashMap<String, Object>();
		map.put("success", true);
		Iterator<String> fileName = files.getFileNames();
		while (fileName.hasNext()) {
			MultipartFile file = files.getFile(fileName.next());
			if (!file.isEmpty()) {
				try {
					FileUtils.copyInputStreamToFile(file.getInputStream(),new File(request.getServletContext().getRealPath("/upload"),file.getOriginalFilename()));
					map.put("file_path", "/seas/upload/"+file.getOriginalFilename());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				map.put("success", false);
			}
		}
		return  new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
