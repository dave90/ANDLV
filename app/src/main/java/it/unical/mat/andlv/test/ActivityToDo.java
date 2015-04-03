package it.unical.mat.andlv.test;

import it.unical.mat.andlv.base.mapper.Predicate;
import it.unical.mat.andlv.base.mapper.Term;

/**
  */
@Predicate("activity_to_do")
public class ActivityToDo {
    @Term(0)
    private String type;
    @Term(1)
    private int howLong;

    public ActivityToDo(){

    }
    @Override
    public String toString() {
        return "ActivityToDo{" +
                "type='" + type + '\'' +
                ", howLong=" + howLong +
                '}';
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHowLong(int howLong) {
        this.howLong = howLong;
    }

    public String getType() {

        return type;
    }

    public int getHowLong() {
        return howLong;
    }

    public ActivityToDo(String type, int howLong) {
        this.type = type;
        this.howLong = howLong;
    }
}
