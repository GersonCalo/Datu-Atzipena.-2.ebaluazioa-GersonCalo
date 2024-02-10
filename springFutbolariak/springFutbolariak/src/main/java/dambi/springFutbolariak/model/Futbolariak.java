package dambi.springFutbolariak.model;

import java.util.List;

import org.bson.types.ObjectId;

public class Futbolariak {

    private ObjectId id;
    private Player player;
    private List<String> positions;
    private String nationality;
    private int overall_rating;
    private int potential;
    private Double value_euro;
    private Double wage_euro;
    private String preferred_foot;
    private int international_reputation;
    private int weak_foot;
    private int skill_moves;
    private String body_type;
    private Double release_clause_euro;
    private String national_team;
    private int national_rating;
    private String national_team_position;
    private String national_jersey_number;
    private int crossing;
    private String finishing;
    private String heading_accuracy;
    private String short_passing;
    private String volleys;
    private String dribbling;
    private String curve;
    private String freekick_accuracy;
    private String long_passing;
    private String ball_control;
    private String acceleration;
    private String sprint_speed;
    private String agility;
    private String reactions;
    private String balance;
    private String shot_power;
    private String jumping;
    private String stamina;
    private String strength;
    private String long_shots;
    private String aggression;
    private String interceptions;
    private String positioning;
    private String vision;
    private String penalties;
    private String composure;
    private String marking;
    private String standing_tackle;
    private String sliding_tackle;
    
    
    public Futbolariak() {
    }
    
    public Futbolariak(ObjectId id, Player player, List<String> positions, String nationality, int overall_rating,
            int potential, Double value_euro, Double wage_euro, String preferred_foot, int international_reputation,
            int weak_foot, int skill_moves, String body_type, Double release_clause_euro, String national_team,
            int national_rating, String national_team_position, String national_jersey_number, int crossing,
            String finishing, String heading_accuracy, String short_passing, String volleys, String dribbling,
            String curve, String freekick_accuracy, String long_passing, String ball_control, String acceleration,
            String sprint_speed, String agility, String reactions, String balance, String shot_power, String jumping,
            String stamina, String strength, String long_shots, String aggression, String interceptions,
            String positioning, String vision, String penalties, String composure, String marking,
            String standing_tackle, String sliding_tackle) {
        this.id = id;
        this.player = player;
        this.positions = positions;
        this.nationality = nationality;
        this.overall_rating = overall_rating;
        this.potential = potential;
        this.value_euro = value_euro;
        this.wage_euro = wage_euro;
        this.preferred_foot = preferred_foot;
        this.international_reputation = international_reputation;
        this.weak_foot = weak_foot;
        this.skill_moves = skill_moves;
        this.body_type = body_type;
        this.release_clause_euro = release_clause_euro;
        this.national_team = national_team;
        this.national_rating = national_rating;
        this.national_team_position = national_team_position;
        this.national_jersey_number = national_jersey_number;
        this.crossing = crossing;
        this.finishing = finishing;
        this.heading_accuracy = heading_accuracy;
        this.short_passing = short_passing;
        this.volleys = volleys;
        this.dribbling = dribbling;
        this.curve = curve;
        this.freekick_accuracy = freekick_accuracy;
        this.long_passing = long_passing;
        this.ball_control = ball_control;
        this.acceleration = acceleration;
        this.sprint_speed = sprint_speed;
        this.agility = agility;
        this.reactions = reactions;
        this.balance = balance;
        this.shot_power = shot_power;
        this.jumping = jumping;
        this.stamina = stamina;
        this.strength = strength;
        this.long_shots = long_shots;
        this.aggression = aggression;
        this.interceptions = interceptions;
        this.positioning = positioning;
        this.vision = vision;
        this.penalties = penalties;
        this.composure = composure;
        this.marking = marking;
        this.standing_tackle = standing_tackle;
        this.sliding_tackle = sliding_tackle;
    }
    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public List<String> getPositions() {
        return positions;
    }
    public void setPositions(List<String> positions) {
        this.positions = positions;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public int getOverall_rating() {
        return overall_rating;
    }
    public void setOverall_rating(int overall_rating) {
        this.overall_rating = overall_rating;
    }
    public int getPotential() {
        return potential;
    }
    public void setPotential(int potential) {
        this.potential = potential;
    }
    public Double getValue_euro() {
        return value_euro;
    }
    public void setValue_euro(Double value_euro) {
        this.value_euro = value_euro;
    }
    public Double getWage_euro() {
        return wage_euro;
    }
    public void setWage_euro(Double wage_euro) {
        this.wage_euro = wage_euro;
    }
    public String getPreferred_foot() {
        return preferred_foot;
    }
    public void setPreferred_foot(String preferred_foot) {
        this.preferred_foot = preferred_foot;
    }
    public int getInternational_reputation() {
        return international_reputation;
    }
    public void setInternational_reputation(int international_reputation) {
        this.international_reputation = international_reputation;
    }
    public int getWeak_foot() {
        return weak_foot;
    }
    public void setWeak_foot(int weak_foot) {
        this.weak_foot = weak_foot;
    }
    public int getSkill_moves() {
        return skill_moves;
    }
    public void setSkill_moves(int skill_moves) {
        this.skill_moves = skill_moves;
    }
    public String getBody_type() {
        return body_type;
    }
    public void setBody_type(String body_type) {
        this.body_type = body_type;
    }
    public Double getRelease_clause_euro() {
        return release_clause_euro;
    }
    public void setRelease_clause_euro(Double release_clause_euro) {
        this.release_clause_euro = release_clause_euro;
    }
    public String getNational_team() {
        return national_team;
    }
    public void setNational_team(String national_team) {
        this.national_team = national_team;
    }
    public int getNational_rating() {
        return national_rating;
    }
    public void setNational_rating(int national_rating) {
        this.national_rating = national_rating;
    }
    public String getNational_team_position() {
        return national_team_position;
    }
    public void setNational_team_position(String national_team_position) {
        this.national_team_position = national_team_position;
    }
    public String getNational_jersey_number() {
        return national_jersey_number;
    }
    public void setNational_jersey_number(String national_jersey_number) {
        this.national_jersey_number = national_jersey_number;
    }
    public int getCrossing() {
        return crossing;
    }
    public void setCrossing(int crossing) {
        this.crossing = crossing;
    }
    public String getFinishing() {
        return finishing;
    }
    public void setFinishing(String finishing) {
        this.finishing = finishing;
    }
    public String getHeading_accuracy() {
        return heading_accuracy;
    }
    public void setHeading_accuracy(String heading_accuracy) {
        this.heading_accuracy = heading_accuracy;
    }
    public String getShort_passing() {
        return short_passing;
    }
    public void setShort_passing(String short_passing) {
        this.short_passing = short_passing;
    }
    public String getVolleys() {
        return volleys;
    }
    public void setVolleys(String volleys) {
        this.volleys = volleys;
    }
    public String getDribbling() {
        return dribbling;
    }
    public void setDribbling(String dribbling) {
        this.dribbling = dribbling;
    }
    public String getCurve() {
        return curve;
    }
    public void setCurve(String curve) {
        this.curve = curve;
    }
    public String getFreekick_accuracy() {
        return freekick_accuracy;
    }
    public void setFreekick_accuracy(String freekick_accuracy) {
        this.freekick_accuracy = freekick_accuracy;
    }
    public String getLong_passing() {
        return long_passing;
    }
    public void setLong_passing(String long_passing) {
        this.long_passing = long_passing;
    }
    public String getBall_control() {
        return ball_control;
    }
    public void setBall_control(String ball_control) {
        this.ball_control = ball_control;
    }
    public String getAcceleration() {
        return acceleration;
    }
    public void setAcceleration(String acceleration) {
        this.acceleration = acceleration;
    }
    public String getSprint_speed() {
        return sprint_speed;
    }
    public void setSprint_speed(String sprint_speed) {
        this.sprint_speed = sprint_speed;
    }
    public String getAgility() {
        return agility;
    }
    public void setAgility(String agility) {
        this.agility = agility;
    }
    public String getReactions() {
        return reactions;
    }
    public void setReactions(String reactions) {
        this.reactions = reactions;
    }
    public String getBalance() {
        return balance;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }
    public String getShot_power() {
        return shot_power;
    }
    public void setShot_power(String shot_power) {
        this.shot_power = shot_power;
    }
    public String getJumping() {
        return jumping;
    }
    public void setJumping(String jumping) {
        this.jumping = jumping;
    }
    public String getStamina() {
        return stamina;
    }
    public void setStamina(String stamina) {
        this.stamina = stamina;
    }
    public String getStrength() {
        return strength;
    }
    public void setStrength(String strength) {
        this.strength = strength;
    }
    public String getLong_shots() {
        return long_shots;
    }
    public void setLong_shots(String long_shots) {
        this.long_shots = long_shots;
    }
    public String getAggression() {
        return aggression;
    }
    public void setAggression(String aggression) {
        this.aggression = aggression;
    }
    public String getInterceptions() {
        return interceptions;
    }
    public void setInterceptions(String interceptions) {
        this.interceptions = interceptions;
    }
    public String getPositioning() {
        return positioning;
    }
    public void setPositioning(String positioning) {
        this.positioning = positioning;
    }
    public String getVision() {
        return vision;
    }
    public void setVision(String vision) {
        this.vision = vision;
    }
    public String getPenalties() {
        return penalties;
    }
    public void setPenalties(String penalties) {
        this.penalties = penalties;
    }
    public String getComposure() {
        return composure;
    }
    public void setComposure(String composure) {
        this.composure = composure;
    }
    public String getMarking() {
        return marking;
    }
    public void setMarking(String marking) {
        this.marking = marking;
    }
    public String getStanding_tackle() {
        return standing_tackle;
    }
    public void setStanding_tackle(String standing_tackle) {
        this.standing_tackle = standing_tackle;
    }
    public String getSliding_tackle() {
        return sliding_tackle;
    }
    public void setSliding_tackle(String sliding_tackle) {
        this.sliding_tackle = sliding_tackle;
    }
    

}
