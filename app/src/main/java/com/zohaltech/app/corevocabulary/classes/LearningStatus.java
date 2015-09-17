package com.zohaltech.app.corevocabulary.classes;


public class LearningStatus {
    private int totalVocabIndex;
    private int themeVocabIndex;
    private int totalDayIndex;
    private int themeDayIndex;

    public static LearningStatus getLearningStatusByTheme(int themeId) {
        Reminder reminder = ReminderManager.getReminderSettings().getReminder();
        if (reminder == null) {

return null;
        }

        LearningStatus learningStatus=new LearningStatus();


        return learningStatus;
    }


    public void learningStatus(int totalVocabIndex, int themeVocabIndex,
                               int totalDayIndex, int themeDayIndex) {
        setTotalVocabIndex(totalVocabIndex);
        setThemeVocabIndex(themeVocabIndex);
        setTotalDayIndex(totalDayIndex);
        setThemeDayIndex(themeDayIndex);

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


}
