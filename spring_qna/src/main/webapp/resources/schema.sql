create table board (
bno bigint not null auto_increment,
title varchar(200) not null,
category varchar(100) default null,
content text not null,
writer varchar(100) not null,
reg_at datetime default current_timestamp,
mod_at datetime default current_timestamp,
read_count int default '0',
cmt_qty int default '0',
file_count INT DEFAULT '0'
primary key (bno)
);

create table `user` (
id varchar(100) not null,
pwd varchar(1000) not null,
nick_name varchar(100) not null,
reg_at datetime default current_timestamp,
last_login datetime default null,
grade tinyint default '10',
primary key(id)
)default CHARSET=utf8mb4;

create table cmt(
cno bigint not null auto_increment,
bno bigint not null,
writer varchar(100) not null,
content varchar(2000) not null,
reg_at datetime default current_timestamp,
mod_at datetime default current_timestamp,
primary key(cno)
)default CHARSET=utf8mb4;

create table image_file (
uuid varchar(256) primary key,
save_dir varchar(1000) not null,
file_name varchar(1000) not null,
bno bigint not null,
file_size bigint not null,
reg_at datetime default current_timestamp
)default CHARSET=utf8mb4;

create table rep(
rno bigint not null auto_increment,
cno bigint not null,
bno bigint not null,
writer varchar(100) not null,
content varchar(2000) not null,
reg_at datetime default current_timestamp,
mod_at datetime default current_timestamp,
primary key(rno)
)default CHARSET=utf8mb4;
