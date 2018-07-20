package com.example.dalton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MelissaController {
    @Autowired
    ClassRepository classRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @RequestMapping("/instructor")
    public String getInstructorPage(Model model){

        return "instructor.html";
    }

    @RequestMapping("/schedule")
    public String getStudentSchedule(Model model){
        Student student = studentRepository.findByUserNameContainingIgnoreCaseAndPassword("WMelon","password").iterator().next();
        model.addAttribute("classes",classRepository.findAllByStudents(student));
        return "schedule";
    }
}
