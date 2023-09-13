package ru.sanctio.model.dto;

import ru.sanctio.model.Address;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public record ClientDTO(String clientName,
                        String type,
                        String added) implements Serializable {
}
