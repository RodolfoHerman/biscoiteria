package br.com.rodolfo.biscoiteria.core.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import br.com.rodolfo.biscoiteria.core.storage.StorageProperties.TipoStorage;
import br.com.rodolfo.biscoiteria.domain.service.FotoStorageService;
import br.com.rodolfo.biscoiteria.infrastructure.service.storage.LocalFotoStorageService;
import br.com.rodolfo.biscoiteria.infrastructure.service.storage.S3FotoStorageService;

@Configuration
public class StorageConfig {

    @Autowired
    private StorageProperties storageProperties; 

    @Bean
    @ConditionalOnProperty(name = "biscoiteria.storage.tipoStorage", havingValue = "s3")
    AmazonS3 amazonS3() {
        var credenciais = new BasicAWSCredentials(
            storageProperties.getS3().getIdChaveAcesso(),
            storageProperties.getS3().getChaveAcessoSecreta()
        );

        return AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(credenciais))
            .withRegion(storageProperties.getS3().getRegiao())
        .build();
    }

    @Bean
    FotoStorageService getFotoStorageService() {
        if(TipoStorage.S3.equals(storageProperties.getTipoStorage())) {
            return new S3FotoStorageService();
        }

        return new LocalFotoStorageService();
    }
}
