package com.multiDb.test;

public final class Immutable {
    private final String name;
    private final Address address;

    public Immutable(String name, Address address) {
        this.name = name;
        this.address = new Address(address.type());
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return new Address(address.type());
    }
}

record Address(String type) {
}
