public interface IWaren /* Interface */ {    
    int getArtikelID();
    String getName();
    String getBeschreibung();
    int getMenge();
    String getLagerort();
    String getStatus();

    void setArtikelID(int _artikelID);
    void setName(String _name);
    void setBeschreibung(String _beschreibung);
    void setMenge(int _menge);
    void setLagerort(String _lagerort);
    void setStatus(String _status);
    
}
