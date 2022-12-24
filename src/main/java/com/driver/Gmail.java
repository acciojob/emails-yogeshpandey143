package com.driver;

import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.lang3.tuple.Triple;

public class Gmail extends Email {
    int inboxCapacity;
    private ArrayList<Triple<Date, String, String>> Inbox;
    private ArrayList<Triple<Date, String, String>> Trash;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.Inbox = new ArrayList();
        this.Trash = new ArrayList();
    }

    public void receiveMail(Date date, String sender, String message) {
        Triple mail;
        if (this.Inbox.size() == this.inboxCapacity) {
            mail = (Triple)this.Inbox.get(0);
            this.Inbox.remove(0);
            this.Trash.add(mail);
        }

        mail = Triple.of(date, sender, message);
        this.Inbox.add(mail);
    }

    public void deleteMail(String message) {
        int index = -1;

        for(int i = 0; i < this.Inbox.size(); ++i) {
            if (message.equals(((Triple)this.Inbox.get(i)).getRight())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            this.Trash.add((Triple)this.Inbox.get(index));
            this.Inbox.remove(index);
        }

    }

    public String findLatestMessage() {
        return this.Inbox.isEmpty() ? null : (String)((Triple)this.Inbox.get(this.Inbox.size() - 1)).getRight();
    }

    public String findOldestMessage() {
        return this.Inbox.isEmpty() ? null : (String)((Triple)this.Inbox.get(0)).getRight();
    }

    public int findMailsBetweenDates(Date start, Date end) {
        int cnt = 0;

        for(int i = 0; i < this.Inbox.size(); ++i) {
            if (((Date)((Triple)this.Inbox.get(i)).getLeft()).compareTo(start) >= 0 && ((Date)((Triple)this.Inbox.get(i)).getLeft()).compareTo(end) <= 0) {
                ++cnt;
            }
        }

        return cnt;
    }

    public int getInboxSize() {
        return this.Inbox.size();
    }

    public int getTrashSize() {
        return this.Trash.size();
    }

    public void emptyTrash() {
        this.Trash.clear();
    }

    public int getInboxCapacity() {
        return this.inboxCapacity;
    }
}
