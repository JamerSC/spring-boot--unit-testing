drop table student_sequence;
drop table students;

    create table student_sequence (
        next_val bigint
    ) engine=InnoDB;
# Hibernate: 
    insert into student_sequence values ( 1 );
#Hibernate: 
    create table students (
        id bigint not null,
        email varchar(255) not null,
        gender enum ('FEMALE','MALE','OTHER') not null,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;
# Hibernate: 
    alter table students 
       drop index UKe2rndfrsx22acpq2ty1caeuyw;
# Hibernate: 
    alter table students 
       add constraint UKe2rndfrsx22acpq2ty1caeuyw unique (email);
       
insert into `students` (`email`, `gender`, `name`)
VALUES ('john@mail.com', 'MALE', 'John'),
		('mary@mail.com', 'FEMALE', 'Mary'),
		('susan@mail.com', 'FEMALE', 'Susan');
