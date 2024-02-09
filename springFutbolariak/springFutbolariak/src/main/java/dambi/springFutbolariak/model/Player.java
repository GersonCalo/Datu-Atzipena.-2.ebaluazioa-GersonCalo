package dambi.springFutbolariak.model;

public class Player {
    private String name;
    private String full_name;
    private String birth_date;
    private int age;
    private Double height_cm;
    private Double weight_kgs;
    public Player() {
    }
    public Player(String name, String full_name, String birth_date, int age, Double height_cm, Double weight_kgs) {
        this.name = name;
        this.full_name = full_name;
        this.birth_date = birth_date;
        this.age = age;
        this.height_cm = height_cm;
        this.weight_kgs = weight_kgs;
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Double getHeight_cm() {
        return height_cm;
    }
    public void setHeight_cm(Double height_cm) {
        this.height_cm = height_cm;
    }
    public Double getWeight_kgs() {
        return weight_kgs;
    }
    public void setWeight_kgs(Double weight_kgs) {
        this.weight_kgs = weight_kgs;
    }

    
}
