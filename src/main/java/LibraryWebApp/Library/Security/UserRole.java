package LibraryWebApp.Library.Security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(UserPermission.BOOKS_READ,UserPermission.BOOKS_WRITE,UserPermission.USER_READ,UserPermission.USER_WRITE));

    private final Set<UserPermission> permission;

    UserRole(Set<UserPermission> permission) {
        this.permission = permission;
    }

    public Set<UserPermission> getPermission() {
        return permission;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermission().stream().map(permission-> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;
    }
}
