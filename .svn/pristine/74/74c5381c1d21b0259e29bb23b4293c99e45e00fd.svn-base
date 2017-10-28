SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS pc.t_rule_role;
DROP TABLE IF EXISTS pc.t_rule_user;
DROP TABLE IF EXISTS pc.t_rule;
DROP TABLE IF EXISTS pc.t_assement;




/* Create Tables */

-- 考评表
CREATE TABLE pc.t_assement
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 考评名称
	name varchar(100) COMMENT '考评名称',
	-- 年份
	year varchar(4) COMMENT '年份',
	-- 是否下发规则
	is_publish char(1) COMMENT '是否下发规则',
	PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = '考评表' DEFAULT CHARACTER SET utf8;


-- 考评规则表
CREATE TABLE pc.t_rule
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 父级编号
	parent_id varchar(64) NOT NULL COMMENT '父级编号',
	-- 考评编号
	assement_id varchar(64) NOT NULL COMMENT '考评编号',
	-- 是否评分项目
	is_score_time char(1) COMMENT '是否评分项目',
	-- 类型
	type char(2) COMMENT '类型',
	-- 分值上限
	sorce_limit int COMMENT '分值上限',
	-- 内容
	content varchar(1000) COMMENT '内容',
	PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = '考评规则表' DEFAULT CHARACTER SET utf8;


-- 考评规则的使用角色
CREATE TABLE pc.t_rule_role
(
	-- 规则编号
	rule_id varchar(64) NOT NULL COMMENT '规则编号',
	-- 角色编号
	role_id varchar(64) NOT NULL COMMENT '角色编号',
	PRIMARY KEY (rule_id, role_id)
) ENGINE = InnoDB COMMENT = '考评规则的使用角色' DEFAULT CHARACTER SET utf8;


-- 考评规则的使用用户
CREATE TABLE pc.t_rule_user
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 规则编号
	rule_id varchar(64) NOT NULL COMMENT '规则编号',
	-- 用户编号
	user_id varchar(64) NOT NULL COMMENT '用户编号',
	-- 使用类型
	use_type char(2) COMMENT '使用类型',
	PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = '考评规则的使用用户' DEFAULT CHARACTER SET utf8;



