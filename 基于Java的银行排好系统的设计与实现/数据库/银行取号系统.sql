-- �˿���Ϣ��
create table customer_tbl(
   id number primary key,
   nextId number not null unique,
   takeDate date not null,
   type number not null,
   flag number not null
)
comment on table customer_tbl  is  '�˿���Ϣ��';
comment on column customer_tbl.id is 'Ψһ��ʾ';
comment on column customer_tbl.nextId is '�˿��ź�';
comment on column customer_tbl.takeDate is 'ȡƱ����';
comment on column customer_tbl.type is '��������';
comment on column customer_tbl.flag is '������';

-- ҵ�����Ϣ��
create table work_tbl(
 id number primary key,
 processDate date not null,
 workId number not null,
 type number not null,
 nextId number unique not null
)
comment on table work_tbl  is  'ҵ�����Ϣ��';
comment on column work_tbl.id is 'Ψһ��ʾ';
comment on column work_tbl.processDate is '��������';
comment on column work_tbl.workId is '����̨���';
comment on column work_tbl.type is '��������';
comment on column work_tbl.nextId is '�˿��ź�';

-- ҵ��Ա��Ϣ��
create table workmen_tbl(
 id number primary key,
 name varchar2(20) not null,
 password varchar2(20) not null
)
comment on table workmen_tbl  is  'ҵ��Ա��Ϣ��';
comment on column workmen_tbl.id is 'Ψһ��ʾ';
comment on column workmen_tbl.name is '�û���';
comment on column workmen_tbl.password is '����';
-- ��ʼ����
insert into workmen_tbl
values(1,'kallen','123');

insert into workmen_tbl
values(2,'scott','123');


-- �Զ�������Ϣ
create table srt_tbl(
 nextId number(20) not null,
 type number(7) not null unique
)
comment on table srt_tbl  is  '�Զ�������Ϣ';
comment on column srt_tbl.nextId is '�˿��ź�';
comment on column srt_tbl.type is '��������';
-- ��ʼ����
--vipҵ��
insert into srt_tbl
values(1,1);
-- ��ͨҵ��
insert into srt_tbl
values(1,2);

-- ��ʱ��
create table hl_tbl(
 id number
)
-- ��ʼ����
insert into hl_tbl
values(0)

-- ����w_seq����
create sequence w_seq start with 1 increment by 1;
