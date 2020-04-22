package gov.va.bip.archetypetest.model.validators;

import gov.va.bip.framework.messages.MessageKeys;
import gov.va.bip.framework.messages.MessageSeverity;
import gov.va.bip.framework.messages.ServiceMessage;
import gov.va.bip.archetypetest.messages.ArchetypeTestMessageKeys;
import gov.va.bip.archetypetest.model.SampleDomainRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SampleDomainRequestValidatorTest {

    SampleDomainRequestValidator instance;

    private final Long PARTICIPANT_ID = 1L;

    @Before
    public void setUp() {
        instance = new SampleDomainRequestValidator();
    }

    @Test
    public void testValidate_Success() {
        // Setup
        SampleDomainRequest sampleDomainRequest = createSampleDomainRequest();
        List<ServiceMessage> messagesList = new ArrayList<>();

        // Execute Test
        instance.validate(sampleDomainRequest, messagesList);

        // Verifications
        assertEquals(0, messagesList.size());
    }

    @Test
    public void testValidate_PidNull() {
        // Setup
        SampleDomainRequest sampleDomainRequest = createSampleDomainRequest();
        sampleDomainRequest.setParticipantID(null);
        List<ServiceMessage> messagesList = new ArrayList<>();

        // Execute Test
        instance.validate(sampleDomainRequest, messagesList);

        // Verifications
        assertEquals(1, messagesList.size());
        assertEquals(MessageSeverity.ERROR, messagesList.get(0).getSeverity());
        assertEquals(HttpStatus.BAD_REQUEST, messagesList.get(0).getHttpStatus());
        assertEquals(MessageKeys.BIP_VALIDATOR_NOT_NULL.getKey(), messagesList.get(0).getKey());
    }

    @Test
    public void testValidate_PidLTZero() {
        // Setup
        SampleDomainRequest sampleDomainRequest = createSampleDomainRequest();
        sampleDomainRequest.setParticipantID(-1L);
        List<ServiceMessage> messagesList = new ArrayList<>();

        // Execute Test
        instance.validate(sampleDomainRequest, messagesList);

        // Verifications
        assertEquals(1, messagesList.size());
        assertEquals(MessageSeverity.ERROR, messagesList.get(0).getSeverity());
        assertEquals(HttpStatus.BAD_REQUEST, messagesList.get(0).getHttpStatus());
        assertEquals(ArchetypeTestMessageKeys.BIP_SAMPLE_REQUEST_PID_MIN.getKey(), messagesList.get(0).getKey());
    }

    private SampleDomainRequest createSampleDomainRequest() {
        SampleDomainRequest sampleDomainRequest = new SampleDomainRequest();
        sampleDomainRequest.setParticipantID(PARTICIPANT_ID);

        return sampleDomainRequest;
    }
}
