#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------

CREATE TABLE PERSONNE(
        nomPersonne         Varchar (30) ,
        adresse             Varchar (60) ,
        cp                  Int ,
        ville               Varchar (30) ,
        idCandidat          Int NOT NULL ,
        idCandidat_CANDIDAT Int ,
        PRIMARY KEY (idCandidat )
)ENGINE=InnoDB;

CREATE TABLE EQUIPE(
        nomEquipe  Varchar (30) ,
        idCandidat Int NOT NULL ,
        PRIMARY KEY (idCandidat )
)ENGINE=InnoDB;

CREATE TABLE CANDIDAT(
        idCandidat    int (11) Auto_increment  NOT NULL ,
        nomCandidat   Varchar (30) ,
        idCompetition Int ,
        PRIMARY KEY (idCandidat )
)ENGINE=InnoDB;

CREATE TABLE COMPETITION(
        idCompetition  int (11) Auto_increment  NOT NULL ,
        nomCompetition Varchar (60) ,
        PRIMARY KEY (idCompetition )
)ENGINE=InnoDB;

ALTER TABLE PERSONNE ADD CONSTRAINT FK_PERSONNE_idCandidat FOREIGN KEY (idCandidat) REFERENCES CANDIDAT(idCandidat);
ALTER TABLE PERSONNE ADD CONSTRAINT FK_PERSONNE_idCandidat_CANDIDAT FOREIGN KEY (idCandidat_CANDIDAT) REFERENCES CANDIDAT(idCandidat);
ALTER TABLE EQUIPE ADD CONSTRAINT FK_EQUIPE_idCandidat FOREIGN KEY (idCandidat) REFERENCES CANDIDAT(idCandidat);
ALTER TABLE CANDIDAT ADD CONSTRAINT FK_CANDIDAT_idCompetition FOREIGN KEY (idCompetition) REFERENCES COMPETITION(idCompetition);
