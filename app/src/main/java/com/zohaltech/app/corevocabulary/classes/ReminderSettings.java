package com.zohaltech.app.corevocabulary.classes;

public class ReminderSettings
{
    private String startTime;
    private int intervals;
    private Reminder reminder;
    private boolean[] weekdays = new boolean[7];

    public ReminderSettings(String startTime, int intervals, Reminder reminder, boolean[] weekdays)
    {
        this.startTime = startTime;
        this.intervals = intervals;
        this.reminder = reminder;
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

    public Reminder getReminder()
    {
        return reminder;
    }

    public void setReminder(Reminder reminder)
    {
        this.reminder = reminder;
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
