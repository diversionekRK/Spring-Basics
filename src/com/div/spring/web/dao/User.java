package com.div.spring.web.dao;

import com.div.spring.web.validation.ValidEmail;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import sun.misc.FormattedFloatingDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Div on 2018-04-18.
 */

@Entity
@Table(name = "users")
public class User {

    @NotBlank(groups = {PersistanceValidationGroup.class, FormValidationGroup.class})
    @Size(min = 8, max = 25, groups = {PersistanceValidationGroup.class, FormValidationGroup.class})
    @Pattern(regexp = "^\\w{8,}$", groups = {PersistanceValidationGroup.class, FormValidationGroup.class})
    @Id
    @Column(name = "username")
    private String username;

    @NotBlank(groups = {PersistanceValidationGroup.class, FormValidationGroup.class})
    @Pattern(regexp = "^\\S+$", groups = {PersistanceValidationGroup.class, FormValidationGroup.class})
    @Size(min = 8, max = 15, groups = {FormValidationGroup.class})
    private String password;

    @ValidEmail(groups = {PersistanceValidationGroup.class, FormValidationGroup.class})
    private String email;

    @NotBlank(groups = {PersistanceValidationGroup.class, FormValidationGroup.class})
    @Size(min = 5, max = 60, message = "Name must be between 5 and 100 characters",
            groups = {PersistanceValidationGroup.class, FormValidationGroup.class})
    private String name;

    private boolean enabled = false;
    private String authority;

    public User() {
    }

    public User(String username, String password, String name, String email, boolean enabled, String authority) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.enabled = enabled;
        this.authority = authority;
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
        if (!name.equals(user.name)) return false;
        return authority.equals(user.authority);
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (authority != null ? authority.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", authority='" + authority + '\'' +
                '}';
    }
}
