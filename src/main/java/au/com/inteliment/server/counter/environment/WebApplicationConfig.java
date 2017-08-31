package au.com.inteliment.server.counter.environment;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Read the environment properties..
 * <p>
 * Reading content file name from application.properties
 * @author rjavaria
 *
 */
@Configuration
@ConfigurationProperties(ignoreUnknownFields = false,
						 prefix = "counter.web.env")
public class WebApplicationConfig extends WebMvcConfigurerAdapter {
    
    @NotBlank
    private String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
