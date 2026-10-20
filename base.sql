DROP table etudiant;

CREATE table etudiant (
    id int primary key auto_increment,
    nom varchar(255) not null,
    age int not null,
    province varchar(255) not null
);

INSERT INTO etudiant (nom, age, province) VALUES ('Alice', 20, 'Ontario');
INSERT INTO etudiant (nom, age, province) VALUES ('Bob', 22, 'Quebec');