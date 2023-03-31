package framework.settings;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JsonSettingsFile implements ISettingsFile {
    private final ObjectMapper mapper = new ObjectMapper();
    private final String content;

    public JsonSettingsFile(File file) throws IOException {
        this.content = this.getFileContent(file.getCanonicalPath());
    }

    public JsonSettingsFile(String resourceName) {
        ResourceFile resourceFile = new ResourceFile(resourceName);
        this.content = resourceFile.getFileContent();
    }

    public Object getValue(String jsonPath) {
        return this.getEnvValueOrDefault(jsonPath, true);
    }

    private Object getEnvValueOrDefault(String jsonPath, boolean throwIfEmpty) {
        String envVar = this.getEnvValue(jsonPath);
        JsonNode node = this.getJsonNode(jsonPath, throwIfEmpty && envVar == null);
        return node.isMissingNode() ? envVar : this.castEnvOrDefaultValue(node, envVar);
    }

    private Object castEnvOrDefaultValue(JsonNode node, String envVar) {
        if (node.isBoolean()) {
            return envVar == null ? node.asBoolean() : Boolean.parseBoolean(envVar);
        } else if (node.isLong()) {
            return envVar == null ? node.asLong() : Long.parseLong(envVar);
        } else if (node.isInt()) {
            return envVar == null ? node.asInt() : Integer.parseInt(envVar);
        } else if (node.isDouble()) {
            return envVar == null ? node.asDouble() : Double.parseDouble(envVar);
        } else if (node.isObject()) {
            return envVar == null ? node.toString() : envVar;
        } else {
            return envVar == null ? node.asText() : envVar;
        }
    }

    private String getEnvValue(String jsonPath) {
        String key = jsonPath.replace("/", ".").substring(1, jsonPath.length());
        String envVar = System.getProperty(key);
        if (envVar != null) {
            //Selenide.getInstance().debug(String.format("***** Using variable passed from environment %1$s=%2$s", key, envVar));
        }

        return envVar;
    }

    public List<String> getList(String jsonPath) {
        String envVar = this.getEnvValue(jsonPath);
        List list;
        if (envVar != null) {
            list = (List)Arrays.stream(envVar.split(",")).map(String::trim).collect(Collectors.toList());
        } else {
            Spliterator<JsonNode> spliterator = Spliterators.spliteratorUnknownSize(this.getJsonNode(jsonPath).elements(), 16);
            list = (List)StreamSupport.stream(spliterator, false).map(JsonNode::asText).collect(Collectors.toList());
        }

        return list;
    }

    public Map<String, Object> getMap(String jsonPath) {
        Spliterator<Map.Entry<String, JsonNode>> spliterator = Spliterators.spliteratorUnknownSize(this.getJsonNode(jsonPath).fields(), 16);
        return (Map)StreamSupport.stream(spliterator, false).collect(Collectors.toMap(Map.Entry::getKey, (entry) -> {
            return this.getValue(jsonPath + "/" + (String)entry.getKey());
        }));
    }

    private JsonNode getJsonNode(String jsonPath) {
        return this.getJsonNode(jsonPath, true);
    }

    private JsonNode getJsonNode(String jsonPath, boolean throwIfEmpty) {
        String errorMessage = String.format("Json field by json-path %1$s was not found in the file %2$s", jsonPath, this.content);

        JsonNode nodeAtPath;
        try {
            JsonNode node = this.mapper.readTree(this.content);
            nodeAtPath = node.at(jsonPath);
        } catch (IOException var6) {
            throw new UncheckedIOException(errorMessage, var6);
        }

        if (throwIfEmpty && nodeAtPath.isMissingNode()) {
            throw new IllegalArgumentException(errorMessage);
        } else {
            return nodeAtPath;
        }
    }

    private String getFileContent(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException var3) {
            throw new UncheckedIOException(String.format("Content of file %1$s can't be read as String", filename), var3);
        }
    }

    public boolean isValuePresent(String path) {
        Object value = this.getEnvValueOrDefault(path, false);
        return value != null && !value.toString().trim().isEmpty();
    }
}
