package com.div.spring.web.dao;

import com.div.spring.web.validation.ValidEmail;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Div on 2018-04-18.
 */
public class User {

    @NotBlank
    @Size(min = 8, max = 25)
    @Pattern(regexp = "^\\w{8,}$")
    private String username;

    @NotBlank
    @Pattern(regexp = "^\\S+$")
    @Size(min = 8, max = 15)
    private String password;

    @ValidEmail
    private String email;

    private boolean enabled = false;
    private String authority;

    public User() {
    }

    public User(String username, String password, String email, boolean enabled, String authority) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.authority = authority;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (enabled != user.enabled) return false;
        if (!username.equals(user.username)) return false;
        if (!email.equals(user.email)) return false;
        return authority.equals(user.authority);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + authority.hashCode();
        return result;
    }
}
