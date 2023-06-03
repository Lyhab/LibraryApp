create table library(
	BID int auto_increment not null,
    Title varchar(50),
    Author varchar(30),
    Language varchar(10),
    Genre varchar(10),
    SID int unique,
    borrow_date date,
    return_date date,
    primary key (BID)
);

insert into library (Title, Author, Language, Genre, SID, borrow_date, return_date) values ('Harry Potter', 'J. K. Rowling', 'English', 'Fantasy', 20200484, '2023-06-01', '2003-06-05');
insert into library (Title, Author, Language, Genre, SID) values ('Moby-Dick', 'Herman Melville', 'English', 'Fantasy', 20200485);
insert into library (Title, Author, Language, Genre, SID, borrow_date) values ('Little Women', 'Louisa May Alcott', 'English', 'Novel', 20201030, '2023-01-04');

select * from library;