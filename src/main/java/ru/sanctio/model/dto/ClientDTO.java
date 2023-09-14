package ru.sanctio.model.dto;

import java.io.Serializable;

public record ClientDTO(int clientId,
                        String clientName,
                        String type,
                        String added) implements Serializable {
}
