package com.amagana.settingsservice.dto;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AddressResponseDTO {

	private Long id;
	private String addressName;
	private String addressCity;
	private int addressNumber;
	private String addressEmail;
	private String addressPhone;
	private String addressProfessionalPhone;
	private LocalDateTime createdat;
	private String createdby;
	private String lastupdatedby;
	private LocalDateTime lastupdatedate;

}
