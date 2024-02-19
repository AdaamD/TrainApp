package logiqueTrain;

public class TrajetTrain {
    private String villeDepart;
    private String villeArrivee;
    private String heureDepart;
    private String heureArrivee;

    // Constructeur
    public TrajetTrain(String villeDepart, String villeArrivee, String heureDepart, String heureArrivee) {
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
    }

    // Getters et Setters
    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleArrivee(String villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(String heureArrivee) {
        this.heureArrivee = heureArrivee;
    }
}
