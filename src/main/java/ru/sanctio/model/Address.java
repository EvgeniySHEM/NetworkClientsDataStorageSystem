package ru.sanctio.model;

public class Address {
    private int id;
    private String ip;
    private String mac;
    private String model;
    private String address;
    private Client client;

    public Address() {
    }

    public Address(String ip, String mac, String model, String address) {
        setIp(ip);
        setMac(mac);
        setModel(model);
        setAddress(address);
    }

    public Address(int id, String ip, String mac, String model, String address) {
        this(ip, mac, model, address);
        setId(id);
    }

    public Address(int id, String ip, String mac, String model, String address, Client client) {
        this(ip, mac, model, address);
        setClient(client);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        if (ip == null || ip.trim().isEmpty()) {
            throw new IllegalArgumentException("The ip value cannot be empty");
        }
        if (ip.length() > 25)
            throw new IllegalArgumentException("The ip length should not exceed 25 characters");
        if (ip.matches("^(([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d?\\d|2[0-4]\\d|25[0-5])$")) {
            this.ip = ip;
        } else {
            throw new IllegalArgumentException("You entered an incorrect ip address");
        }
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        if (mac == null || mac.trim().isEmpty()) {
            throw new IllegalArgumentException("The mac value cannot be empty");
        }
        if (mac.length() > 20)
            throw new IllegalArgumentException("The mac length should not exceed 20 characters");
        if (mac.matches("^([0-9A-Za-z]{2}[-]){5}([0-9A-Za-z]{2})$")) {
            this.mac = mac;
        } else {
            throw new IllegalArgumentException("You entered an incorrect mac address");
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("The model value cannot be empty");
        }
        if (model.length() > 100)
            throw new IllegalArgumentException("The model length should not exceed 100 characters");
        this.model = model;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("The address value cannot be empty");
        }
        if (address.length() > 200)
            throw new IllegalArgumentException("The address length should not exceed 200 characters");
        this.address = address;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address that = (Address) o;

        if (id != that.id) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (mac != null ? !mac.equals(that.mac) : that.mac != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (mac != null ? mac.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", mac='" + mac + '\'' +
                ", model='" + model + '\'' +
                ", address='" + address + '\'' +
                ", client=" + client +
                '}';
    }
}
