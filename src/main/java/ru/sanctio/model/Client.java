package ru.sanctio.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client implements Serializable {
    private int clientId;
    private String clientName;
    private String type;
    private String added;
    private final List<Address> addresses = new ArrayList<>();

    public Client() {
    }

    public Client(String clientName, String type, String added) {
        setClientName(clientName);
        setType(type);
        setAdded(added);
    }

    public Client(int clientId, String clientName, String type, String added) {
        this(clientName, type, added);
        setClientId(clientId);
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientid) {
        this.clientId = clientid;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        if (clientName == null || clientName.trim().isEmpty()) {
            throw new IllegalArgumentException("The client name value cannot be empty");
        }
        if (clientName.length() > 100)
            throw new IllegalArgumentException("The client name length should not exceed 100 characters");
        if (clientName.matches("^[а-яёА-ЯЁ{\\-\\s,.}]+$")) {
            this.clientName = clientName;
        } else {
            throw new IllegalArgumentException("For the client name field, it is allowed " +
                    "to use only the Russian alphabet, as well as the characters {- ,.}");
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        Objects.requireNonNull(type, "The type value cannot be empty");
        this.type = type;
    }

    public String getAdded() {
        if (added == null) {
            added = LocalDate.now().toString();
        }
        return added;
    }

    public void setAdded(String date) {
        LocalDate localDate = LocalDate.parse(date);
        if (localDate.isBefore(LocalDate.EPOCH) || localDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("You entered an incorrect year");
        this.added = date;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address... address) {
        for (Address ex : address) {
            addresses.add(ex);
            ex.setClient(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client that = (Client) o;

        if (clientId != that.clientId) return false;
        if (!Objects.equals(clientName, that.clientName)) return false;
        if (!Objects.equals(type, that.type)) return false;
        return Objects.equals(added, that.added);
    }

    @Override
    public int hashCode() {
        int result = clientId;
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (added != null ? added.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientid=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", type='" + type + '\'' +
                ", added=" + added +
                '}';
    }
}
