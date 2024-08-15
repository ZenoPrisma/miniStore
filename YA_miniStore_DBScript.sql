-- Sequence f�r die ID's
CREATE SEQUENCE seq_waren START WITH 4 INCREMENT BY 1;

--Table Creation
CREATE TABLE YA_Waren (
    Artikel_ID INT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    Beschreibung VARCHAR(255) NOT NULL,
    Menge INT NOT NULL,
    Lagerort VARCHAR(50) NOT NULL,
    Status VARCHAR(10) NOT NULL CHECK (Status IN ('verf�gbar', 'storniert'))
);

--Table Insertion

INSERT ALL
    INTO YA_Waren (Artikel_ID, Name, Beschreibung, Menge, Lagerort, Status) VALUES (1, 'Kissen', 'Ein sehr weiches Kissen!', 20, 'Donaueschingen', 'verf�gbar')
    INTO YA_Waren (Artikel_ID, Name, Beschreibung, Menge, Lagerort, Status) VALUES (2, 'Laptop', 'Ein sehr guter Laptop!', 13, 'Donaueschingen', 'verf�gbar')
    INTO YA_Waren (Artikel_ID, Name, Beschreibung, Menge, Lagerort, Status) VALUES (3, 'Stuhl', 'Ein sehr bequemer Stuhl!', 0, 'Donaueschingen', 'storniert')
SELECT * FROM dual;

--Deletion

--DROP SEQUENCE seq_waren;
--DROP TABLE YA_Waren;


COMMIT;