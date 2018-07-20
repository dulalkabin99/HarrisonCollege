package com.example.dalton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

//@Controller
public class jyController {

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ClassRepository classRepository;

//    @RequestMapping("/")
//    public String index()
//    {
//        return "courselist";
//    }
//
//    @GetMapping("/addCourse")
//    public String addCourse(Model model)
//    {
//        model.addAttribute("course", new Course());
//        //model.addAttribute("majors", majorRepository.findAll());
//        return "courselist";
//    }

//    @RequestMapping("/")
//    public String index()
//    {
//        return "coursedetail";
//    }

//    @RequestMapping("/")
//    public String index()
//    {
//        return "classlist";
//    }

//    @RequestMapping("/")
//    public String index(HttpServletRequest request, Model model)
//    {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String role = request.getParameter("role");
//        if (instructorRepository.countByUserNameAndPassword(username, password) > 0) {
//            //model.addAttribute("courses", courseRepository.findAll());
//            model.addAttribute("instructor", instructorRepository.findByUserNameContainingIgnoreCaseAndPassword(username, password).iterator().next());
//
//        }
//            return "courselist";
//    }

    @RequestMapping("/")
    public String index(Model model)
    {
        model.addAttribute("courses", courseRepository.findAll());
        return "courselist";
    }

    //@{/detailCourse/{

    @RequestMapping("/detailCourse/{id}")
    public String showDetail(@PathVariable("id") long id, Model model)
    {
        Course course = courseRepository.findById(id).get();
        model.addAttribute("course", course);

        return "coursedetail";
    }

    @RequestMapping("/detail")
    public String detail(Model model)
    {
        model.addAttribute("courses", courseRepository.findAll());
        return "coursedetail";
    }
//
//    @GetMapping("/addcourse")
//    public String newCourse(Model model)
//    {
//        model.addAttribute("course", new Course());
//        return "courselist";
//    }
//
//    @PostMapping("/addcourse")
//    public String processCourse(@ModelAttribute Course course, Model model)
//    {
//        courseRepository.save(course);
//        return "redirect:/";
//    }
//
//
//    @RequestMapping("/detail/{id}")
//    public String showDetail(@PathVariable("id") long id, Model model)
//    {
//        Course course = courseRepository.findById(id).get();
//
//        model.addAttribute("course", course);
//
//        return "show";
//    }

}
