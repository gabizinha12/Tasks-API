create table task (id bigserial not null, deadline varchar(255), description varchar(255), title varchar(255), primary key (id));
alter table if exists task add constraint UK_nuxjdiq9o90t2l66b8nyurq4t unique (description);
alter table if exists task add constraint UK_20c7byw48jcthxnvt67bbvijq unique (title);
