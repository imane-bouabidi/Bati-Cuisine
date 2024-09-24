CREATE DATABASE bati_cuisine;
USE bati_cuisine;
CREATE EXTENSION "uuid-ossp";

CREATE TYPE etat_projet AS ENUM ('EN_COURS', 'TERMINE', 'ANNULE');
CREATE TYPE type_composant AS ENUM ('MATERIAU', 'WORKER');

-- Création des tables

CREATE TABLE IF NOT EXISTS client (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nom VARCHAR(100) NOT NULL,
    adresse VARCHAR(255),
    telephone VARCHAR(15),
    est_professionnel BOOLEAN
    );

CREATE TABLE IF NOT EXISTS projet (
     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nom_projet VARCHAR(255),
    marge_beneficiaire DOUBLE PRECISION,
    cout_total DOUBLE PRECISION,
    etat_projet etat_projet DEFAULT 'EN_COURS',
    client_id UUID,
    FOREIGN KEY (client_id) REFERENCES client(id)
    );

CREATE TABLE IF NOT EXISTS composant (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nom VARCHAR(100),
    type_composant type_composant,
    taux_tva DOUBLE PRECISION,
    projet_id UUID,
    FOREIGN KEY (projet_id) REFERENCES projet(id)
    );

CREATE TABLE IF NOT EXISTS materiaux (
    cout_transport DOUBLE PRECISION,
    coefficient_quantite DOUBLE PRECISION,
    cout_unitaire DOUBLE PRECISION,
    quantite DOUBLE PRECISION
)
    INHERITS (composant);

CREATE TABLE IF NOT EXISTS mainoeuvre (
     productivite_ouvrier DOUBLE PRECISION,
     taux_horaire NUMERIC(10, 2),
    heures_travail NUMERIC(10, 2)
    )
    INHERITS (composant);

CREATE TABLE IF NOT EXISTS devis (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    montant_estime DOUBLE PRECISION,
    date_emission DATE,
    date_validite DATE,
    accepte BOOLEAN DEFAULT FALSE,
    projet_id UUID,
    FOREIGN KEY (projet_id) REFERENCES projet(id)
    );
