package com.zohaltech.app.corevocabulary.classes;

import java.io.Serializable;
import java.util.Date;

public class Reminder implements Serializable
{
    private int Id;
    private Date Time;
    private String Title;
    private String Message;

    public Reminder(int id, Date time, String title, String message)
    {
        setId(id);
        setTime(time);
        setTitle(title);
        setMessage(message);
    }

    public int getId()
    {
        return Id;
    }

    public void setId(int id)
    {
        Id = id;
    }

    public Date getTime()
    {
        return Time;
    }

    public void setTime(Date time)
    {
        Time = time;
    }

    public String getTitle()
    {
        return Title;
    }

    public void setTitle(String title)
    {
        Title = title;
    }

    public String getMessage()
    {
        return Message;
    }

    public void setMessage(String message)
    {
        Message = message;
    }
}
