package com.example.atividades.atividade14;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EventHandlerTest {

    @Test
    public void testEventHandlerConstructor() {
        EmailService emailService = mock(EmailService.class);
        EventHandler eventHandler = new EventHandler(emailService);
        assertNotNull(eventHandler);
    }

    @Test
    public void testHandleEvent() {
        EmailService emailService = mock(EmailService.class);
        EventHandler eventHandler = new EventHandler(emailService);
        String event = "Test Event";
        eventHandler.handleEvent(event);
        verify(emailService).sendEmail("test@example.com", "Event Occurred", event);
    }
}