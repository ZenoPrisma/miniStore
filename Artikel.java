public class Artikel /* Superklasse */ {
    private int artikelID;
    private String name;
    private String beschreibung;
    private int menge;
    private String lagerort;
    
    //Standardkonstruktor
    public Artikel() {
    }

    // Getter und Setter für die Waren-Attribute
    public int getArtikelID() {
        return artikelID;
    }

    public void setArtikelID(int artikelID) {
        this.artikelID = artikelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public String getLagerort() {
        return lagerort;
    }

    public void setLagerort(String lagerort) {
        this.lagerort = lagerort;
    }
}
