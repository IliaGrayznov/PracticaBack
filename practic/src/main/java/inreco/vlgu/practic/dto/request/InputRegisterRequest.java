package inreco.vlgu.practic.dto.request;


import java.util.Date;


public class InputRegisterRequest {
    private long car_id;

    private Date date;

    private long idS1;

    private long idS2;

    private long idS3;

    public InputRegisterRequest(long car_id, Date date, long idS1, long idS2, long idS3) {
        this.car_id = car_id;
        this.date = date;
        this.idS1 = idS1;
        this.idS2 = idS2;
        this.idS3 = idS3;
    }

    public long getCar_id() {
        return car_id;
    }

    public void setCar_id(long car_id) {
        this.car_id = car_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getIdS1() {
        return idS1;
    }

    public void setIdS1(long idS1) {
        this.idS1 = idS1;
    }

    public long getIdS2() {
        return idS2;
    }

    public void setIdS2(long idS2) {
        this.idS2 = idS2;
    }

    public long getIdS3() {
        return idS3;
    }

    public void setIdS3(long idS3) {
        this.idS3 = idS3;
    }
}