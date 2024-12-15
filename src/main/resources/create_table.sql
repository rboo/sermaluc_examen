create table phone (id bigint auto_increment not null, city_code varchar(1), country_code varchar(2), number varchar(10), user_id bigint not null, primary key (id))
create table usr (id bigint auto_increment not null, email varchar(100), name varchar(100), password varchar(20), created date, modified date, last_login date, token varchar(50), active bit default true, primary key (id), unique (email))
alter table if exists phone add constraint FKcchyhsnqcjy7dfq2j4286bwk0 foreign key (user_id) references usr