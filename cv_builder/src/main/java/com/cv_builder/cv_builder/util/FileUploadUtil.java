package com.cv_builder.cv_builder.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileUploadUtil {

    public static byte[] uploadFile(MultipartFile file) throws IOException {
        return file.getBytes();
    }
}