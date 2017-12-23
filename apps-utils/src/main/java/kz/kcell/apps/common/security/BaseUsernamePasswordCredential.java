package kz.kcell.apps.common.security;

import org.springframework.util.Assert;

import java.util.Objects;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 12 09 2014
 */
public class BaseUsernamePasswordCredential implements UsernamePasswordCredential {
    final private String username;
    final private String password;

    public BaseUsernamePasswordCredential(String username, String password) {
        Assert.notNull(username, "User name must not empty");
        Assert.notNull(password, "password must not empty");
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username + password);
    }

    @Override
    public String toString() {
        return "UsernamePasswordCredential{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
