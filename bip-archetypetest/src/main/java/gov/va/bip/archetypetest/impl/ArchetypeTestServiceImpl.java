package gov.va.bip.archetypetest.impl;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import gov.va.bip.framework.cache.BipCacheUtil;
import gov.va.bip.framework.exception.BipException;
import gov.va.bip.framework.exception.BipRuntimeException;
import gov.va.bip.framework.log.BipLogger;
import gov.va.bip.framework.log.BipLoggerFactory;
import gov.va.bip.framework.messages.MessageKeys;
import gov.va.bip.framework.messages.MessageSeverity;
import gov.va.bip.archetypetest.ArchetypeTestService;
import gov.va.bip.archetypetest.messages.ArchetypeTestMessageKeys;
import gov.va.bip.archetypetest.model.SampleDomainRequest;
import gov.va.bip.archetypetest.model.SampleDomainResponse;
import gov.va.bip.archetypetest.model.SampleInfoDomain;
import gov.va.bip.archetypetest.utils.CacheConstants;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

/**
 * Implementation class for the ArchetypeTest Service.
 * The class demonstrates the implementation of resilience4j circuit breaker pattern for read
 * operations. When there is a failure the fallback method is invoked and the
 * response is returned from the cache
 *
 * @author akulkarni
 */
@Service(value = ArchetypeTestServiceImpl.BEAN_NAME)
@Component
@Qualifier("ARCHETYPETEST_SERVICE_IMPL")
@RefreshScope
public class ArchetypeTestServiceImpl implements ArchetypeTestService {
	private static final BipLogger LOGGER = BipLoggerFactory.getLogger(ArchetypeTestServiceImpl.class);

	/** Bean name constant */
	public static final String BEAN_NAME = "archetypetestServiceImpl";

	/** The cache manager (redis implementation) */
	@Autowired
	private CacheManager cacheManager;

	/**
	 * Viability checks before the application is put into service.
	 */
	@PostConstruct
	void postConstruct() {
	}

	/**
	 * Implementation of the service (domain) layer API.
	 * <p>
	 * If graceful degredation is possible, add
	 * {@code fallbackMethod = "sampleFindByParticipantIDFallBack"}
	 * to the {@code @CircuitBreaker}.
	 * <p>
	 * {@inheritDoc}
	 *
	 */
	@Override
	@CachePut(value = CacheConstants.CACHENAME_ARCHETYPETEST_SERVICE,
			key = "#root.methodName + T(gov.va.bip.framework.cache.BipCacheUtil).createKey(#sampleDomainRequest.participantID)",
			unless = "T(gov.va.bip.framework.cache.BipCacheUtil).checkResultConditions(#result)")
	@CircuitBreaker(name = "sampleFindByParticipantID", fallbackMethod = "sampleFindByParticipantIDFallBack")
	@Bulkhead(name = "sampleFindByParticipantID")
	@RateLimiter(name = "sampleFindByParticipantID")
	@Retry(name = "sampleFindByParticipantID")
	public SampleDomainResponse sampleFindByParticipantID(final SampleDomainRequest sampleDomainRequest) {

		String cacheKey = "sampleFindByParticipantID" + BipCacheUtil.createKey(sampleDomainRequest.getParticipantID());

		// try from cache
		SampleDomainResponse response = new SampleDomainResponse();
		try {
			Cache cache = null;
			if ((cacheManager != null) && ((cache = cacheManager.getCache(CacheConstants.CACHENAME_ARCHETYPETEST_SERVICE)) != null)
					&& (cache.get(cacheKey) != null)) {
				LOGGER.debug("sampleFindByParticipantID returning cached data");
				response = cache.get(cacheKey, SampleDomainResponse.class);
				return response;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		LOGGER.debug("sampleFindByParticipantID no cached data found");

		// send hard coded data ... normally would get from db or partner
		SampleInfoDomain sampleInfoDomain = new SampleInfoDomain();
		sampleInfoDomain.setName("JANE DOE");
		sampleInfoDomain.setParticipantId(sampleDomainRequest.getParticipantID());
		response.setSampleInfo(sampleInfoDomain);
		response.addMessage(MessageSeverity.INFO, HttpStatus.OK, ArchetypeTestMessageKeys.BIP_SAMPLE_SERVICE_IMPL_RESPONDED_WITH_MOCK_DATA,
				"");
		return response;
	}

	/**
	 * Support graceful degradation by adding a fallback method that @CircuitBreaker will call to obtain a
	 * default value or values in case the main command fails for {@link #sampleFindByParticipantID(SampleDomainRequest)}.
	 * 
	 * @CircuitBreaker doesn't REQUIRE you to set this method. However, if it is possible to degrade gracefully
	 * - perhaps by returning static data, or performing some other process - the degraded process should
	 * be performed in the fallback method. In order to enable a fallback such as this, on the main method,
	 * add to its {@code @CircuitBreaker} the {@code fallbackMethod} attribute. So for
	 * {@link #sampleFindByParticipantID(SampleDomainRequest)}
	 * you would add the attribute to its {@code @CircuitBreaker}:<br/>
	 *
	 * <pre>
	 * fallbackMethod = "sampleFindByParticipantIDFallBack"
	 * </pre>
	 * 
	 * <p>
	 *
	 * @param sampleDomainRequest The request from the Java Service.
	 * @param throwable the throwable
	 * @return A JAXB element for the WS request
	 */
	public SampleDomainResponse sampleFindByParticipantIDFallBack(final SampleDomainRequest sampleDomainRequest,
			final Throwable throwable) {
		LOGGER.info("sampleFindByParticipantIDFallBack has been activated");

		/*
		 * Fallback Method for Demonstration Purpose. In this use case, there is no static / mock data
		 * that can be sent back to the consumers. Hence the method isn't configured as fallback.
		 *
		 * If needed to be configured, add annotation to the implementation method "findPersonByParticipantID" as below
		 *
		 * @Command(fallbackMethod = "findPersonByParticipantIDFallBack")
		 */
		final SampleDomainResponse response = new SampleDomainResponse();
		response.setDoNotCacheResponse(true);

		if (throwable != null) {
			LOGGER.debug(ReflectionToStringBuilder.toString(throwable, null, true, true, Throwable.class));
			response.addMessage(MessageSeverity.WARN, HttpStatus.OK, MessageKeys.BIP_GLOBAL_GENERAL_EXCEPTION,
					throwable.getClass().getSimpleName(), throwable.getLocalizedMessage());
		} else {
			LOGGER.error(
					"sampleFindByParticipantIDFallBack No Throwable Exception. Just Raise Runtime Exception {}",
					sampleDomainRequest);
			response.addMessage(MessageSeverity.WARN, HttpStatus.OK, MessageKeys.WARN_KEY,
					"There was a problem processing your request.");
		}
		return response;
	}
}