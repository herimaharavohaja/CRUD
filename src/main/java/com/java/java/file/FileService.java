package com.java.java.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {
    String saveFile(MultipartFile file);

    boolean deleteFile(String url);
}
