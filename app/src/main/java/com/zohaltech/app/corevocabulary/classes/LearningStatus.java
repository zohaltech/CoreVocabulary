package com.zohaltech.app.corevocabulary.classes;


import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;

class LearningStatus {
    private int vocabIndex;
    private int progress;
    private int dayIndex;
    private int dayCount;
    private int vocabCount;

    static LearningStatus getLearningStatusByTheme(int themeId) {
        LearningStatus learningStatus = new LearningStatus();
        ReminderSettings settings = ReminderManager.getReminderSettings();

        if (settings.getStatus() == ReminderSettings.Status.FINISHED) {
            learningStatus.setProgress(100);
            if (themeId != 10 && themeId != 11) {
                learningStatus.setDayCount(6);
                learningStatus.setVocabCount(36);

            } else if (themeId == 10) {
                learningStatus.setDayCount(4);
                learningStatus.setVocabCount(24);
            } else {
                learningStatus.setDayCount(4);
                learningStatus.setVocabCount(12);
            }
        }

        Reminder reminder = settings.getReminder();
        if (reminder == null) {
            return null;
        }


        int currentVocabId = reminder.getId();
        Vocabulary currentVocab = Vocabularies.select(currentVocabId);
        assert currentVocab != null;

        ArrayList<Vocabulary> vocabularies = Vocabularies.selectByTheme(themeId);
        int vocabIndex = vocabularies.indexOf(currentVocab) + 1;


        if (themeId != 10 && themeId != 11) {
            learningStatus.setProgress((vocabIndex / 36) * 100);
            learningStatus.setDayCount(6);
            learningStatus.setVocabCount(36);

        } else if (themeId == 10) {
            learningStatus.setProgress((vocabIndex / 24) * 100);
            learningStatus.setDayCount(4);
            learningStatus.setVocabCount(24);
        } else {
            learningStatus.setProgress((vocabIndex / 12) * 100);
            learningStatus.setDayCount(4);
            learningStatus.setVocabCount(12);
        }

        return learningStatus;
    }

    public void learningStatus() {
    }

    public int getVocabIndex() {
        return vocabIndex;
    }

    private void setVocabIndex(int vocabIndex) {
        this.vocabIndex = vocabIndex;
    }

    public int getProgress() {
        return progress;
    }

    private void setProgress(int progress) {
        this.progress = progress;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    private void setDayIndex(int dayIndex) {
        this.dayIndex = dayIndex;
    }

    public int getDayCount() {
        return dayCount;
    }

    private void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public int getVocabCount() {
        return vocabCount;
    }

    private void setVocabCount(int vocabCount) {
        this.vocabCount = vocabCount;
    }
}
