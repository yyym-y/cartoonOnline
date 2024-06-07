SHOW DATABASES;

CREATE DATABASE IF NOT EXISTS cartoon_online;

USE cartoon_online;

DROP TABLE IF EXISTS carousel;
DROP TABLE IF EXISTS cartoon;

DROP TABLE IF EXISTS m3u8_info;

# cartoon( cartoon_id[主键], cartoon_name, cartoon_cover, cartoon_permit)
CREATE TABLE cartoon(
    cartoon_id VARCHAR(7) UNIQUE PRIMARY KEY NOT NULL COMMENT "番剧的唯一标识",
    cartoon_name VARCHAR(30) COMMENT "番剧的名字",
    cartoon_cover VARCHAR(50) COMMENT "番剧封面的存储路径",
    cartoon_permit INT COMMENT "是否为VIP, VIP为1, 普通为0"
) COMMENT "番剧基本信息表";

# carousel( cartoon_id[外键], carousel_cover )
CREATE TABLE carousel(
    cartoon_id VARCHAR(7) UNIQUE NOT NULL COMMENT "关联cartton表的标识",
    carousel_cover VARCHAR(50) COMMENT "走马灯展示的图片",
    FOREIGN KEY (cartoon_id) REFERENCES cartoon (cartoon_id)
) COMMENT "走马灯展示信息";

# cartoon_episode( cartoon_id[外键], num )
CREATE TABLE cartoon_episode(
    cartoon_id VARCHAR(7) UNIQUE NOT NULL COMMENT "关联cartton表的标识",
    num INT NOT NULL COMMENT "一共有多少集",
    FOREIGN KEY (cartoon_id) REFERENCES cartoon (cartoon_id)
) COMMENT "集数信息";

# m3u8_info( cartoon_id[外键], num, m3u8Url )
CREATE TABLE m3u8_info(
    cartoon_id VARCHAR(7) NOT NULL COMMENT "关联cartton表的标识",
    num INT NOT NULL COMMENT "第几集",
    m3u8_url VARCHAR(50) COMMENT "m3u8文件地址",
    FOREIGN KEY (cartoon_id) REFERENCES cartoon (cartoon_id)
) COMMENT "标识m3u8文件地址";

# user_info( uid[主键], username, password, email, type )
CREATE TABLE user_info(
    uid VARCHAR(7) NOT NULL COMMENT "用户唯一标识",
    username VARCHAR(30) NOT NULL COMMENT "用户名",
    password VARCHAR(30) NOT NULL COMMENT "密码",
    email VARCHAR(30) NOT NULL UNIQUE COMMENT "邮箱",
    type INT NOT NULL COMMENT "用户级别 0-管理员/1-普通用户/2-VIP用户"
) COMMENT "用户表";

# code_tem( email, code, time )
CREATE TABLE code_tem(
    email VARCHAR(30) NOT NULL UNIQUE COMMENT "邮箱",
    code VARCHAR(7) NOT NULL COMMENT "验证码",
    time DateTime NOT NULL COMMENT "创造的时间"
) COMMENT "验证码临时表";

# play_infos( cartoon_id[外键],  year, month, play_time )
CREATE TABLE play_infos(
    cartoon_id VARCHAR(7) NOT NULL COMMENT "关联cartton表的标识",
    year INT NOT NULL COMMENT "年份",
    month INT NOT NULL COMMENT "月份",
    play_time INT NOT NULL COMMENT "播放量"
) COMMENT "播放量数据表";

# descrip( cartoon_id[外键], discrip )
CREATE TABLE descrip(
    cartoon_id VARCHAR(7) NOT NULL COMMENT "关联cartton表的标识",
    descrip VARCHAR(500) COMMENT "简介"
) COMMENT "简介表";

CREATE TABLE tag_group(
    tag_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT "tag id",
    type VARCHAR(15) NOT NULL COMMENT "标识标签的类别",
    name VARCHAR(20) NOT NULL COMMENT "标签具体的名字"
) COMMENT "标签类别表";

CREATE TABLE category(
    cartoon_id VARCHAR(7) NOT NULL COMMENT "关联cartton表的标识",
    tag_id INT NOT NULL COMMENT "tag 标签id"
) COMMENT "影视-标签";

CREATE TABLE orders(
    order_id VARCHAR(40) PRIMARY KEY NOT NULL UNIQUE COMMENT "订单编号",
    uid VARCHAR(7) NOT NULL COMMENT "用户唯一标识",
    buyer_id VARCHAR(40) COMMENT "买家在支付宝唯一id",
    buyer_pay_amount INT COMMENT "买家付款金额",
    trade_no VARCHAR(40) COMMENT "支付宝交易凭证号",
    gmt_payment DATETIME COMMENT "买家付款时间",
    order_create DATETIME COMMENT "创建订单时间"
) COMMENT "订单表";

drop TABLE orders;

SHOW TABLES;
DROP TABLE play_infos;


INSERT INTO user_info VALUES("d981034", "admin", "123456", "3109778990@qq.com", 0);

SELECT * FROM user_info;
SELECT * FROM code_tem;


INSERT INTO cartoon VALUES()


