package ru.sanctio.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    private Address address;

    @BeforeEach
    void initAddress() {
        address = new Address("122.177.133.19", "GG-63-9d-3j-kc-2y", "model6", "Minsk");
    }

    @Test
    void setIp() {
        String ip = "123.23.222.222";
        address.setIp(ip);

        assertEquals(ip, address.getIp());
    }

    @Test
    void setIp_ShouldThrowIllegalArgumentExceptionWhenIpIsNull() {
        String ip = null;
        String message = "The ip value cannot be empty";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> address.setIp(ip));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setIp_ShouldThrowIllegalArgumentExceptionWhenIpIsEmpty() {
        String ip = "";
        String message = "The ip value cannot be empty";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> address.setIp(ip));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setIp_ShouldThrowIllegalArgumentExceptionWhenIpMore25Сharacters() {
        String ip = "м".repeat(26);
        String message = "The ip length should not exceed 25 characters";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> address.setIp(ip));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setIp_ShouldThrowIllegalArgumentExceptionWhenIpNotCorrectPositiveValue() {
        String ip = "256.23.222.222";
        String ip2 = "255.256.222.222";
        String ip3 = "255.23.256.222";
        String ip4 = "255.23.222.256";
        String message = "You entered an incorrect ip address";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> address.setIp(ip));
        assertThrows(IllegalArgumentException.class, () -> address.setIp(ip2));
        assertThrows(IllegalArgumentException.class, () -> address.setIp(ip3));
        assertThrows(IllegalArgumentException.class, () -> address.setIp(ip4));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setIp_ShouldThrowIllegalArgumentExceptionWhenIpNotCorrectNegativeValue() {
        String ip = "-1.23.222.222";
        String ip2 = "255.-1.222.222";
        String ip3 = "255.23.-1.222";
        String ip4 = "255.23.222.-1";
        String message = "You entered an incorrect ip address";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> address.setIp(ip));
        assertThrows(IllegalArgumentException.class, () -> address.setIp(ip2));
        assertThrows(IllegalArgumentException.class, () -> address.setIp(ip3));
        assertThrows(IllegalArgumentException.class, () -> address.setIp(ip4));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setMac() {
        String mac = "GG-63-9d-3j-kc-2y";
        address.setMac(mac);

        assertEquals(mac, address.getMac());
    }

    @Test
    void setMac_ShouldThrowIllegalArgumentExceptionWhenMacIsNull() {
        String mac = null;
        String message = "The mac value cannot be empty";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> address.setMac(mac));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setMac_ShouldThrowIllegalArgumentExceptionWhenMacIsEmpty() {
        String mac = "";
        String message = "The mac value cannot be empty";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> address.setMac(mac));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setMac_ShouldThrowIllegalArgumentExceptionWhenMacMore20Сharacters() {
        String mac = "м".repeat(21);
        String message = "The mac length should not exceed 20 characters";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> address.setMac(mac));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setMac_ShouldThrowIllegalArgumentExceptionWhenMacNotCorrectCharacter() {
        String mac = "мм-63-9d-3j-kc-2y";
        String mac2 = "GG-мм-9d-3j-kc-2y";
        String mac3 = "GG-63-мм-3j-kc-2y";
        String mac4 = "GG-63-9d-мм-kc-2y";
        String mac5 = "GG-63-9d-3j-мм-2y";
        String mac6 = "GG-63-9d-3j-kc-мм";
        String message = "You entered an incorrect mac address";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> address.setMac(mac));
        assertThrows(IllegalArgumentException.class, () -> address.setMac(mac2));
        assertThrows(IllegalArgumentException.class, () -> address.setMac(mac3));
        assertThrows(IllegalArgumentException.class, () -> address.setMac(mac4));
        assertThrows(IllegalArgumentException.class, () -> address.setMac(mac5));
        assertThrows(IllegalArgumentException.class, () -> address.setMac(mac6));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setMac_ShouldThrowIllegalArgumentExceptionWhenMacNotCorrectNegativeValue() {
        String mac = "-11-63-9d-3j-kc-2y";
        String mac2 = "GG--11-9d-3j-kc-2y";
        String mac3 = "GG-63--11-3j-kc-2y";
        String mac4 = "GG-63-9d--11-kc-2y";
        String mac5 = "GG-63-9d-3j--11-2y";
        String mac6 = "GG-63-9d-3j-kc--11";
        String message = "You entered an incorrect mac address";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> address.setMac(mac));
        assertThrows(IllegalArgumentException.class, () -> address.setMac(mac2));
        assertThrows(IllegalArgumentException.class, () -> address.setMac(mac3));
        assertThrows(IllegalArgumentException.class, () -> address.setMac(mac4));
        assertThrows(IllegalArgumentException.class, () -> address.setMac(mac5));
        assertThrows(IllegalArgumentException.class, () -> address.setMac(mac6));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setModel() {
        String model = "model1";
        address.setModel(model);

        assertEquals(model, address.getModel());
    }

    @Test
    void setModel_ShouldThrowIllegalArgumentExceptionWhenModelIsNull() {
        String model = null;
        String message = "The model value cannot be empty";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> address.setModel(model));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setModel_ShouldThrowIllegalArgumentExceptionWhenModelIsEmpty() {
        String model = "";
        String message = "The model value cannot be empty";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> address.setModel(model));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setModel_ShouldThrowIllegalArgumentExceptionWhenModelMore100Сharacters() {
        String model = "м".repeat(101);
        String message = "The model length should not exceed 100 characters";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> address.setModel(model));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setAddress() {
        String adr = "Moscow";
        address.setAddress(adr);

        assertEquals(adr, address.getAddress());
    }

    @Test
    void setAddress_ShouldThrowIllegalArgumentExceptionWhenAddressIsNull() {
        String adr = null;
        String message = "The address value cannot be empty";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> address.setAddress(adr));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setAddress_ShouldThrowIllegalArgumentExceptionWhenAddressIsEmpty() {
        String adr = "";
        String message = "The address value cannot be empty";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> address.setAddress(adr));

        assertEquals(message, exception.getMessage());
    }

    @Test
    void setAddress_ShouldThrowIllegalArgumentExceptionWhenAddressMore200Сharacters() {
        String adr = "м".repeat(201);
        String message = "The address length should not exceed 200 characters";

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> address.setAddress(adr));

        assertEquals(message, exception.getMessage());
    }
}