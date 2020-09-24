package gov.va.bip.archetypetest.api.provider;

import gov.va.bip.archetypetest.api.model.v1.GetServiceInstancesResponse;
import gov.va.bip.archetypetest.api.model.v1.GetServicesResponse;
import gov.va.bip.framework.swagger.SwaggerResponseMessages;
import gov.va.bip.archetypetest.ReferenceDiscoveryClientService;
import gov.va.bip.archetypetest.api.ReferencePersonDiscoveryClientApi;
import gov.va.bip.archetypetest.api.model.v1.DiscoveryClientPingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReferenceDiscoveryClientResource implements ReferencePersonDiscoveryClientApi, SwaggerResponseMessages {

    @Autowired
    @Qualifier("REFERENCE_DISCOVERY_CLIENT_SERVICE_IMPL")
    private ReferenceDiscoveryClientService refDiscoveryClientPersonService;

    @Override
    public ResponseEntity<DiscoveryClientPingResponse> discoveryClientPing() {
        return new ResponseEntity<>(refDiscoveryClientPersonService.discoveryClientPing(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GetServicesResponse> getServices() {
        return new ResponseEntity<>(refDiscoveryClientPersonService.getServices(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GetServiceInstancesResponse> getServiceInstances() {
        return new ResponseEntity<>(refDiscoveryClientPersonService.getServiceInstances(), HttpStatus.OK);
    }

}
