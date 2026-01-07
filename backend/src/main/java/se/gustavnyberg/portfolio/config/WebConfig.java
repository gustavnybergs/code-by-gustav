package se.gustavnyberg.portfolio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS-konfiguration för att tillåta frontend prata med backend.
 *
 * Utan denna fil blockerar webbläsaren alla anrop från frontend (Vercel)
 * till backend (Render) eftersom de ligger på olika domäner.
 *
 * Konfigurationen tillåter:
 * - Localhost för lokal utveckling
 * - Vercel-domäner för produktion och preview
 * - Min egen domän (gustavnyberg.se)
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOriginPatterns(
                        "http://localhost:3000",
                        "https://code-by-gustav.vercel.app",
                        "https://*.vercel.app",
                        "https://gustavnyberg.se",
                        "https://www.gustavnyberg.se"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
