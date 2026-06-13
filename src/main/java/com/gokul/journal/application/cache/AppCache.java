package com.gokul.journal.application.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gokul.journal.application.entity.ConfigJournalAppEntity;
import com.gokul.journal.application.repos.ConfigJournalAppRepository;

@Component
public class AppCache {

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> appCacheMap;

    @PostConstruct
    public void init() {
        appCacheMap = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all) {
            appCacheMap.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }
}
