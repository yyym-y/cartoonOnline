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



SHOW TABLES;
