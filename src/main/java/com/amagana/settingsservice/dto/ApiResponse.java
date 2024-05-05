package com.amagana.settingsservice.dto;

import java.util.List;

import com.amagana.settingsservice.enums.StatusResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record ApiResponse<T>(StatusResponse status, String message, List<ErrorsDTO> errors, T results) {

}
