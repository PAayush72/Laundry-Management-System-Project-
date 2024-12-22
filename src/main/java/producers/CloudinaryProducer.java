package producers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import service.CloudinaryService;

@ApplicationScoped
public class CloudinaryProducer {

    @Produces
    @ApplicationScoped
    public CloudinaryService produceCloudinaryService() {
        String cloudName = null;
        String apiKey = null;
        String apiSecret = null;

        try {
            // Load environment variables
            cloudName = System.getenv("CLOUDINARY_CLOUD_NAME");
            apiKey = System.getenv("CLOUDINARY_API_KEY");
            apiSecret = System.getenv("CLOUDINARY_API_SECRET");

            System.out.println("Cloud Name: " + cloudName);
            System.out.println("API Key: " + apiKey);
            System.out.println("API Secret: " + apiSecret);

            // Fallback to properties file if any environment variable is missing
            if (cloudName == null || apiKey == null || apiSecret == null) {
                Properties properties = loadProperties("cloudinary.properties");
                cloudName = properties.getProperty("cloud_name", cloudName);
                apiKey = properties.getProperty("api_key", apiKey);
                apiSecret = properties.getProperty("api_secret", apiSecret);
            }

            System.out.println("Cloud Name: " + cloudName);
            System.out.println("API Key: " + apiKey);
            System.out.println("API Secret: " + apiSecret);
            // Validate configuration
            if (cloudName == null || apiKey == null || apiSecret == null) {
                throw new IllegalStateException("Cloudinary configuration is missing or incomplete.");
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to load Cloudinary configuration", e);
        }

        return new CloudinaryService(cloudName, apiKey, apiSecret);
    }

    private Properties loadProperties(String fileName) throws IOException {
        Properties properties = new Properties();
        try ( InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IOException(fileName + " file not found in resources.");
            }
            properties.load(input);
        }
        return properties;
    }
}
