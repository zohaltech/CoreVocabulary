package com.zohaltech.app.corevocabulary.classes;


import android.content.Context;
import android.content.Intent;

import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.Calendar;

public class ReminderManager
{
    public static void add(Context context, Reminder reminder)
    {
        Intent intent = new Intent(context, AlarmService.class);
        intent.setAction(AlarmService.CREATE);
        intent.putExtra("reminder", reminder);
        context.startService(intent);
    }

    public static void setNextReminder(Context context, int vocabularyId)
    {
        Vocabulary nextVocabulary = Vocabularies.getNextVocabulary(vocabularyId);
        if (nextVocabulary == null)
        {

        }
        else
        {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, 10);

            add(context, new Reminder(nextVocabulary.getId(), calendar.getTime().getTime(), nextVocabulary.getVocabulary(), nextVocabulary.getVocabEnglishDef()));
        }
    }
}
