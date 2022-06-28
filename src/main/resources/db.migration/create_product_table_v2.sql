create table if not exists Product (
id integer not null auto_increment,
productName varchar(50) not null,
productValue varchar(100) not null,
estoque_id integer not null,
primary key(id),
foreign key(estoque_id) references Estoque(id));