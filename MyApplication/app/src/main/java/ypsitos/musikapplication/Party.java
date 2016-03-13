package ypsitos.musikapplication;

/**
 * Created by David on 3/12/16.
 */
public class Party {
    private int id;
    private String type;
    private String address;
    private String date;
    private String time;

    public Party(int id, String type, String address, String date, String time) {

        this.id = id;
        this.type = type;
        this.address = address;
        this.date = date;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
