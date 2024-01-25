package edu.fisa.lab.finance.role.domain;

import java.io.Serializable;

import edu.fisa.lab.finance.client.domain.entity.Client;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "client_roles")
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientRole {

	@EmbeddedId
	private Pk pk;


	@MapsId("clientId")
	@JoinColumn(name = "client_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Client client;


	@MapsId
	@JoinColumn(name = "role_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Role role;



	@EqualsAndHashCode
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Builder
	public static class Pk implements Serializable {
		@Column(name = "client_id")
		private Long clientId;


		@Column(name = "role_id")
		private Long roleId;
	}
}
