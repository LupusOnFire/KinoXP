package dk.kinoxp.web.model.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordServiceTest {
    PasswordService encoder;
    @Before
    public void setUp() throws Exception {
        encoder = new PasswordService();
    }

    @Test
    public void encodePassword() throws Exception {

    }

    @Test
    public void checkEncode() throws Exception {
        String password = "mehmet";
        String hashedPassword = "$2a$10$TlyuzP8m.W9qMCDq/z2Pq.Llp3uSOcv0Wm0oES0NDlbpA01XN1sM.";
        assertTrue(encoder.checkMatch(password, hashedPassword));
    }

}