set names utf8;
drop database if exists ht;
create database ht charset=utf8;
use ht;
#用户表
create table user(
    uId int primary key auto_increment,
    firstName varchar(32),
    lastName varchar(32),
    phone varchar(32),
    email varchar(20)
);
insert into user values
(null,'Lin1','jie1','18826278318','54323456545@qq.com'),
(null,'Lin2','jie2','18826278318','45323456545@qq.com'),
(null,'Lin3','jie3','18826278318','3123456545@qq.com'),
(null,'Lin4','jie4','18826278318','7323456545@qq.com'),
(null,'Lin5','jie5','18826278318','7323456545@qq.com'),
(null,'Lin6','jie6','18826278318','7323456545@qq.com'),
(null,'Lin7','jie7','18826278318','7323456545@qq.com'),
(null,'Lin8','jie8','18826278318','7323456545@qq.com'),
(null,'Lin9','jie9','18826278318','3723456545@qq.com');
