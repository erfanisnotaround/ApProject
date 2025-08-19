package org.example.phaze2.controllers.loadingSave;

import org.example.phaze2.model.loadingSaves.Decision;

public interface LoadPrompt {
    Decision ask(String title, String message);
}