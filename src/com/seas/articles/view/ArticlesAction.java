package com.seas.articles.view;

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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.seas.articles.bean.Articles;
import com.seas.articles.service.ArticlesService;

@Controller
@RequestMapping("/articles")
public class ArticlesAction {

	@Resource
	private ArticlesService articlesService;

	@ResponseBody
	@RequestMapping("/list")
	public List<Articles> selectArticles(@RequestParam(name="title",required=false) String title) {
		return articlesService.selectArticles(title);
	}
	
	/*@RequestMapping("/query/{id}")
	public String queryArticles(@PathVariable("id") Integer id,Model model) {
		Articles article = articlesService.queryArticles(id);
		model.addAttribute("article",article);
		return "/detail";
	}*/
	
	@ResponseBody
	@RequestMapping("/query/{id}")
	public Articles queryArticles(@PathVariable("id") Integer id,Model model) {
		return articlesService.queryArticles(id);
	}

	@RequestMapping("/delete")
	public ResponseEntity<Map<String, Boolean>> deleteArticles(Integer id) {
		Map<String, Boolean> map = articlesService.deleteArticles(id);
		return new ResponseEntity<Map<String, Boolean>>(map, HttpStatus.OK);

	}

	@RequestMapping("/insert")
	public ResponseEntity<Map<String, Boolean>> insertArticles(@Valid Articles articles, BindingResult result) {
		if (result.hasErrors()) {
			Map<String,Boolean> map = new HashMap<String,Boolean>();
			map.put("status", false);
			return new ResponseEntity<Map<String, Boolean>>(map, HttpStatus.OK);
		}
		Map<String, Boolean> map = articlesService.insertArticles(articles);
		return new ResponseEntity<Map<String, Boolean>>(map, HttpStatus.OK);
	}

	@RequestMapping("/update")
	public ResponseEntity<Map<String, Boolean>> updateArticles(Articles articles) {
		Map<String, Boolean> map = articlesService.updateArticles(articles);
		return new ResponseEntity<Map<String, Boolean>>(map, HttpStatus.OK);

	}
	
	/**
	 * 
	 * 上传文件
	 * @param articles
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
