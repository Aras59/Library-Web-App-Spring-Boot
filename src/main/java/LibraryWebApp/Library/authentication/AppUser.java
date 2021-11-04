package LibraryWebApp.Library.authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class AppUser implements UserDetails {

    private final Set<? extends GrantedAuthority> grantedAuthorities;
    private final String password;
    private final String username;
    private final boolean isaccountnotlock;
    private final boolean isaccountnotexpired;
    private final boolean isacnotexpired;
    private final boolean isenabled;

    public AppUser(Set<? extends GrantedAuthority> grantedAuthorities, String password, String username, boolean isaccountnotlock, boolean isaccountnotexpired, boolean isacnotexpired, boolean isenabled) {
        this.grantedAuthorities = grantedAuthorities;
        this.password = password;
        this.username = username;
        this.isaccountnotlock = isaccountnotlock;
        this.isaccountnotexpired = isaccountnotexpired;
        this.isacnotexpired = isacnotexpired;
        this.isenabled = isenabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isaccountnotexpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isaccountnotlock;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isacnotexpired;
    }

    @Override
    public boolean isEnabled() {
        return isenabled;
    }
}
