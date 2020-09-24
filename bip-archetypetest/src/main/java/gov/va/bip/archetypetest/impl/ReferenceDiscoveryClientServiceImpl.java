package gov.va.bip.archetypetest.impl;

import gov.va.bip.archetypetest.api.model.v1.GetServiceUriResponse;
import gov.va.bip.archetypetest.api.model.v1.GetServicesResponse;
import gov.va.bip.framework.log.BipLogger;
import gov.va.bip.framework.log.BipLoggerFactory;
import gov.va.bip.archetypetest.ReferenceDiscoveryClientService;
import gov.va.bip.archetypetest.api.model.v1.DiscoveryClientPingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation class for the Reference Person Service to demonstrate Consul Service Discovery of the BLUE Framework.
 */
@Service(value = ReferenceDiscoveryClientServiceImpl.BEAN_NAME)
@Component
@Qualifier("REFERENCE_DISCOVERY_CLIENT_SERVICE_IMPL")
@RefreshScope
public class ReferenceDiscoveryClientServiceImpl implements ReferenceDiscoveryClientService {

	/**
	 * Bean name constant
	 */
	public static final String BEAN_NAME = "referenceDiscoveryClientServiceImpl";

	private static final BipLogger LOGGER = BipLoggerFactory.getLogger(ReferenceDiscoveryClientServiceImpl.class);

	@Autowired
	private DiscoveryClient discoveryClient;

	@Override
	public DiscoveryClientPingResponse discoveryClientPing() {
		List<String> services = discoveryClient.getServices();
		DiscoveryClientPingResponse response = new DiscoveryClientPingResponse();
		if(services.size() > 0) {
			response.setServiceUrl(services.get(0));
		}

		return response;
	}

	@Override
    public GetServicesResponse getServices() {
        List<String> services = discoveryClient.getServices();
        GetServicesResponse response = new GetServicesResponse();
        if(services.size() > 0) {
            response.setServiceNames(services);
        }

        return response;
    }

    @Override
    public GetServiceUriResponse getServiceUri(String serviceName) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
        GetServiceUriResponse response = new GetServiceUriResponse();
        if(serviceInstances.size() > 0) {
            response.setServiceUri(serviceInstances.get(0).getUri().toString());
        }
        else {
            //TODO_CMF: Hard coded response for testing.
            response.setServiceUri("No service Instances found.");
        }

        return response;
    }
}