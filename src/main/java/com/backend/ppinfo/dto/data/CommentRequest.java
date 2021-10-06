package com.backend.ppinfo.dto.data;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CommentRequest {

    @NotNull(message = "O conteudo do comentario nao deve ser nulo.")
    @Size(min = 1, max = 4096, message = "O tamanho do comentario deve ser entre 1 e 4096.")
    private String content;

}
