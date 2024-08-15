public class Warenhaus extends Artikel implements IWaren {
    
    private String status;

    // Implementierung der Status-Methoden
    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    // Methode zur Ausgabe der Warenhausinformationen
    @Override
    public String toString() {
        return "Artikel_ID = " + getArtikelID() +
                ", Name = " + getName() +
                ", Beschreibung = '" + getBeschreibung() + "'" +
                ", Menge = " + getMenge() +
                ", Lagerort = " + getLagerort() +
                ", Status = " + getStatus();
    }
}