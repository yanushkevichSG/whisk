package utilities;

import framework.settings.JsonSettingsFile;

public class Environment {
    private static final JsonSettingsFile ENVIRONMENT_SETTINGS_FILE;

    static {
        String environment = System.getProperty("environment", "dev");
        ENVIRONMENT_SETTINGS_FILE = new JsonSettingsFile(String.format("environment/%s.json", environment.toLowerCase()));
    }

    public static String getBaseUrl() {
        return ENVIRONMENT_SETTINGS_FILE.getValue("/baseUrl").toString();
    }

    public static String getWhiskApiUrl() {
            return ENVIRONMENT_SETTINGS_FILE.getValue("/apiService/url").toString();
    }

    public static String getUserEmail() {
        return ENVIRONMENT_SETTINGS_FILE.getValue("/user/email").toString();
    }

    public static String getUserPassword() {
        return ENVIRONMENT_SETTINGS_FILE.getValue("/user/password").toString();
    }

    public static String getBearerToken() {
        return ENVIRONMENT_SETTINGS_FILE.getValue("/apiService/bearerToken").toString();
    }
}
