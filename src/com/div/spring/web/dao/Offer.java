package com.div.spring.web.dao;

import com.div.spring.web.validation.ValidEmail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Div on 2018-02-13.
 */

public class Offer {
    private int id;

    @Size(min = 5, max = 100, message = "Name must be between 5 and 100 characters")
    private String name;

    @NotNull
    //@Pattern(regexp = ".*\\@.*\\..*", message = "This does not appear to be a valid email address.")
    @ValidEmail(min = 7, message = "This email address is not valid.")
    private String email;

    @Size(min = 20, max = 255, message = "Text must be between 20 and 255 characters")
    private String text;

    public Offer() {
    }

    public Offer(int id, String name, String email, String text) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.text = text;
    }

    public Offer(String name, String email, String text) {
        this.name = name;
        this.email = email;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offer offer = (Offer) o;

        if (!name.equals(offer.name)) return false;
        if (!email.equals(offer.email)) return false;
        return text.equals(offer.text);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }
}
