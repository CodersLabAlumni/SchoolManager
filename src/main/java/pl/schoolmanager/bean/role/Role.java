package pl.schoolmanager.bean.role;

public enum Role {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    STUDENT("ROLE_STUDENT"),
    TEACHER("ROLE_TEACHER"),
    SCHOOLADMIN("ROLE_SCHOOLADMIN"),
    PARENT("ROLE_PARENT");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public boolean isEqualTo(String role) {
        return this.role.equals(role);
    }

    public String value() {
        return role;
    }

}