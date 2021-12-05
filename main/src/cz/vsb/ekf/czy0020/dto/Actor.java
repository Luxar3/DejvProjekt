package cz.vsb.ekf.czy0020.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Actor implements Comparable<Actor>, MostSuccess {
    private int id;
    private int countOfOscars;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountOfOscars() {
        return countOfOscars;
    }

    public void setCountOfOscars(int countOfOscars) {
        this.countOfOscars = countOfOscars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Actor: " + "id=" + id + ", countOfOscars=" + countOfOscars + ", name=" + name + "\n";
    }


    @Override
    public int compareTo(Actor o) {
        return getCountOfOscars() - o.getCountOfOscars();
    }

    @Override
    public List<? extends Actor> getMostSuccess(List original) {
        List<Actor> sortedByOscars = new ArrayList<>();
        Collections.sort(original, Collections.reverseOrder((Actor o1, Actor o2) -> o1.getCountOfOscars()-(o2.getCountOfOscars())));
        for (int i = 0; i < 10; i++) {
            sortedByOscars.add((Actor)(original.get(i)));
        }
        return  sortedByOscars;
    }
}
