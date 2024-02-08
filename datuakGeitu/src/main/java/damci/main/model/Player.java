package damci.main.model;

import java.util.List;

public class Player {
    private String name;
    private String full_name;
    private String birth_date;
    private String age;
    private String height_cm;
    private String weight_kgs;
    
    public Player(String name, String full_name, String birth_date, String age, String height_cm, String weight_kgs) {
        this.name = name;
        this.full_name = full_name;
        this.birth_date = birth_date;
        this.age = age;
        this.height_cm = height_cm;
        this.weight_kgs = weight_kgs;
        
    }
    public Player() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFull_name() {
        return full_name;
    }
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    public String getBirth_date() {
        return birth_date;
    }
    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getHeight_cm() {
        return height_cm;
    }
    public void setHeight_cm(String height_cm) {
        this.height_cm = height_cm;
    }
    public String getWeight_kgs() {
        return weight_kgs;
    }
    public void setWeight_kgs(String weight_kgs) {
        this.weight_kgs = weight_kgs;
    }
    

    
}
