package inreco.vlgu.practic.dto.car;

public class RegisterCarRequest {

    private String number;

    private String mark;

    private String model;

    public RegisterCarRequest(String number, String mark, String model) {
        this.number = number;
        this.mark = mark;
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}