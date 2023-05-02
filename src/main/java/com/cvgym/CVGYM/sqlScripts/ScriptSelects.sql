select * from course c 
inner join gym_courses gc on gc.courses_id = c.id
inner join gym g on g.id = gc.gyms_id
where g.city = 'Valencia';