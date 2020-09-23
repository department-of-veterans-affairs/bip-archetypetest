package gov.va.bip.archetypetest;

import gov.va.bip.archetypetest.api.model.v1.DiscoveryClientPingResponse;

/**
 * The contract interface for the Reference S3 domain (service) layer.
 *
 */
public interface ReferenceDiscoveryClientService {

	DiscoveryClientPingResponse discoveryClientPing();

}
