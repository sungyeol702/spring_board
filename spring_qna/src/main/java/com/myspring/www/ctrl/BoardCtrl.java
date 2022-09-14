package com.myspring.www.ctrl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.www.domain.BoardDTO;
import com.myspring.www.domain.BoardVO;
import com.myspring.www.domain.ImageVO;
import com.myspring.www.domain.PagingVO;
import com.myspring.www.handler.FileHandler;
import com.myspring.www.handler.PagingHandler;
import com.myspring.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/board/*")
public class BoardCtrl {
	@Inject
	BoardService bsv;
	@Inject
	private FileHandler fhd;
	@GetMapping("/list")
	public void list(Model model,PagingVO pgvo) {
		model.addAttribute("list",bsv.getList(pgvo));
		int totalCount = bsv.getTotalCount(pgvo);
		model.addAttribute("pgn", new PagingHandler(pgvo, totalCount));
		
	}
	@GetMapping("/register")
	public void register() {
	}
	@PostMapping("/register")
	public String register(BoardVO bvo, RedirectAttributes rttr,
			@RequestParam(name ="fileAttached", required =false) MultipartFile[] images) {
		List<ImageVO> imageList = null;
		if(images[0].getSize()>0) {
			imageList = fhd.getFileList(images);
			bvo.setFileCount(imageList.size());
		}
		int isUp = bsv.register(new BoardDTO(bvo, imageList));
		rttr.addFlashAttribute("isUp",isUp);
		return "redirect:/board/list";
	}
	@GetMapping({"/detail","/modify"})
	public void detail(Model model,@RequestParam("bno") long bno,
			@ModelAttribute("pgvo") PagingVO pgvo) {
		model.addAttribute("bdto",bsv.getDetail(bno));
		
	}
	@PostMapping("/modify")
	public String modify(BoardVO bvo,RedirectAttributes rttr, PagingVO pgvo,
			@RequestParam(name ="fileAttached", required =false) MultipartFile[] images) {
		List<ImageVO> imageList = null;
		if(images[0].getSize()>0) {
			imageList = fhd.getFileList(images);
			bvo.setFileCount(imageList.size());
		}
		int isUp= bsv.modify(new BoardDTO(bvo, imageList));
		rttr.addFlashAttribute("isUp",isUp);
		rttr.addAttribute("pageNo", pgvo.getPageNo());
		rttr.addAttribute("qty", pgvo.getQty());
		rttr.addAttribute("scope", pgvo.getScope());
		rttr.addAttribute("kwd", pgvo.getKwd());
		return "redirect:/board/detail?bno="+bvo.getBno();
	}
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") long bno,RedirectAttributes rttr,
			 PagingVO pgvo) {
		log.info(">>>>remove{}",bno);
		int isDel= bsv.remove(bno);
		rttr.addFlashAttribute("isDel",isDel);
		rttr.addAttribute("pageNo", pgvo.getPageNo());
		rttr.addAttribute("qty", pgvo.getQty());
		rttr.addAttribute("scope", pgvo.getScope());
		rttr.addAttribute("kwd", pgvo.getKwd());
		return "redirect:/board/list"; 
	}
	@DeleteMapping(value="/image/{uuid}", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> removeFile(@PathVariable("uuid") String uuid){
		return bsv.removeIamge(uuid) > 0 ?
				new ResponseEntity<String>("1", HttpStatus.OK) : 
					new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
