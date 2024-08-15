import java.sql.*;

import java.util.*;

public class MiniStoreVerwaltung {
    private Connection conn;
    
    public static void main(String[] args) { //Verbindungsdaten Datenbank
        final String url = "jdbc:oracle:thin:@//localhost:1521/MF19";
        final String user = "mf_manager";
        final String password = "mf_manager";
        
        try {
            MiniStoreVerwaltung miniStoreVerwaltung = new MiniStoreVerwaltung(url, user, password);
            miniStoreVerwaltung.startMiniStoreVerwaltung();                     

        } catch (SQLException e) {
        	System.out.println("Verbindung zur Datenbank konnte nicht hergestellt werden: " + e.getMessage());
            return;
        }
    }
    
    //Konstruktor, der die Verbindung zur Datenbank initialisiert
    public MiniStoreVerwaltung(String url, String user, String password) throws SQLException {
        this.conn = DriverManager.getConnection(url, user, password);
        new MiniStoreController(conn);
        System.out.println("Verbunden!");
        System.out.println("Willkommen zur Patientenverwaltung!"); 
    }

    public void startMiniStoreVerwaltung() {
        Scanner sc = new Scanner(System.in);
        MiniStoreController storeController;

        try {
            storeController = new MiniStoreController(conn);
            while (true) {
                System.out.println("");
                System.out.println("Was möchten Sie tun?");
                System.out.println("1. Waren anzeigen");
                System.out.println("2. Stornierte Waren anzeigen");
                System.out.println("3. Verfügbare Waren anzeigen");
                System.out.println("4. Waren hinzufügen");
                System.out.println("5. Waren stornieren");
                System.out.println("6. Waren reaktivieren");
                System.out.println("123. Warenverwaltung herunterfahren");

                int choice = sc.nextInt();
            
                if (choice == 123) {
                    System.out.println("Warenverwaltung wird heruntergefahren...");
                    break;                  
                }
            
                switch (choice) {
                    case 1:
                        List<Warenhaus> alleWaren = storeController.getAlleWaren();
                        for (Warenhaus waren : alleWaren) {
                            System.out.println(waren.toString());                           
                        }
                        break;
                    case 2:
                        List<Warenhaus> stornierteWaren = storeController.getStornierteWaren();
                        for (Warenhaus waren : stornierteWaren) {
                            System.out.println(waren.toString());
                        }
                        break;
                    case 3:
                        List<Warenhaus> verfuegbareWaren = storeController.getVerfuegbareWaren();
                        for (Warenhaus waren : verfuegbareWaren) {
                            System.out.println(waren.toString());
                        }
                        break;
                    case 4:
                        storeController.addWaren(sc);
                        break;
                    case 5:
                        storeController.storniereWaren(sc);
                        break;
                    case 6:
                        storeController.reaktiviereWaren(sc);
                        break;
                    default:
                        System.out.println("Ungueltige Eingabe. Bitte erneut versuchen.");
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Ein Datenbankfehler ist aufgetreten: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}

