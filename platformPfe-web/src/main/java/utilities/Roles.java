package utilities;

public enum Roles {
	EMPLOYEE("employe"),
    DEPARTMENTHEAD("dep-head"),
    DIRECTEURINTERNSHIPS("int-dir"),
    TEACHER("teacher"),
    ADMIN("admin");

    private final String role;

    Roles(String value) {
        this.role = value;
    }

    @Override
    public String toString() {
        return this.role;
    }

}
