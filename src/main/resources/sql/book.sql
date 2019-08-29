create table book(
  id bigint(20) not null auto_increment comment 'id',
  book_name varchar(100) not null comment 'book name',
  primary key (id),
  index idx_book_name (book_name)
)Engine=InnoDB default charset=utf8;