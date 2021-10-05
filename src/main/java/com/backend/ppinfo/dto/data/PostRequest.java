package com.backend.ppinfo.dto.data;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class PostRequest {

    @Size(min = 1, max = 128, message = "O tamanho do post deve estar entre 1 e 128 caracteres")
    private String title;

    @Size(min = 1, max = 4096, message = "O tamanho do conteudo deve estar entre 1 e 4096 caracteres")
    private String content;

}
