package com.zohaltech.app.corevocabulary.classes;


import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;

public class LearningStatus {
    private int totalVocabIndex;
    private int themeVocabIndex;
    private int totalDayIndex;
    private int themeDayIndex;
    private int vocabCount;


    public static LearningStatus getLearningStatusByTheme(int themeId) {
        Reminder reminder = ReminderManager.getReminderSettings().getReminder();
        if (reminder == null) {
            return null;
        }
        int currentVocabId = reminder.getId();
        Vocabulary currentVocab = Vocabularies.select(currentVocabId);
        assert currentVocab != null;

        ArrayList<Vocabulary> vocabularies = Vocabularies.selectByTheme(themeId);
        int themeVocabIndex = vocabularies.indexOf(currentVocab) + 1;
        int totalDayIndex = 0;

        if (themeId < 11) {
            totalDayIndex = (themeId - 1) * 6 + currentVocab.getDay();
        } else if (themeId == 11) {
            totalDayIndex = (9 * 6) + 4 + currentVocab.getDay();
        } else if (themeId >= 12) {
            totalDayIndex = (9 * 6) + 6 + currentVocab.getDay();
        }

        LearningStatus learningStatus = new LearningStatus();
        learningStatus.setTotalDayIndex(currentVocabId);
        learningStatus.setVocabCount(432);
        learningStatus.setThemeDayIndex(currentVocab.getDay());
        learningStatus.setThemeVocabIndex(themeVocabIndex);
        learningStatus.setTotalDayIndex(totalDayIndex);

        return learningStatus;
    }


    public void learningStatus() {
    }

    public int getTotalVocabIndex() {
        return totalVocabIndex;
    }

    public void setTotalVocabIndex(int totalVocabIndex) {
        this.totalVocabIndex = totalVocabIndex;
    }

    public int getThemeVocabIndex() {
        return themeVocabIndex;
    }

    public void setThemeVocabIndex(int themeVocabIndex) {
        this.themeVocabIndex = themeVocabIndex;
    }

    public int getTotalDayIndex() {
        return totalDayIndex;
    }

    public void setTotalDayIndex(int totalDayIndex) {
        this.totalDayIndex = totalDayIndex;
    }

    public int getThemeDayIndex() {
        return themeDayIndex;
    }

    public void setThemeDayIndex(int themeDayIndex) {
        this.themeDayIndex = themeDayIndex;
    }

    public int getVocabCount() {
        return vocabCount;
    }

    public void setVocabCount(int vocabCount) {
        this.vocabCount = vocabCount;
    }
}
