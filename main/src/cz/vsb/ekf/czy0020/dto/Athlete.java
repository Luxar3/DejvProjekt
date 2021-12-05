package cz.vsb.ekf.czy0020.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Athlete implements Comparable<Athlete>{
    private Integer id;
    private String name;
    private String surname;
    private Sport sport;
    private LocalDate birthDate;
    private BigDecimal prizeMoney;
    private Boolean isActive;
    private LocalDateTime lastUpdateTs;
    private Integer countOfMedals;

    public Integer getCountOfOscars() {
        return countOfMedals;
    }

    public void setCountOfMedals(Integer countOfMedals) {
        this.countOfMedals = countOfMedals;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public BigDecimal getPrizeMoney() {
        return prizeMoney;
    }

    public void setPrizeMoney(BigDecimal prizeMoney) {
        this.prizeMoney = prizeMoney;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getLastUpdateTs() {
        return lastUpdateTs;
    }

    public void setLastUpdateTs(LocalDateTime lasUpdateTs) {
        this.lastUpdateTs = lasUpdateTs;
    }

    @Override
    public String toString() {
        return "Athlete: " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", count of medals='" + countOfMedals + '\'' +
                ", sport=" + sport +
                ", birthDate=" + birthDate +
                ", prizeMoney=" + prizeMoney +
                ", isActive=" + isActive +
                ", lasUpdateTs=" + lastUpdateTs;
    }

    @Override
    public int compareTo(Athlete o) {
        if (getPrizeMoney()== null || o.getPrizeMoney() == null) {
      return 0;
    }
        return getPrizeMoney().compareTo(o.getPrizeMoney());
  }
    }

