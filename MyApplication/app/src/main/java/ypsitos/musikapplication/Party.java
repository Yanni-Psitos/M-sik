package ypsitos.musikapplication;

/**
 * Created by David on 3/12/16.
 */
public class Party {
    private int id;
    private String name;
    private String creator;

    public Party(int id, String name, String creator) {
        this.id = id;
        this.name = name;
        this.creator = creator;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}