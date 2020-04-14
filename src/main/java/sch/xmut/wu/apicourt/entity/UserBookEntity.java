package sch.xmut.wu.apicourt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wu on 2020/04/13
 */
@Table(name = "user_book")
@Entity
public class UserBookEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "court_id")
    private Integer courtId;
    @Column(name = "book_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookTime;
    @Column(name = "book_long")
    private Double bookLong;
    @Column(name = "money")
    private Double money;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourtId() {
        return courtId;
    }

    public void setCourtId(Integer courtId) {
        this.courtId = courtId;
    }

    public Date getBookTime() {
        return bookTime;
    }

    public void setBookTime(Date bookTime) {
        this.bookTime = bookTime;
    }

    public Double getBookLong() {
        return bookLong;
    }

    public void setBookLong(Double bookLong) {
        this.bookLong = bookLong;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
