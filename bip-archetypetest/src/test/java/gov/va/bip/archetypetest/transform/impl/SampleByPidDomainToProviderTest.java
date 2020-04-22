package gov.va.bip.archetypetest.transform.impl;

import gov.va.bip.framework.messages.MessageSeverity;
import gov.va.bip.archetypetest.api.model.v1.SampleResponse;
import gov.va.bip.archetypetest.messages.ArchetypeTestMessageKeys;
import gov.va.bip.archetypetest.model.SampleDomainResponse;
import gov.va.bip.archetypetest.model.SampleInfoDomain;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SampleByPidDomainToProviderTest {

    SampleByPidDomainToProvider instance;

    private final Long PARTICIPANT_ID = 1L;

    @Before
    public void setUp() {
        instance = new SampleByPidDomainToProvider();
    }

    @Test
    public void testConvert() {
        // Setup
        SampleDomainResponse sampleDomainResponse = createSampleDomainResponse();

        // Execute Test
        SampleResponse response = instance.convert(sampleDomainResponse);

        // Verifications
        assertNotNull(response);
        assertNotNull(response.getSampleInfo());
        assertEquals("JOHN DOE", response.getSampleInfo().getName());
        assertEquals(PARTICIPANT_ID, response.getSampleInfo().getParticipantId());
        assertEquals(1, response.getMessages().size());
        assertEquals(MessageSeverity.INFO.value(), response.getMessages().get(0).getSeverity());
        assertEquals(Integer.toString(HttpStatus.OK.value()), response.getMessages().get(0).getStatus());
        assertEquals(ArchetypeTestMessageKeys.BIP_SAMPLE_SERVICE_IMPL_RESPONDED_WITH_MOCK_DATA.getKey(), response.getMessages().get(0).getKey());
    }

    private SampleDomainResponse createSampleDomainResponse() {
        SampleInfoDomain sampleInfoDomain = new SampleInfoDomain();
        sampleInfoDomain.setName("JOHN DOE");
        sampleInfoDomain.setParticipantId(PARTICIPANT_ID);

        SampleDomainResponse sampleDomainResponse = new SampleDomainResponse();
        sampleDomainResponse.setSampleInfo(sampleInfoDomain);
        sampleDomainResponse.addMessage(MessageSeverity.INFO, HttpStatus.OK, ArchetypeTestMessageKeys.BIP_SAMPLE_SERVICE_IMPL_RESPONDED_WITH_MOCK_DATA,
                "");

        return sampleDomainResponse;
    }
}
