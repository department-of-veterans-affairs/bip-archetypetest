package gov.va.bip.archetypetest;

import gov.va.bip.archetypetest.api.model.v1.DiscoveryClientPingResponse;
import gov.va.bip.archetypetest.api.model.v1.GetServicesResponse;

/**
 * The contract interface for the Reference S3 domain (service) layer.
 *
 */
public interface ReferenceDiscoveryClientService {

	DiscoveryClientPingResponse discoveryClientPing();

	GetServicesResponse getServices();
}
