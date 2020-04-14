package sch.xmut.wu.apicourt.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

/**
 * Created by wu on 2020/04/14
 */
public class UserBookRequest {
    @JsonProperty("court_id")
    private Integer courtId;
    @JsonProperty("book_time")
    private Date bookTime;
    @JsonProperty("book_long")
    private Double bookLong;
    @JsonProperty("money")
    private Double money;

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
}
