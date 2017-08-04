create table company (
    id integer not null auto_increment,
    name varchar(100) not null,
    website varchar(100) not null,
    revenue decimal(10,2) not null,
    employees integer not null,
    headquarters varchar(100) not null,
    founded date not null,
    constraint pk_company primary key (id),
    constraint uk_company_name unique(name),
    constraint uk_company_website unique(website));

insert into company(name, website, revenue, employees, headquarters, founded)
values ('Amazon.com Inc.', 'https://www.amazon.com', 135.99, 268908, 'Seattle, WA, USA', parsedatetime('1994-07-05', 'yyyy-MM-dd'));

insert into company(name, website, revenue, employees, headquarters, founded)
values ('Google Inc.', 'https://www.google.com', 89.46, 26316, 'Mountain View, California, USA', parsedatetime('1998-09-04', 'yyyy-MM-dd'));

insert into company(name, website, revenue, employees, headquarters, founded)
values ('Facebook Inc.', 'https://www.facebook.com', 27.64, 12691, 'Menlo Park, CA, USA', parsedatetime('2004-02-04', 'yyyy-MM-dd'));