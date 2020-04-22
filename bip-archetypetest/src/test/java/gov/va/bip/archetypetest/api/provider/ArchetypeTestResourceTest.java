package gov.va.bip.archetypetest.api.provider;

import gov.va.bip.framework.log.BipLogger;
import gov.va.bip.archetypetest.ArchetypeTestTestUtils;
import gov.va.bip.archetypetest.api.model.v1.SampleRequest;
import gov.va.bip.archetypetest.api.model.v1.SampleResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.bind.WebDataBinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArchetypeTestResourceTest {

    ArchetypeTestResource instance;

    @Mock
    private BipLogger mockBipLogger;
    @Mock
    private BuildProperties mockBuildProperties;
    @Mock
    private WebDataBinder mockWebDataBinder;
    @Mock
    private SampleRequest mockSampleRequest;
    @Mock
    private SampleResponse mockSampleResponse;
    @Mock
    private ServiceAdapter mockServiceAdapter;

    @Before
    public void setUp() {
        instance = new ArchetypeTestResource();

        MockitoAnnotations.initMocks(this);

        try {
            ArchetypeTestTestUtils.setFinalStatic(ArchetypeTestResource.class.getDeclaredField("LOGGER"), mockBipLogger);
        } catch (Exception e) {
            // Ignore and attempt to test without
        }
        ReflectionTestUtils.setField(instance, "buildProperties", mockBuildProperties);
        ReflectionTestUtils.setField(instance, "serviceAdapter", mockServiceAdapter);
    }

    @Test
    public void testPostConstruct() {
        // Setup
        String testString1 = "Test1";
        String testString2 = "Test2";
        String testString3 = "Test3";
        String testString4 = "Test4";
        when(mockBuildProperties.getName()).thenReturn(testString1);
        when(mockBuildProperties.getVersion()).thenReturn(testString2);
        when(mockBuildProperties.getArtifact()).thenReturn(testString3);
        when(mockBuildProperties.getGroup()).thenReturn(testString4);

        // Execute Test
        instance.postConstruct();

        // Verifications
        verify(mockBipLogger).info(testString1);
        verify(mockBipLogger).info(testString2);
        verify(mockBipLogger).info(testString3);
        verify(mockBipLogger).info(testString4);
    }

    @Test
    public void testInitBinder() {
        // Setup

        // Execute Test
        instance.initBinder(mockWebDataBinder);

        // Verifications
        verify(mockWebDataBinder).setAllowedFields("sampleInfo", "name", "participantId");
    }

    @Test
    public void testSampleByPid() {
        // Setup
        when(mockServiceAdapter.sampleByPid(mockSampleRequest)).thenReturn(mockSampleResponse);

        // Execute Test
        ResponseEntity<SampleResponse> response = instance.sampleByPid(mockSampleRequest);

        // Verifications
        assertNotNull(response);
        assertEquals(mockSampleResponse, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
