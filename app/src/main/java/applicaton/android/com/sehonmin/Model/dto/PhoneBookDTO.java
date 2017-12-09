package applicaton.android.com.sehonmin.Model.dto;

/**
 * Created by Park on 2017-12-07.
 */

public class PhoneBookDTO {
    private String id;
    private String name;
    private String number;
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
