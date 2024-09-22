public enum Version {
    VERSION_1("1"),
    VERSION_2("2");

    private final String version;
    Version(String version) {
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }

    public static Version fromValue(String val) {
        for (Version v: values()) {
            if(v.version.equals(val)) {
                return v;
            }
        }
        return null;
    }
}
