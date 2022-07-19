package vendingmachine.util;

public enum Role {

    SELLER("SELLER"),
    BUYER("BUYER");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
