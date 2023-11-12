package step.learning.androidspu121.orm;

import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.view.View;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import step.learning.androidspu121.R;

public class ChatMessage {
    private String id;
    private String author;
    private String text;
    private String moment;

    transient private View view;

    private  static  final SimpleDateFormat momentFormat =
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.UK);
    private  static  final SimpleDateFormat outputFormat =
            new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.UK);
    public Date getMomentAsDate() {
        try {
            return  momentFormat.parse(this.getMoment());
            }
        catch (ParseException ex){
            Log.d("ChatMessage::getMomentAsDate",
                    ex.getMessage() + "" + this.getMoment());
            return null;
        }
    }

    public String getFormattedMoment() {
        try {
            Date date = momentFormat.parse(this.getMoment());
            return outputFormat.format(date);
        } catch (ParseException ex) {
            Log.e("ChatMessage::getFormattedMoment", "Error formatting date: " + ex.getMessage());
            return this.getMoment();
        }
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoment() {
        return moment;
    }

    public void setMoment(String moment) {
        this.moment = moment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private boolean isMine; // моі повідомлення

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }
}

/*
ORM for chat message
{
      "id": "2139",
      "author": "Max",
      "text": "123 :)",
      "moment": "2023-10-27 10:00:38"
    },
 */
