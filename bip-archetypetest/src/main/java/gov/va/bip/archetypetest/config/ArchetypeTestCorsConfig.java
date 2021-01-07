package gov.va.bip.archetypetest.config;

import gov.va.bip.framework.security.HttpProperties;
import gov.va.bip.archetypetest.ArchetypeTestProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ArchetypeTestCorsConfig {
    
    private ArchetypeTestProperties archetypetestProperties;
    private HttpProperties httpProperties;
    
    public ArchetypeTestCorsConfig(@Autowired ArchetypeTestProperties archetypetestProperties,
        @Autowired HttpProperties httpProperties) {
        this.archetypetestProperties = archetypetestProperties;
        this.httpProperties = httpProperties;
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                if (httpProperties.getCors() != null && httpProperties.getCors().isEnabled()) {
                    // UIEnablement
                    // The usage of the word origin in all lower case is problematic in the gen.sh script.
                    // After generation, replace with all lower case version or camel case version and uncomment.
        registry.addMapping("/api/v1/archetypetest/**").allowedOrigins(archetypetestProperties.getOrigins());
                }
            }
        };
    }
}
