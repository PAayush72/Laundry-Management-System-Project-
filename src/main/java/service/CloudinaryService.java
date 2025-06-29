package service;

import com.cloudinary.Cloudinary;
import java.io.File;
import java.io.FileOutputStream;
import org.primefaces.model.file.UploadedFile;

import javax.enterprise.inject.Vetoed;
import java.io.InputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Vetoed
public class CloudinaryService {

    private Cloudinary cloudinary;

    // No-argument constructor for CDI proxying
    public CloudinaryService() {
    }

    // Constructor with Cloudinary credentials
    public CloudinaryService(String cloudName, String apiKey, String apiSecret) {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        this.cloudinary = new Cloudinary(config);
    }

    // Upload an image from UploadedFile (PrimeFaces) using InputStream
    public String uploadImage(UploadedFile uploadedFile) throws IOException {
        if (uploadedFile == null || uploadedFile.getSize() == 0) {
            throw new IllegalArgumentException("No file uploaded or file is empty.");
        }

        try ( InputStream imageStream = uploadedFile.getInputStream()) {
            // Create a temporary file
            File tempFile = File.createTempFile("upload_", ".tmp");
            try ( FileOutputStream fos = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = imageStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            }

            // Cloudinary upload options
            Map<String, Object> options = new HashMap<>();
            options.put("public_id", generateUniqueImageName(uploadedFile.getFileName(), ".jpg"));
            options.put("resource_type", "auto");

            String uploadPreset = "thapastore";  // Replace with your actual upload preset name
            options.put("upload_preset", uploadPreset);

            // Upload image to Cloudinary
            Map<String, Object> uploadResult = cloudinary.uploader().upload(tempFile, options);

            // Clean up the temporary file
            tempFile.delete();

            return (String) uploadResult.get("url");
        } catch (IOException e) {
            throw new IOException("Image upload to Cloudinary failed: " + e.getMessage(), e);
        }
    }

    // Generate a unique image name using the original file name and a random number
    private String generateUniqueImageName(String originalFileName, String extension) {
        return originalFileName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + System.currentTimeMillis() + extension;
    }
}
