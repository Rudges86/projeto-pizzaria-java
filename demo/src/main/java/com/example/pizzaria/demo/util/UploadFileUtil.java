package com.example.pizzaria.demo.util;

import com.example.pizzaria.demo.exception.FailSaveFileException;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class UploadFileUtil {
    private static final String DIRETORIO_UPLOAD = "src/main/java/com/example/pizzaria/demo/imagens";

    public static boolean uploadFile(MultipartFile arquivo) {
        if (arquivo.isEmpty()) {
            return false;
        }

        try {
            // Obtenha o caminho absoluto do diretório de upload
            String diretorioUpload = new File(DIRETORIO_UPLOAD).getAbsolutePath();
            // Salve o arquivo no diretório de upload
            String caminhoCompleto = diretorioUpload + File.separator + arquivo.getOriginalFilename();
            arquivo.transferTo(new File(caminhoCompleto));
            System.out.println("Upload bem-sucedido! Caminho: " + caminhoCompleto);

            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
