use dalton;

select * from student;
select * from class;
select * from classroom;
select * from course;
select * from subject;
select * from major;
select * from instructor;
select * from department;

alter table class drop class_num;

-- list of classes for a student
select class.crn, course.course_name, class.day, class.time, classroom.building_name, classroom.room_num
from course
inner join class on course.id = class.course_id
inner join student_class on student_class.class_id = class.id
inner join student on student.id = student_class.student_id
inner join classroom on classroom.id = class.classroom_id
where student.id = 1;

-- list of classes and instructors
select course.id as course_id, course.course_name, class.crn, class.day, class.time, instructor.id, instructor.instructor_name
from course
inner join class on course.id = class.course_id
inner join instructor on instructor.id = class.instructor_id
where instructor.id=5
order by instructor_name;

-- list of classes and students in each class
select course.course_name, student_class.class_id as class_id, class.crn, instructor.instructor_name, student.id as student_id, student.student_name
from course
inner join class on course.id = class.course_id
inner join instructor on instructor.id = class.instructor_id
inner join student_class on student_class.class_id = class.id
inner join student on student.id = student_class.student_id
where instructor.id=1
order by crn;

-- list of classes not from the current semester with students and their grades
select course.course_name, student_class.class_id as class_id, class.crn, instructor.instructor_name, student.id as student_id, student.student_name, grade.grade
from course
inner join class on course.id = class.course_id
inner join instructor on instructor.id = class.instructor_id
inner join student_class on class.id = student_class.class_id
inner join student on student.id = student_class.student_id
inner join grade on student_class.student_id = grade.student_id
where instructor.id=1 
order by student_class.class_id, student.id;





