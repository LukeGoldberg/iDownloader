create table collection_record(
  id bigint(20) not null auto_increment comment 'ID',
  file_name varchar(50) not null comment 'file name',
  mongo_id varchar(100) default null comment 'mongo db _id',
  state char(50) default null comment 'collect state',
  create_time timestamp not null default CURRENT_TIMESTAMP comment 'create time',
  update_time timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment 'update time',
  primary key(id)
)Engine=InnoDB default charset=utf8 comment 'collections';