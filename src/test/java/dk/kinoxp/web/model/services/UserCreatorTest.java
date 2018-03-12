package dk.kinoxp.web.model.services;

import dk.kinoxp.web.model.entities.Actor;
import dk.kinoxp.web.model.entities.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserCreatorTest {

    @Test
    public void createuser() {
        User user = new User();
        Actor actor = new Actor(); // To fail the test
        System.out.println(user.getClass());
        UserCreator userCreator = new UserCreator();
        assertEquals(userCreator.createUser("Arne", "1234").getClass(), user.getClass());

    }
}