package com.backend.ppinfo.dto.data;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class BoardRequest {

    @Size(min = 3, max = 64, message = "O nome deve conter entre 3 e 64 caracteres.")
    String name;

    @Size(max = 512, message = "A descricao deve conter no maximo 512 caracteres.")
    String description;

}
