package br.com.rodolfo.biscoiteria.infrastructure.service.storage;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import br.com.rodolfo.biscoiteria.core.storage.StorageProperties;
import br.com.rodolfo.biscoiteria.domain.service.FotoStorageService;

@Service
public class LocalFotoStorageService implements FotoStorageService {

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public void armazenar(NovaFoto novaFoto) {
        try {
            Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());

            FileCopyUtils.copy(novaFoto.getInputStream(),
                Files.newOutputStream(arquivoPath));

        } catch(Exception ex) {
            throw new StorageException("Não foi possível armazenar arquivo.", ex);
        }
    }

    @Override
    public void remover(String nomeArquivo) {
        try {
            Path path = getArquivoPath(nomeArquivo);
    
            Files.deleteIfExists(path);
        } catch (Exception ex) {
            throw new StorageException("Não foi possível excluir arquivo.", ex);
        }
    }

    @Override
    public InputStream recuperar(String nomeArquivo) {
        try {
            return Files.newInputStream(getArquivoPath(nomeArquivo));
        } catch (Exception ex) {
            throw new StorageException("Não foi possível recuperar o arquivo.", ex);
        }
    }

    private Path getArquivoPath(String nomeArquivo) {
        return storageProperties.getLocal()
            .getDiretorioFotos().resolve(Path.of(nomeArquivo));
    }
}
