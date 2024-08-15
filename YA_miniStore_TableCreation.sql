-- Sequence für die ID's
CREATE SEQUENCE seq_waren START WITH 4 INCREMENT BY 1;

--Table Creation
CREATE TABLE YA_Waren (
    Artikel_ID INT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    Beschreibung VARCHAR(255) NOT NULL,
    Menge INT NOT NULL,
    Lagerort VARCHAR(50) NOT NULL,
    Status VARCHAR(10) NOT NULL CHECK (Status IN ('verfügbar', 'storniert'))
);

COMMIT;