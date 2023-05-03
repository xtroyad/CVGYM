select * from gym;
select * from course;
select * from gym_courses;
select * from manager;
select * from question;

select * from course c
inner join gym_courses gc on gc.courses_id = c.id
inner join gym g on g.id = gc.gyms_id
where g.city = 'Murcia';