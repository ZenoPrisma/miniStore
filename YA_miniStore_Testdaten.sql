--Table Insertion

INSERT ALL
    INTO YA_Waren (Artikel_ID, Name, Beschreibung, Menge, Lagerort, Status) VALUES (1, 'Kissen', 'Ein sehr weiches Kissen!', 20, 'Donaueschingen', 'verf�gbar')
    INTO YA_Waren (Artikel_ID, Name, Beschreibung, Menge, Lagerort, Status) VALUES (2, 'Laptop', 'Ein sehr guter Laptop!', 13, 'Donaueschingen', 'verf�gbar')
    INTO YA_Waren (Artikel_ID, Name, Beschreibung, Menge, Lagerort, Status) VALUES (3, 'Stuhl', 'Ein sehr bequemer Stuhl!', 0, 'Donaueschingen', 'storniert')
SELECT * FROM dual;

COMMIT;