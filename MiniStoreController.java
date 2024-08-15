import java.sql.*;
import java.util.*;

public class MiniStoreController {
    Scanner sc = new Scanner(System.in);
    private Connection conn;

    public MiniStoreController(Connection conn) {
        this.conn = conn;
    }

    //Auflisten aller Waren
    public List<Warenhaus> getAlleWaren() throws SQLException {
        List <Warenhaus> warenListe = new ArrayList<>();
        String sql = "SELECT * FROM YA_Waren ORDER BY Artikel_ID";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Warenhaus ware = new Warenhaus();
                    ware.setArtikelID(rs.getInt("Artikel_ID"));
                    ware.setName(rs.getString("Name"));
                    ware.setBeschreibung(rs.getString("Beschreibung"));
                    ware.setMenge(rs.getInt("Menge"));
                    ware.setLagerort(rs.getString("Lagerort"));
                    ware.setStatus(rs.getString("Status"));

                    warenListe.add(ware);
                }
             }
        return warenListe;
    }

    //Auflisten Stornierter Waren
    public List<Warenhaus> getStornierteWaren() throws SQLException {
        List<Warenhaus> warenListe = new ArrayList<>();
        String sql = "SELECT * FROM YA_Waren WHERE Status = 'storniert' ORDER BY Artikel_ID";
    
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                Warenhaus ware = new Warenhaus();
                ware.setArtikelID(rs.getInt("Artikel_ID"));
                ware.setName(rs.getString("Name"));
                ware.setBeschreibung(rs.getString("Beschreibung"));
                ware.setMenge(rs.getInt("Menge"));
                ware.setLagerort(rs.getString("Lagerort"));
                ware.setStatus(rs.getString("Status"));
    
                warenListe.add(ware);
            }
        }
        return warenListe;
    }

    //Auflisten verfügbarer Waren
    public List<Warenhaus> getVerfuegbareWaren() throws SQLException {
        List<Warenhaus> warenListe = new ArrayList<>();
        String sql = "SELECT * FROM YA_Waren WHERE Status = 'verfügbar' ORDER BY Artikel_ID";
    
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                Warenhaus ware = new Warenhaus();
                ware.setArtikelID(rs.getInt("Artikel_ID"));
                ware.setName(rs.getString("Name"));
                ware.setBeschreibung(rs.getString("Beschreibung"));
                ware.setMenge(rs.getInt("Menge"));
                ware.setLagerort(rs.getString("Lagerort"));
                ware.setStatus(rs.getString("Status"));
    
                warenListe.add(ware);
            }
        }
        return warenListe;
    }

    //Hinzufügen - Eingabe
    public Warenhaus addWaren(Scanner sc) throws SQLException {
        System.out.println("Bitte geben sie die nötigen Daten für das Hinzufügen von Waren ein:");

        //Artikelname
        String[] nameHolder = new String[1];
        if (!errorName(sc, nameHolder)) {
            return null;
        }
        String Name = nameHolder[0];

        //Beschreibung
        System.out.println("Bitte geben Sie eine Beschreibung zu dem Produkt an:");
        sc.nextLine();  // Konsumiert den Zeilenumbruch, der nach dem letzten next() übrig bleibt.
        String Beschreibung = sc.nextLine();

        //Menge
        int[] mengeHolder = new int[1];
        if (!errorMenge(sc, mengeHolder)) {
            return null;
        }
        int Menge = mengeHolder[0];
        sc.nextLine();

        //Lagerort
        String[] lagerortHolder = new String[1];
        if (!errorLagerort(sc, lagerortHolder)) {
            return null;
        }
        String Lagerort = lagerortHolder[0];

        String Status = (Menge > 0) ? "verfügbar" : "storniert";
        
        Warenhaus newWaren = new Warenhaus();
        newWaren.setName(Name);
        newWaren.setBeschreibung(Beschreibung);
        newWaren.setMenge(Menge);
        newWaren.setLagerort(Lagerort);
        newWaren.setStatus(Status);

        insertNewWaren(conn, newWaren);
        return newWaren;
    }

    //Hinzufügen - Einfügen
    public void insertNewWaren(Connection conn, Warenhaus waren) throws SQLException {
        String sql = "INSERT INTO YA_Waren (Artikel_ID, Name, Beschreibung, Menge, Lagerort, Status) VALUES (seq_waren.NEXTVAL, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, waren.getName());
            stmt.setString(2, waren.getBeschreibung());
            stmt.setInt(3, waren.getMenge());
            stmt.setString(4, waren.getLagerort());
            stmt.setString(5, waren.getStatus());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Artikel erfolgreich eingefuegt!");                               
            } else {
                System.out.println("Fehler beim Einfuegen des Artikels.");
            }
        }
    }
    
    // Stornieren von Waren
    public void storniereWaren(Scanner sc) throws SQLException {
        List<Warenhaus> verfuegbareWaren = getVerfuegbareWaren();
        for (Warenhaus waren : verfuegbareWaren) {
            System.out.println(waren.toString());
        }
        System.out.print("Bitte geben Sie die Artikel-ID ein, die Sie stornieren möchten: ");
        int artikelID = sc.nextInt();

        String sql = "UPDATE YA_Waren SET Status = 'storniert' WHERE Artikel_ID = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, artikelID);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Artikel mit ID " + artikelID + " wurde erfolgreich storniert.");
            } else {
                System.out.println("Fehler: Artikel mit ID " + artikelID + " konnte nicht gefunden oder storniert werden.");
            }
        }
    }

    // Reaktivieren von Waren
    public void reaktiviereWaren(Scanner sc) throws SQLException {
        List<Warenhaus> stornierteWaren = getStornierteWaren();
        for (Warenhaus waren : stornierteWaren) {
            System.out.println(waren.toString());
        }
        System.out.print("Bitte geben Sie die Artikel-ID ein, die Sie reaktivieren möchten: ");
        int artikelID = sc.nextInt();

        String sql = "UPDATE YA_Waren SET Status = 'verfügbar' WHERE Artikel_ID = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, artikelID);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Artikel mit ID " + artikelID + " wurde erfolgreich verfügbar gemacht.");
            } else {
                System.out.println("Fehler: Artikel mit ID " + artikelID + " konnte nicht gefunden oder verfügbar gemacht werden.");
            }
        }
    }
    
    //Error-Handling
    //Artikelname
    public boolean errorName(Scanner sc, String[] nameHolder) { 
    	//Testfall 1: Eingabe eines gültigen Namens (z.B. "Tisch" oder "Computer").
    	//Testfall 2: Eingabe eines ungültigen Namens (z.B. mit Zahlen "Tisch123" oder mit Sonderzeichen "Computer!"). 
    	System.out.print("Name: ");
        nameHolder[0] = sc.next();

        if (nameHolder[0].length() <= 2 || nameHolder[0].length() > 50) {
            System.out.println("Fehler: Namen duerfen nicht kuerzer als 2 oder laenger als 50 Buchstaben sein.");
            return false;
        } else if (!nameHolder[0].matches("[a-zA-Z]+")) {
            System.out.println("Fehler: Der Name darf nur Buchstaben enthalten.");
            return false;
        }
        return true; // Name ist gültig
    } 

    // Menge
    public boolean errorMenge(Scanner sc, int[] mengeHolder) {
        //Testfall 1: Eingabe einer gültigen Menge (z.B. "10" oder "0")
        //Testfall 2: Eingabe einer ungültigen Menge (z.B. Negative zahlen wie "-1")
        System.out.print("Menge: ");
        
        if (sc.hasNextInt()) {
            mengeHolder[0] = sc.nextInt();
            
            if (mengeHolder[0] < 0) { // Menge darf nicht negativ sein
                System.out.println("Fehler: Die Menge darf nicht negativ sein.");
                return false;
            }
        } else {
            System.out.println("Fehler: Die Menge muss eine gültige Zahl sein.");
            sc.next(); // Ungültige Eingabe konsumieren
            return false;
        }

        return true; // Menge ist gültig
    }

    //Lagerort
    public boolean errorLagerort(Scanner sc, String[] lagerortHolder) {
        //Testfall 1: Eingabe eines gültigen Lagerorts (z.B. "Donaueschingen", "Raum 101" oder "Lager A")
        //Testfall 2: Eingabe eines ungültigen Lagerorts (z.B. mit Sonderzeichen "#Lager!" oder nur Ziffern "12345").
        System.out.print("Lagerort: ");
        lagerortHolder[0] = sc.nextLine();

        if (lagerortHolder[0].length() < 3 || lagerortHolder[0].length() > 50) {
            System.out.println("Fehler: Der Lagerort muss zwischen 3 und 50 Zeichen lang sein.");
            return false;
        } else if (!lagerortHolder[0].matches("[a-zA-Z0-9 ]+")) {
            System.out.println("Fehler: Der Lagerort darf nur Buchstaben, Ziffern und Leerzeichen enthalten.");
            return false;
        }
        return true; // Lagerort ist gültig
    }

    //WAS FEHLT:

    /*
     * DB Modell siehe AP
     * Hochladen in Git
     * Berichtshefte
     * Die Internationalität in der Progressnote aufgabe
     */
}
