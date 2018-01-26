package pl.schoolmanager.bean.role;

public enum Role {

    ROLE_ADMIN,
    ROLE_USER,
    ROLE_STUDENT,
    ROLE_TEACHER,
    ROLE_SCHOOLADMIN,
    ROLE_PARENT;

    public boolean isEqual(String role) {
        return this.equals(valueOf(role));
    }

}