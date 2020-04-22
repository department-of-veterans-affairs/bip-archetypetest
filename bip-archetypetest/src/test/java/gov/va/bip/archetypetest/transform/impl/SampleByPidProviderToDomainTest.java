package gov.va.bip.archetypetest.transform.impl;

import gov.va.bip.archetypetest.api.model.v1.SampleRequest;
import gov.va.bip.archetypetest.model.SampleDomainRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SampleByPidProviderToDomainTest {

    SampleByPidProviderToDomain instance;

    private final Long PARTICIPANT_ID = 1L;

    @Before
    public void setUp() {
        instance = new SampleByPidProviderToDomain();
    }

    @Test
    public void testConvert() {
        // Setup
        SampleRequest sampleRequest = createSampleRequest();

        // Execute Test
        SampleDomainRequest request = instance.convert(sampleRequest);

        // Verifications
        assertNotNull(request);
        assertEquals(PARTICIPANT_ID, request.getParticipantID());
    }

    private SampleRequest createSampleRequest() {
        SampleRequest sampleRequest = new SampleRequest();
        sampleRequest.setParticipantID(PARTICIPANT_ID);

        return sampleRequest;
    }
}
