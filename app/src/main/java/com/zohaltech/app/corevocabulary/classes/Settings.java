package com.zohaltech.app.corevocabulary.classes;

public class Settings
{
    private String startTime;
    private int intervals;
    private int currentVocabularyId;
    private boolean[] weekdays = new boolean[7];

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public int getIntervals()
    {
        return intervals;
    }

    public void setIntervals(int intervals)
    {
        this.intervals = intervals;
    }

    public int getCurrentVocabularyId()
    {
        return currentVocabularyId;
    }

    public void setCurrentVocabularyId(int currentVocabularyId)
    {
        this.currentVocabularyId = currentVocabularyId;
    }

    public boolean[] getWeekdays()
    {
        return weekdays;
    }

    public void setWeekdays(boolean[] weekdays)
    {
        this.weekdays = weekdays;
    }


}
