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
    void getAdded_ShouldReturnLocalDateNow() {
        Client client1 = new Client(2, "Петр", "Физическое лицо", null);

        assertEquals(LocalDate.now().toString(), client1.getAdded());
    }

    @Test
    void setAdded_ShouldReturnLocalDateNow() {
        String added = null;
        client.setAdded(added);

        assertEquals(LocalDate.now().toString(), client.getAdded());
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

    @Test
    void testEquals_ShouldReturnTrue() {
        Client client1 = new Client(1, "Петр", "Физическое лицо", "2023-05-13");

        assertEquals(client, client1);
    }

    @Test
    void testEquals_ShouldReturnTrue2() {

        assertEquals(client, client);
    }

    @Test
    void testEquals_ShouldReturnFalse() {
        Client client1 = new Client(2, "Макс", "Юридическое лицо", "2023-05-11");

        assertNotEquals(client, client1);
    }

    @Test
    void testEquals_ShouldReturnFalseIfClientNull() {
        Client client1 = null;

        assertNotEquals(client, client1);
    }

    @Test
    void testHashCode_ShouldReturnTrue() {
        Client client1 = new Client(1, "Петр", "Физическое лицо", "2023-05-13");

        assertTrue(client.hashCode() == client1.hashCode());
    }

    @Test
    void testHashCode_ShouldReturnFalse() {
        Client client1 = new Client(2, "Петр", "Физическое лицо", "2023-05-11");

        assertFalse(client.hashCode() == client1.hashCode());
    }

    @Test
    void testToString_ShouldReturnTrue() {
        Client client1 = new Client(1, "Петр", "Физическое лицо", "2023-05-13");

        assertTrue(client.toString().equals(client1.toString()));
    }

    @Test
    void testToString_ShouldReturnFalse() {
        Client client1 = new Client(2, "Петр", "Физическое лицо", "2023-05-11");

        assertFalse(client.toString().equals(client1.toString()));
    }

    @Test
    void getAddresses() {
        Address address1 = new Address(0, "122.177.133.19", "GG-63-9d-3j-kc-2y", "model6", "Minsk");
        client.addAddress(address1);

        assertTrue(client.getAddresses().get(0).equals(address1));
    }

    @Test
    void addAddress() {
        Address address1 = new Address(0, "122.177.133.19", "GG-63-9d-3j-kc-2y", "model6", "Minsk");
        client.addAddress(address1);

        assertTrue(client.getAddresses().get(0).equals(address1));
    }
}