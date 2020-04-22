package gov.va.bip.archetypetest.api.provider;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import gov.va.bip.framework.log.BipLogger;
import gov.va.bip.framework.log.BipLoggerFactory;
import gov.va.bip.framework.validation.Defense;
import gov.va.bip.archetypetest.ArchetypeTestService;
import gov.va.bip.archetypetest.api.model.v1.SampleRequest;
import gov.va.bip.archetypetest.api.model.v1.SampleResponse;
import gov.va.bip.archetypetest.model.SampleDomainRequest;
import gov.va.bip.archetypetest.model.SampleDomainResponse;
import gov.va.bip.archetypetest.transform.impl.SampleByPidDomainToProvider;
import gov.va.bip.archetypetest.transform.impl.SampleByPidProviderToDomain;

/**
 * An adapter between the provider layer api/model, and the services layer interface/model.
 *
 * @author aburkholder
 */
@Component
public class ServiceAdapter {
	/** Class logger */
	private static final BipLogger LOGGER = BipLoggerFactory.getLogger(ServiceAdapter.class);

	/** Transform Provider (REST) request to Domain (service) request */
	private SampleByPidProviderToDomain sampleByPidProvider2Domain = new SampleByPidProviderToDomain();
	/** Transform Domain (service) response to Provider (REST) response */
	private SampleByPidDomainToProvider sampleByPidDomain2Provider = new SampleByPidDomainToProvider();

	/** The service layer API contract for processing sampleByPid() requests */
	@Autowired
	@Qualifier("ARCHETYPETEST_SERVICE_IMPL")
	private ArchetypeTestService archetypetestService;

	/**
	 * Field defense validations.
	 */
	@PostConstruct
	public void postConstruct() {
		Defense.notNull(archetypetestService);
		Defense.notNull(sampleByPidProvider2Domain);
		Defense.notNull(sampleByPidDomain2Provider);
	}

	/**
	 * Adapt the sampleByPid(..) request mapping method to the equivalent service layer method.
	 *
	 * @param sampleRequest - the Provider layer request model object
	 * @return SampleResponse - the Provider layer response model object
	 */
	SampleResponse sampleByPid(final SampleRequest sampleRequest) {
		// transform provider request into domain request
		LOGGER.debug("Transforming from provider sampleRequest to sampleDomainRequest");
		SampleDomainRequest domainRequest = sampleByPidProvider2Domain.convert(sampleRequest);

		// get domain response from the service (domain) layer
		LOGGER.debug("Calling archetypetestService.sampleFindByParticipantID");
		SampleDomainResponse domainResponse = archetypetestService.sampleFindByParticipantID(domainRequest);

		// transform domain response into provider response
		LOGGER.debug("Transforming from domainResponse to providerResponse");
		return sampleByPidDomain2Provider.convert(domainResponse);
	}
}
