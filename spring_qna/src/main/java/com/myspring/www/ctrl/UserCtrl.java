package com.myspring.www.ctrl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.www.domain.UserVO;
import com.myspring.www.service.UserService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/user/*")
public class UserCtrl {
	@Inject
	UserService usv;
	
	@GetMapping("/register")
	public void register() {
	}
	@PostMapping("/register")
	public String register(UserVO uvo){
		int isUp = usv.register(uvo);
		return "redirect:/";
	}
	@GetMapping("/list")
	public void list(Model model) {
		List<UserVO> list = usv.getList();
		model.addAttribute("list", list);
	}
	
	@GetMapping({"/detail","modify"})
	public void detail(Model model , @RequestParam("id") String id) {
	model.addAttribute("uvo", usv.getDitail(id));
	}
	@PostMapping("/modify")
	public String modify(UserVO uvo) {
		int isOk = usv.modify(uvo);
		return "redirect:/user/detail?id="+uvo.getId();
	}
	@PostMapping("remove")
	public String remove(@RequestParam("id") String id ,
			HttpSession ses, RedirectAttributes rttr) {
		ses.removeAttribute("ses");
		ses.invalidate();
		int isUp = usv.remove(id);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public void login() {
		
	}
	@PostMapping("/login")
	public String login(UserVO uvo,HttpSession ses, RedirectAttributes rttr ) {
		UserVO sesUvo = usv.login(uvo);
		if(sesUvo != null) {
			ses.setAttribute("ses", sesUvo);
			ses.setMaxInactiveInterval(600); //10분
			rttr.addFlashAttribute("isLogin", 1);
			return"redirect:/";
		}else {
			rttr.addFlashAttribute("isLogin", 0);
			return"redirect:/user/login";
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession ses, RedirectAttributes rttr) {
		ses.removeAttribute("ses");
		ses.invalidate();
		rttr.addFlashAttribute("isLogout", 1);
		return "redirect:/";
	}
	@ResponseBody
	@PostMapping(value="/dupleCheck", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public String dupleCheck(@RequestBody HashMap<String, String> map) {
		log.info(">>>{}", map.get("id"));
		int isExist = usv.dupleCheck(map.get("id"));
		return isExist >0? "1" : "0";
	}
	
	
	
}
