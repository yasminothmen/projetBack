package Pfe_Education.mongo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig {
    @Configuration
    public class webConfig implements WebMvcConfigurer {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/pdfs/**")
                    .addResourceLocations("file:/pdfs/") // Chemin relatif au répertoire de l'application
                    .setCachePeriod(0);
        }
    }
}