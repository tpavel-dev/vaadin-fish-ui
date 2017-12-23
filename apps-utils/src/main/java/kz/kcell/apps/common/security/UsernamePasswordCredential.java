package kz.kcell.apps.common.security;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 12 09 2014
 */
public interface UsernamePasswordCredential extends Credential {
    String getUsername();

    String getPassword();
}
