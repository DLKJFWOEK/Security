package edu.fisa.lab.finance.client.domain.dto;

import edu.fisa.lab.finance.client.domain.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ClientDto {
	private String name;
	private String password;
	
	public Client toEntity(ClientDto clientDto) {
		return Client.builder()
				.name(clientDto.getName())
				.password(clientDto.getPassword())
				.build();
	}
}
