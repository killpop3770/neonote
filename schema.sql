create table notes(
                      id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                      note varchar(100) not null,
                      date date not null
);