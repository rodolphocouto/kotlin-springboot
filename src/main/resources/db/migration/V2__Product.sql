create table product (
    id integer not null auto_increment,
    name varchar(100) not null,
    website varchar(100) not null,
    industry integer not null,
    company_id integer not null,
    constraint pk_product primary key (id),
    constraint fk_product_company foreign key (company_id) references company (id),
    constraint uk_product_name unique(name),
    constraint uk_product_website unique(website));

insert into product(name, website, industry, company_id) values ('Amazon.com', 'https://amazon.com', 1, 1);
insert into product(name, website, industry, company_id) values ('Amazon Studios', 'https://studios.amazon.com', 5, 1);
insert into product(name, website, industry, company_id) values ('Amazon Web Services', 'https://aws.amazon.com', 4, 1);

insert into product(name, website, industry, company_id) values ('Google Search', 'https://www.google.com', 2, 2);
insert into product(name, website, industry, company_id) values ('Google Plus', 'https://plus.google.com', 3, 2);
insert into product(name, website, industry, company_id) values ('Google Cloud Platform', 'https://cloud.google.com', 4, 2);
insert into product(name, website, industry, company_id) values ('Google Hangouts', 'https://hangouts.google.com', 6, 2);

insert into product(name, website, industry, company_id) values ('Facebook', 'https://www.facebook.com', 3, 3);
insert into product(name, website, industry, company_id) values ('Instagram', 'https://www.instagram.com', 3, 3);
insert into product(name, website, industry, company_id) values ('WhatsApp', 'https://www.whatsapp.com', 6, 3);