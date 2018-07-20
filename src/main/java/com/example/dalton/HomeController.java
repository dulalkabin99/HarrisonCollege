package com.example.dalton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.boot.context.properties.PropertyMapper.get;

@Controller
public class HomeController {

  @Autowired
  ClassRepository classRepository;

  @Autowired
  ClassroomRepository classroomRepository;

  @Autowired
  CourseRepository courseRepository;

  @Autowired
  DepartmentRepository departmentRepository;

  @Autowired
  GradeRepository gradeRepository;

  @Autowired
  InstructorRepository instructorRepository;

  @Autowired
  MajorRepository majorRepository;

  @Autowired
  StudentRepository studentRepository;

  @Autowired
  SubjectRepository subjectRepository;
  @Autowired
  AdvisorRepository advisorRepository;

  @RequestMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/addStudent")
  public String signup(Model model) {

    model.addAttribute("majors", majorRepository.findAll());
    model.addAttribute("adv", advisorRepository.findAll());
    model.addAttribute("student", new Student());
    return "signup";
  }


  @GetMapping("/addroom")
  public String addRoom(Model model) {
    model.addAttribute("classroom", new Classroom());
    return "addclassroom";
  }

  @GetMapping("/adddepartment")
  public String addDepartment(Model model) {
    model.addAttribute("department", new Department());
    return "adddepartment";
  }


  @GetMapping("/addcourse")
  public String addcourse(Model model) {
    model.addAttribute("subject", subjectRepository.findAll());
    model.addAttribute("major", majorRepository.findAll());
    model.addAttribute("course", new Course());
    return "addcourse";
  }

  @GetMapping("/addclass")
  public String addclass(Model model) {
    model.addAttribute("course", courseRepository.findAll());
    model.addAttribute("ins", instructorRepository.findAll());
    model.addAttribute("room", classroomRepository.findAll());
    model.addAttribute("aclass", new Class());
    return "addclass";
  }


  @GetMapping("/addsubject")
  public String addsubject(Model model) {
    model.addAttribute("subject", new Subject());
    return "addsubject";
  }

  @GetMapping("/addmajor")
  public String addMajor(Model model) {
    model.addAttribute("options", departmentRepository.findAll());
    model.addAttribute("major", new Major());
    return "addmajor";
  }



  @GetMapping("/addins")
  public String addInstructor(Model model) {
    model.addAttribute("options", departmentRepository.findAll());
    model.addAttribute("ins", new Instructor());
    return "addinstructor";
  }

  @GetMapping("/addadvisor")
  public String addAdvisor(Model model) {
    model.addAttribute("advisor", new Advisor());
    return "addadvisor";
  }

  @GetMapping("/links")
  public String getLinks() {
    return "links";
  }

  @PostMapping("/addStudent")
  public String processSignUp(@Valid @ModelAttribute Student student, BindingResult result) {
    if (result.hasErrors()) {
      return "signup";
    }
    //System.out.println("######"+student.getId());
    studentRepository.save(student);
    student.setStudentNum(student.getId());
    //System.out.println("######"+student.getId());
    studentRepository.save(student);
    return "redirect:/";
  }


  @PostMapping("/postroom")
  public String processroom(@Valid @ModelAttribute Classroom classroom, BindingResult result) {
    if (result.hasErrors()) {
      return "addroom";
    }
    //System.out.println("######"+student.getId());
    classroomRepository.save(classroom);
    return "success";
  }


  @PostMapping("/postclass")
  public String processClass(@Valid @ModelAttribute Class aclass, BindingResult result) {
    if (result.hasErrors()) {
      return "addclass";
    }

    classRepository.save(aclass);
    return "success";
  }


  @PostMapping("/postins")
  public String postIns(@Valid @ModelAttribute Instructor instructor, BindingResult result) {
    if (result.hasErrors()) {
      return "addinstructor";
    }
    instructorRepository.save(instructor);
    return "success";
  }

  @PostMapping("/postadvisor")
  public String postAdvisor(@Valid @ModelAttribute Advisor advisor, BindingResult result) {
    if (result.hasErrors()) {
      return "addadvisor";
    }
    advisorRepository.save(advisor);
    return "success";
  }


  @PostMapping("/postmajor")
  public String procesmajor(@Valid @ModelAttribute Major major, BindingResult result) {
    if (result.hasErrors()) {
      return "addmajor";
    }
    //System.out.println("######"+student.getId());
    majorRepository.save(major);
    return "success";
  }

  @PostMapping("/postsubject")
  public String processsubject(@Valid @ModelAttribute Subject subject, BindingResult result) {
    if (result.hasErrors()) {
      return "addsubject";
    }
    subjectRepository.save(subject);
    return "success";
  }


  @PostMapping("/postdepartment")
  public String processdepartment(@Valid @ModelAttribute Department department, BindingResult result) {
    if (result.hasErrors()) {
      return "adddepartment";
    }
    //System.out.println("######"+student.getId());
    departmentRepository.save(department);
    return "success";
  }

  @PostMapping("/postcourse")
  public String postCourse(@Valid @ModelAttribute Course course, BindingResult result) {
    if (result.hasErrors()) {
      return "addcourse";
    }
    courseRepository.save(course);
    return "success";
  }

  @GetMapping("/loginsystem")
  public String login(Model model) {
    return "login";
  }

  @PostMapping("/loginsystem")
  public String processLogin(HttpServletRequest request, Model model) {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String role = request.getParameter("role");
    if (role.equals("student")) {
      if (studentRepository.countByUserNameAndPassword(username, password) > 0) {
        Student student = studentRepository.findByUserNameContainingIgnoreCaseAndPassword(username, password).iterator().next();
        model.addAttribute("classes", classRepository.findAllByStudents(student));
        model.addAttribute("student", studentRepository.findByUserNameContainingIgnoreCaseAndPassword(username, password).iterator().next());
        return "studentMainPage";
      } else {
        return "login";
      }
    } else if (role.equals("instructor")) {
      if (instructorRepository.countByUserNameAndPassword(username, password) > 0) {
        model.addAttribute("instructor", instructorRepository.findAllByUserNameContainingIgnoreCaseAndPassword(username, password).iterator().next());
        Instructor i = instructorRepository.findFirstInstructorByUserName(username);
        long num = i.getId();
        model.addAttribute("classes", classRepository.findAllByInstructorId(num));
        return "instructorMainPage";
      } else {
        return "login";
      }
    } else if (role.equals("advisor")) {
      if (advisorRepository.countByUserNameAndPassword(username, password) > 0) {
        model.addAttribute("advisor", advisorRepository.findAllByUserNameContainingIgnoreCaseAndPassword(username, password).iterator().next());
        Advisor i = advisorRepository.findFirstInstructorByUserName(username);
        long num = i.getId();
        model.addAttribute("classes", classRepository.findAllByInstructorId(num));
        return "instructorMainPage";
      } else {
        return "login";
      }


    } else if (role.equals("admin")) {
      if (username.equals("admin") && password.equals("password")) {
        model.addAttribute("students", studentRepository.findAll());
        return "adminMainPage";
      } else {
        return "login";
      }
    } else
      return "login";
  }

  @RequestMapping("/updaterole/{id}")
  public String ChangeRole(@PathVariable("id") long id, Model model) {
    Student student = studentRepository.findById(id).get();
    Instructor instructor = new Instructor();
    instructor.setInstructorName(student.getStudentName());
    instructor.setUserName(student.getUserName());
    instructor.setPassword(student.getPassword());
    studentRepository.deleteById(id);
    //System.out.println("$$$$$$$$$$"+instructor.getInstructorName());
    model.addAttribute("instructor", instructor);
    model.addAttribute("departments", departmentRepository.findAll());
    return "instructorform";
  }

  @RequestMapping("/addInstructor")
  public String processInstructor(@ModelAttribute Instructor instructor, Model model) {
    instructorRepository.save(instructor);
    //System.out.println("$$$$$$$$$$"+instructor.getId());
    instructor.setEmployeeNum(instructor.getId());
    instructorRepository.save(instructor);
    model.addAttribute("students", studentRepository.findAll());
    return "adminMainPage";
  }

  @RequestMapping("/showInstructorlist")
  public String showInstructorList(Model model) {
    model.addAttribute("ins", instructorRepository.findAll());
    return "instructorlist";
  }

  @RequestMapping("@{/dropClass/{id} ")
  public String dropClass(@PathVariable("id") long id) {
return "studentMainPage";
  }

  @RequestMapping("/showschedule/{id}")
  public String shSchedule(@PathVariable("id") long id, Model model) {
   model.addAttribute("st", studentRepository.findById(id).get());
    return "schedule";
  }

  @RequestMapping("/viewclass")
  public String viewClass(Model model) {
    model.addAttribute("classes", classRepository.findAll());
    return "classlist";
  }
}
