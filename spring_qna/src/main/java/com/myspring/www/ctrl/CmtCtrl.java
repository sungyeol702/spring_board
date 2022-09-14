package com.myspring.www.ctrl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myspring.www.domain.CmtVO;
import com.myspring.www.domain.PagingVO;
import com.myspring.www.handler.PagingHandler;
import com.myspring.www.service.CmtService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/cmt/*")
@Controller
public class CmtCtrl {
	@Inject
	private CmtService csv;
	
	@GetMapping(value = "/{bno}/{pageNo}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PagingHandler> spread(@PathVariable("bno") long bno,
			@PathVariable("pageNo") int pageNo){
		PagingVO pgvo = new PagingVO(pageNo, 10);
		return new ResponseEntity<PagingHandler>(csv.spread(bno, pgvo),HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/post", consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> post(@RequestBody CmtVO cvo){
		log.info(">>>post{}",cvo);
		return csv.post(cvo) > 0 ?  
				new ResponseEntity<String>("1", HttpStatus.OK) 
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping(value="/{cno}", consumes = "application/json", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> edit(@PathVariable("cno") long cno,
			@RequestBody CmtVO cvo){
		return csv.modify(cvo) > 0? new ResponseEntity<String>("1", HttpStatus.OK) 
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value= "/{cno}/{bno}", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("cno") long cno,
										 @PathVariable("bno") long bno){ 
		return csv.remove(cno,bno)>0? new ResponseEntity<String>("1", HttpStatus.OK) 
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	} 
	

	
	
}
