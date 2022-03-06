
CREATE TABLE `employee`
( `id` int(11) NOT NULL AUTO_INCREMENT, `name` varchar(50) NOT NULL, `address` varchar(100) NOT NULL, `photo` varchar(200) DEFAULT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=1006 DEFAULT CHARSET=utf8;


insert into `employee`(`id`,`name`,`address`,`photo`)
values (1001,'张三','北京','a.jpg'),(1002,'李四','上海','b.jpg');


current.setAddress(employee.getAddress());
current.setName(employee.getName());
current.setPhoto(employee.getPhoto());