package com.example.user.navigationdrawer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "RENT_CALENDER",indices = {@Index(value = "oid",unique = true)},foreignKeys = @ForeignKey(entity = Occupants.class ,parentColumns = "oid",childColumns = "rent_oid",onUpdate = 5,onDelete = 5))
public class RentCalender {
    @PrimaryKey(autoGenerate = true)@NonNull
    Integer sr_no;
    @NonNull
    Integer room_oid;
    String year,jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec;

    public RentCalender() {
    }

    public RentCalender(@NonNull Integer sr_no, @NonNull Integer room_oid, String year, String jan, String feb, String mar, String apr, String may, String jun, String jul, String aug, String sep, String oct, String nov, String dec) {
        this.sr_no = sr_no;
        this.room_oid = room_oid;
        this.year = year;
        this.jan = jan;
        this.feb = feb;
        this.mar = mar;
        this.apr = apr;
        this.may = may;
        this.jun = jun;
        this.jul = jul;
        this.aug = aug;
        this.sep = sep;
        this.oct = oct;
        this.nov = nov;
        this.dec = dec;
    }

    @NonNull
    public Integer getSr_no() {
        return sr_no;
    }

    public void setSr_no(@NonNull Integer sr_no) {
        this.sr_no = sr_no;
    }

    @NonNull
    public Integer getRoom_oid() {
        return room_oid;
    }

    public void setRoom_oid(@NonNull Integer room_oid) {
        this.room_oid = room_oid;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getJan() {
        return jan;
    }

    public void setJan(String jan) {
        this.jan = jan;
    }

    public String getFeb() {
        return feb;
    }

    public void setFeb(String feb) {
        this.feb = feb;
    }

    public String getMar() {
        return mar;
    }

    public void setMar(String mar) {
        this.mar = mar;
    }

    public String getApr() {
        return apr;
    }

    public void setApr(String apr) {
        this.apr = apr;
    }

    public String getMay() {
        return may;
    }

    public void setMay(String may) {
        this.may = may;
    }

    public String getJun() {
        return jun;
    }

    public void setJun(String jun) {
        this.jun = jun;
    }

    public String getJul() {
        return jul;
    }

    public void setJul(String jul) {
        this.jul = jul;
    }

    public String getAug() {
        return aug;
    }

    public void setAug(String aug) {
        this.aug = aug;
    }

    public String getSep() {
        return sep;
    }

    public void setSep(String sep) {
        this.sep = sep;
    }

    public String getOct() {
        return oct;
    }

    public void setOct(String oct) {
        this.oct = oct;
    }

    public String getNov() {
        return nov;
    }

    public void setNov(String nov) {
        this.nov = nov;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }
}
