package gov.va.bip.archetypetest;

import gov.va.bip.archetypetest.model.SampleDomainRequest;
import gov.va.bip.archetypetest.model.SampleDomainResponse;

/**
 * The contract interface for the domain (service) layer.
 *
 * @author aburkholder
 */
public interface ArchetypeTestService {
	/**
	 * Search for the sample info by their Participant ID.
	 *
	 * @param sampleDomainRequest A SampleDomainRequest instance
	 * @return A SampleDomainResponse instance
	 */
	SampleDomainResponse sampleFindByParticipantID(SampleDomainRequest sampleDomainRequest);
}
