-- 顾客信息表
create table customer_tbl(
   id number primary key,
   nextId number not null unique,
   takeDate date not null,
   type number not null,
   flag number not null
)
comment on table customer_tbl  is  '顾客信息表';
comment on column customer_tbl.id is '唯一标示';
comment on column customer_tbl.nextId is '顾客排号';
comment on column customer_tbl.takeDate is '取票日期';
comment on column customer_tbl.type is '服务类型';
comment on column customer_tbl.flag is '处理标记';

-- 业务端信息表
create table work_tbl(
 id number primary key,
 processDate date not null,
 workId number not null,
 type number not null,
 nextId number unique not null
)
comment on table work_tbl  is  '业务端信息表';
comment on column work_tbl.id is '唯一标示';
comment on column work_tbl.processDate is '处理日期';
comment on column work_tbl.workId is '服务台编号';
comment on column work_tbl.type is '服务类型';
comment on column work_tbl.nextId is '顾客排号';

-- 业务员信息表
create table workmen_tbl(
 id number primary key,
 name varchar2(20) not null,
 password varchar2(20) not null
)
comment on table workmen_tbl  is  '业务员信息表';
comment on column workmen_tbl.id is '唯一标示';
comment on column workmen_tbl.name is '用户名';
comment on column workmen_tbl.password is '密码';
-- 初始数据
insert into workmen_tbl
values(1,'kallen','123');

insert into workmen_tbl
values(2,'scott','123');


-- 自动出号信息
create table srt_tbl(
 nextId number(20) not null,
 type number(7) not null unique
)
comment on table srt_tbl  is  '自动出号信息';
comment on column srt_tbl.nextId is '顾客排号';
comment on column srt_tbl.type is '服务类型';
-- 初始数据
--vip业务
insert into srt_tbl
values(1,1);
-- 普通业务
insert into srt_tbl
values(1,2);

-- 临时表
create table hl_tbl(
 id number
)
-- 初始数据
insert into hl_tbl
values(0)

-- 创建w_seq序列
create sequence w_seq start with 1 increment by 1;
