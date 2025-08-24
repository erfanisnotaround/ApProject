package org.example.phaze2.model.levelsModel;


public record LevelDescriptor(
        int id,
        String title,
        boolean locked,
        Integer suggestedWidth,
        Integer suggestedHeight
) {}