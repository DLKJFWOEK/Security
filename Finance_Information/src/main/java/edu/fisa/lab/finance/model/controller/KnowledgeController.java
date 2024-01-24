package edu.fisa.lab.finance.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.fisa.lab.finance.model.dto.KnowledgeDto;
import edu.fisa.lab.finance.model.service.KnowledgeService;

@Controller
public class KnowledgeController {

	@Autowired
	private KnowledgeService knowledgeService;
	
	@GetMapping("/knowledgeOne")
	public KnowledgeDto knowledgeOne(@Param("knowledgeNo") Long knowledgeNo) {
		
		KnowledgeDto one = knowledgeService.knowledgeOne(knowledgeNo);
		return one;
	}
	
	@GetMapping("/knowledgeAll")
	public ModelAndView knowledgeAll() {
		List<KnowledgeDto> all = knowledgeService.knowledgeAll();
		ModelAndView mv = new ModelAndView();
		mv.addObject("knowledgeAll", all);
		mv.setViewName("allpage");
		System.out.println("=== " + all);
		return mv;
	}
//	@RequestMapping(path = "/knowledgeSave", method = RequestMethod.POST)
//	public String knowledgeInsert(KnowledgeDto knowledgeDto) {
//		knowledgeService.knowledgeInsert(knowledgeDto);
//		return "redirect:/main.jsp";	
//			
//	}
}
