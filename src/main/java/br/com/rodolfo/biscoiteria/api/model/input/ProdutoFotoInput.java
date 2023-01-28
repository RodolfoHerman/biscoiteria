package br.com.rodolfo.biscoiteria.api.model.input;

import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import br.com.rodolfo.biscoiteria.core.validation.FileContentType;
import br.com.rodolfo.biscoiteria.core.validation.FileSize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoFotoInput {

    @NotNull
    @FileSize(max = "3MB")
    @FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
    private MultipartFile arquivo;
}
