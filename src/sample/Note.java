package sample;

import java.sql.Date;

public class Note {
    private int id;
    private String note;
    private Date date;

    public Note(int id, String note, Date date){
        this.id = id;
        this.note = note;
        this.date = date;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note = note;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

}
