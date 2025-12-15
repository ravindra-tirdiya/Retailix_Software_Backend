package com.ecommers.billingsoftware.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ecommers.billingsoftware.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new RuntimeException("Only image files (jpg, png, jpeg, webp) are allowed!");
            }

            String fileName = UUID.randomUUID().toString();

            var uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", "billing-app",
                            "public_id", fileName,  // 👈 store with a fixed public_id
                            "resource_type", "image",
                            "overwrite", true
                    )
            );

            return uploadResult.get("secure_url").toString();

        } catch (Exception e) {
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteFile(String imageUrl) {
        try {
            if (imageUrl == null || imageUrl.isEmpty()) {
                return false;
            }

            // Example URL: https://res.cloudinary.com/demo/image/upload/v1690389030/billing-app/abc123.jpg
            String publicId = imageUrl.substring(
                    imageUrl.indexOf("billing-app/"),
                    imageUrl.lastIndexOf(".")
            );

            Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.asMap("resource_type", "image"));
            return "ok".equals(result.get("result"));
        } catch (Exception e) {
            throw new RuntimeException("File deletion failed: " + e.getMessage());
        }
    }
}
