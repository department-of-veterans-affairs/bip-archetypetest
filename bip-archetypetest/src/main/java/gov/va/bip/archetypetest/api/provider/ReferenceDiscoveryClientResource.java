package gov.va.bip.archetypetest.api.provider;

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

    //TODO_CMF: Determine if any of this is still necessary
//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    @Autowired
//    private RestTemplate restTemplate = new RestTemplate();
//
//    @Autowired
//    private RestClientTemplate discoveryClientRestTemplate;
//
//    public Optional<URI> serviceUrl() {
//        return discoveryClient.getInstances("k8s-bip-archetypetest-dev-archetypetest-dev")
//                .stream()
//                .findFirst()
//                .map(si -> si.getUri());
//
////        List<ServiceInstance> list = discoveryClient.getInstances("k8s-bip-archetypetest-dev-archetypetest-dev");
////        if (list != null && list.size() > 0 ) {
////            return list.get(0).getUri();
////        }
////        return null;
//    }
//
//    @GetMapping("/discoveryClient")
//    public String discoveryPing() throws RestClientException, ServiceUnavailableException {
//        URI service = serviceUrl()
//                .map(s -> s.resolve("/ping"))
//                .orElseThrow(ServiceUnavailableException::new);
//        return restTemplate.getForEntity(service, String.class)
//                .getBody();
////        return discoveryClientRestTemplate.executeURL(service, HttpEntity)
//    }
//
//    @GetMapping("/ping")
//    public String ping() {
//        return "pong";
//    }
//
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
}
