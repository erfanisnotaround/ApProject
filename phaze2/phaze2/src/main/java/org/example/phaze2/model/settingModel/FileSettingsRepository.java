package org.example.phaze2.model.settingModel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.phaze2.model.agentsAndManagers.JsonManager;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileSettingsRepository implements SettingsRepository {
    private final String file;
    private final JsonManager mapper;

    public FileSettingsRepository(String file) {
        this.file = file;
        mapper = new JsonManager(file);
    }

    @Override
    public SettingsData load() {
        try {

            return mapper.readObject(SettingsData.class);
        } catch (IOException e) {
            // On error, return defaults rather than crash
            return new SettingsData();
        }
    }

    @Override
    public void save(SettingsData data) {
        try {
            mapper.writeObject(data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save settings to " + file, e);
        }
    }



}
