package edu.fisa.lab.finance.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import edu.fisa.lab.finance.model.dto.KnowledgeDto;
import edu.fisa.lab.finance.model.entity.Knowledge;
import edu.fisa.lab.finance.model.repository.KnowledgeRepository;

@Service
public class KnowledgeService {
	
	@Autowired
	private KnowledgeRepository knowledgeRepository;
	
	@Transactional
	public KnowledgeDto knowledgeOne(Long knowledgeNo) {
		Optional<Knowledge> one = knowledgeRepository.findById(knowledgeNo);
		KnowledgeDto dto = new KnowledgeDto().toDto(one.get());
		return dto;
	}
	@Transactional
	public List<KnowledgeDto> knowledgeAll(){
		List<Knowledge> kno = knowledgeRepository.findAll();
		List<KnowledgeDto> kList = kno.stream().map(k -> new KnowledgeDto().toDto(k)).toList();
		return kList;
	}
}
