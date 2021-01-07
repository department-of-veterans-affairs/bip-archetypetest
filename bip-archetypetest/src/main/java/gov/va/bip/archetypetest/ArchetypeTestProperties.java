package gov.va.bip.archetypetest;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * The Class ApplicationConfig serves 2 purposes.
 * (1) It provides a bean that classes can use to get to our properties.
 * (2) It allows the actuator and the /configprops endpoint to surface all bound/bindable properties (as documentation)
 *
 * @author akulkarni
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "bip-archetypetest")
@RefreshScope // refresh properties after app running
public class ArchetypeTestProperties {

	@NotNull
	private String env;

	@NotNull
	private String propSource;

	@NotNull
	private String sampleProperty;

	@NotNull
	private String password;

	// UIEnablement
	// The usage of the word origin in all lower case is problematic in the gen.sh script.
	// After generation, replace with all lower case version or camel case version and uncomment.
	@NotNull
	private String[] origins;

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getPropSource() {
		return propSource;
	}

	public void setPropSource(String propSource) {
		this.propSource = propSource;
	}

	public String getSampleProperty() {
		return sampleProperty;
	}

	public void setSampleProperty(String sampleProperty) {
		this.sampleProperty = sampleProperty;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

// UIEnablement
// The usage of the word origin in all lower case is problematic in the gen.sh script.
// After generation, replace with all lower case version or camel case version and uncomment.
	public String[] getOrigins() {
		return origins;
	}

	public void setOrigins(String[] origins) {
		this.origins = origins;
	}
}
