package edu.fisa.lab.finance.model.dto;

import edu.fisa.lab.finance.model.entity.Category;
import edu.fisa.lab.finance.model.entity.Knowledge;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class KnowledgeDto {
	
	private String name;
	private String content;
	private Category category;
	
	public Knowledge toEntity(KnowledgeDto knowledgeDto) {
		return Knowledge.builder()
				.name(knowledgeDto.getName())
				.content(knowledgeDto.getContent())
				.category(knowledgeDto.getCategory())
				.build();
	}
	
	public KnowledgeDto toDto(Knowledge knowledge) {
		return KnowledgeDto.builder()
				.name(knowledge.getName())
				.content(knowledge.getContent())
				.category(knowledge.getCategory())
				.build();
	}
	
	public KnowledgeDto(String name, String content, Category category) {
		super();
		this.name = name;
		this.content = content;
		this.category = category;
	}
}
