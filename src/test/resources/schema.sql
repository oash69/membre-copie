CREATE SCHEMA ABVVTFXA;
-- ABVVTFXA.FZZ definition

-- Drop table

-- DROP TABLE ABVVTFXA.FZZ;

CREATE TABLE ABVVTFXA.FZZ (
                              LIDNUMMER_INTERNAL_KEY DECIMAL(7,0) DEFAULT 0 NOT NULL,
                              GEBRUIKER_WIJZIGING CHAR(8) DEFAULT ' ' NOT NULL,
                              DATUM_WIJZIGING DECIMAL(7,0) DEFAULT 0 NOT NULL,
                              DATUM_OVERLIJDEN DECIMAL(7,0) DEFAULT 0 NOT NULL,
                              NATIONALITEIT_NIS CHAR(3) DEFAULT ' ' NOT NULL,
                              STATUS_GSM CHAR(1) DEFAULT ' ' NOT NULL,
                              STATUS_EMAIL CHAR(1) DEFAULT ' ' NOT NULL,
                              STATUS_TEL CHAR(1) DEFAULT ' ' NOT NULL,
                              NUMMER_MUTUALITEIT VARCHAR(19) DEFAULT '' NOT NULL,
                              NUMMER_SOCIALE_ZEKERHEID VARCHAR(12) DEFAULT '' NOT NULL,
                              NAAM_TITULARIS VARCHAR(35) DEFAULT '' NOT NULL,
                              RESERVE VARCHAR(40) DEFAULT '' NOT NULL,
                              GSM VARCHAR(15) DEFAULT '' NOT NULL,
                              EMAIL_ADRES VARCHAR(200) DEFAULT '' NOT NULL,
                              TEKST_CHAR50 VARCHAR(200) DEFAULT '' NOT NULL,
                              GELDIGHEIDSDATUM_DOCUMENT DECIMAL(7,0) DEFAULT 0 NOT NULL,
                              TIME_STAMP TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                              ERROR_EMAIL CHAR(1) DEFAULT ' ' NOT NULL,
                              VOORNAAM VARCHAR(70) DEFAULT ' ',
                              ACHTERNAAM VARCHAR(70) DEFAULT ' ',
                              CODE_LAND_GRENSARBEIDERS CHAR(1) DEFAULT ' ' NOT NULL,
                              DATUM_EMAIL DECIMAL(7,0) DEFAULT 0 NOT NULL
);
