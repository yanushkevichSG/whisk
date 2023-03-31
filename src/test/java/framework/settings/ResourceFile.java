package framework.settings;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Collectors;

class ResourceFile {
    private final String resourceName;
    private final String fileCanonicalPath;
    private final String fileContent;

    ResourceFile(String resourceName) {
        this.resourceName = resourceName;
        this.fileCanonicalPath = getResourcePath(resourceName);
        this.fileContent = this.getResourceFileContent(resourceName);
    }

    String getResourceFileContent(String resourceName) {
        InputStreamReader inputStream = new InputStreamReader((InputStream)Objects.requireNonNull(JsonSettingsFile.class.getClassLoader().getResourceAsStream(resourceName)), StandardCharsets.UTF_8);

        try {
            BufferedReader br = new BufferedReader(inputStream);

            String var4;
            try {
                var4 = (String)br.lines().collect(Collectors.joining(System.lineSeparator()));
            } catch (Throwable var7) {
                try {
                    br.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }

                throw var7;
            }

            br.close();
            return var4;
        } catch (IOException var8) {
            throw new UncheckedIOException(String.format("Reading of resource file '%1$s' was failed", resourceName), var8);
        }
    }

    static String getResourcePath(String resourceName) {
        try {
            URL resourceURL = JsonSettingsFile.class.getClassLoader().getResource(resourceName);
            return ((URL)Objects.requireNonNull(resourceURL)).getPath();
        } catch (NullPointerException var2) {
            throw new IllegalArgumentException(String.format("Resource file %1$s was not found or cannot be loaded", resourceName), var2);
        }
    }

    public String getResourceName() {
        return this.resourceName;
    }

    String getFileCanonicalPath() {
        return this.fileCanonicalPath;
    }

    String getFileContent() {
        return this.fileContent;
    }
}
