SHOW DATABASES;

CREATE DATABASE IF NOT EXISTS cartoon_online;

USE cartoon_online;

# cartoon( cartoon_id[主键], cartoon_name, cartoon_cover )
CREATE TABLE cartton(
    cartoon_id VARCHAR(7) UNIQUE PRIMARY KEY NOT NULL COMMENT "番剧的唯一标识",
    cartoon_name VARCHAR(30) COMMENT "番剧的名字",
    cartoon_cover VARCHAR(50) COMMENT "番剧封面的存储路径"
) COMMENT "番剧基本信息表";

# carousel( cartoon_id[外键], carousel_cover )
CREATE TABLE carousel(
    cartoon_id VARCHAR(7) UNIQUE NOT NULL COMMENT "关联cartton表的标识",
    carousel_cover VARCHAR(50) COMMENT "走马灯展示的图片",
    FOREIGN KEY (cartoon_id) REFERENCES cartton (cartoon_id)
) COMMENT "走马灯展示信息";

SHOW TABLES;