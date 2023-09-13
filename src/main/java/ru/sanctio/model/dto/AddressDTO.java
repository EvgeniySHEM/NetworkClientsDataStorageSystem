package ru.sanctio.model.dto;

import ru.sanctio.model.Client;

import java.io.Serializable;

public record AddressDTO(
        String ip,
        String mac,
        String model,
        String address) implements Serializable {
}
