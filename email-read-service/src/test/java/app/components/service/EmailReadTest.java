package app.components.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmailReadTest {

    @InjectMocks
    public EmailReadService emailReadService;
    @Test
    public void readEmail(){

        emailReadService.readEmail();
    }
}
