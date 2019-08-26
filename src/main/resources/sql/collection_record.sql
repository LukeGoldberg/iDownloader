create table collection_record(
  id bigint(20) not null comment 'ID',
  file_name varchar(50) not null comment 'file name',
  mongo_id varchar(100) not null comment 'mongo db _id',
  create_time timestramp not null comment 'create time',
  update_time timestramp not null comment 'update time',
  primary key(id)
)Engine=InnoDB default charset=utf8;