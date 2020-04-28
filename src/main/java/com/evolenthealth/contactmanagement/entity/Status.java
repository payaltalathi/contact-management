package com.evolenthealth.contactmanagement.entity;

public enum Status {

    ACTIVE("active"), INACTIVE("inactive");

    private String text;

    Status(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Status fromValue(String text) {
        for (Status status : Status.values()) {
            if (status.text.equalsIgnoreCase(text)) {
                return status;
            }
        }
        return null;
    }
}
