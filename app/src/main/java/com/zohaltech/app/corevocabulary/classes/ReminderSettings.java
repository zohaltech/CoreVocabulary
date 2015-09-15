package com.zohaltech.app.corevocabulary.classes;

public class ReminderSettings
{
    private String startTime;
    private int intervals;
    private long vocabularyId;
    private boolean remindToday;
    private boolean[] weekdays = new boolean[7];

    public ReminderSettings(String startTime, int intervals, int vocabularyId, boolean remindToday, boolean[] weekdays)
    {
        this.startTime = startTime;
        this.intervals = intervals;
        this.vocabularyId = vocabularyId;
        this.remindToday = remindToday;
        this.weekdays = weekdays;
    }

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

    public long getVocabularyId()
    {
        return vocabularyId;
    }

    public void setVocabularyId(long vocabularyId)
    {
        this.vocabularyId = vocabularyId;
    }

    public boolean isRemindToday()
    {
        return remindToday;
    }

    public void setRemindToday(boolean remindToday)
    {
        this.remindToday = remindToday;
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
