package LibraryWebApp.Library.Security;

public enum UserPermission {
    BOOKS_READ("books:read"),
    USER_READ("user:read"),
    BOOKS_WRITE("books:write"),
    USER_WRITE("user:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
