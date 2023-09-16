package ru.sanctio.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    private Client client;

    @BeforeEach
    void initMethod() {
        client = new Client(1, "Петр", "Физическое лицо", "2023-05-13");
    }

    @Test
    void setClientName() {
        String name = "Макс";
        client.setClientName(name);

        assertEquals(name, client.getClientName());
    }

    @Test
    void setClientName_ShouldThrowIllegalArgumentExceptionWhenClientNameIsNull() {
        String message = "The client name value cannot be empty";
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> client.setClientName(null));

        assertEquals(message, exception.getMessage());

    }

    @Test
    void setClientName_ShouldThrowIllegalArgumentExceptionWhenClientNameIsEmpty() {
        String message = "The client name value cannot be empty";
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> client.setClientName(""));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setClientName_ShouldThrowIllegalArgumentExceptionWhenClientNameMore100Сharacters() {
        String message = "The client name length should not exceed 100 characters";
        String string = "м".repeat(101);
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> client.setClientName(string));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setClientName_ShouldThrowIllegalArgumentExceptionWhenClientNameNotValidCharacters() {
        String message = "For the client name field, it is allowed to use only the Russian alphabet, " +
                "as well as the characters {- ,.}";
        String name = "Tom";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> client.setClientName(name));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setType() {
        String type = "Юридическое лицо";
        client.setType(type);

        assertEquals(type, client.getType());
    }

    @Test
    void setType_ShouldThrowNullPointerExceptionWhenClientTypeIsNull() {
        String type = null;
        String message = "The type value cannot be empty";

        NullPointerException exception =
                assertThrows(NullPointerException.class, () -> client.setType(type));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setAdded() {
        String added = "2023-05-13";
        client.setAdded(added);

        assertEquals(added, client.getAdded());
    }

    @Test
    void setAdded_ShouldThrowIllegalArgumentExceptionWhenAddedIsBeforeEpoch() {
        String added = "1900-05-13";
        String message = "You entered an incorrect year";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> client.setAdded(added));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setAdded_ShouldThrowIllegalArgumentExceptionWhenAddedIsAfterNow() {
        String added = (LocalDate.now().plusDays(1)).toString();
        String message = "You entered an incorrect year";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> client.setAdded(added));

        assertEquals(message, exception.getMessage());
    }
}