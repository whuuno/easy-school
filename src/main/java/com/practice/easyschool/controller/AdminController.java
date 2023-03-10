package com.practice.easyschool.controller;

import com.practice.easyschool.model.Courses;
import com.practice.easyschool.model.Person;
import com.practice.easyschool.model.SchoolClass;
import com.practice.easyschool.repository.CoursesRepository;
import com.practice.easyschool.repository.PersonRepository;
import com.practice.easyschool.repository.SchoolClassRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    SchoolClassRepository schoolClassRepository;

    @Autowired
    CoursesRepository coursesRepository;

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model){
        List<SchoolClass> schoolClasses = schoolClassRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("schoolClass", new SchoolClass());
        modelAndView.addObject("schoolClasses", schoolClasses);
        return modelAndView;
    }

    @RequestMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @Valid @ModelAttribute("schoolClass") SchoolClass schoolClass){
        schoolClassRepository.save(schoolClass);
        ModelAndView modelAndView =new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @RequestMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession session,
                                        @RequestParam(value = "error", required = false) String error){
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("students.html");
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(classId);
        modelAndView.addObject("schoolClass", schoolClass.get());
        modelAndView.addObject("person", new Person());
        session.setAttribute("schoolClass", schoolClass.get());
        if(error != null){
            errorMessage = "Invalid Email Entered!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @GetMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id ){
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(id);
        for(Person person : schoolClass.get().getPersons()){
            person.setSchoolClass(null);
            personRepository.save(person);
        }
        schoolClassRepository.deleteById(id);
        ModelAndView modelAndView =new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        SchoolClass schoolClass = (SchoolClass) session.getAttribute("schoolClass");
        person = personRepository.readByEmail(person.getEmail());
        if(person == null || person.getPersonId()<0){
            modelAndView.setViewName("redirect:/admin/displayStudents?classId="+schoolClass.getClassId()+"&error=true");
            return modelAndView;
        }
        person.setSchoolClass(schoolClass);
        schoolClass.getPersons().add(person);
        schoolClassRepository.save(schoolClass);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId="+schoolClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam("personId") int personId, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        SchoolClass schoolClass = (SchoolClass) session.getAttribute("schoolClass");
        Optional<Person> person = personRepository.findById(personId);
        if(person.get() == null || person.get().getPersonId()<0){
            modelAndView.setViewName("redirect:/admin/displayStudents?classId="+schoolClass.getClassId()+"&error=true");
            return modelAndView;
        }
        schoolClass.getPersons().remove(person.get());
        schoolClassRepository.save(schoolClass);
        session.setAttribute("schoolClass", schoolClass);
        person.get().setSchoolClass(null);
        personRepository.save(person.get());
        modelAndView.setViewName("redirect:/admin/displayStudents?classId="+schoolClass.getClassId());
        return modelAndView;
    }

    @RequestMapping("/displayCourses")
    public ModelAndView displayCourses(Model model){
        ModelAndView modelAndView = new ModelAndView("courses_secure.html");
        modelAndView.addObject("course", new Courses());
        //List<Courses> courses = coursesRepository.findByOrderByNameDesc();
        List<Courses> courses = coursesRepository.findAll(Sort.by("name").ascending());
        modelAndView.addObject("courses", courses);
        return modelAndView;
    }

    @RequestMapping("/addNewCourse")
    public ModelAndView addNewCourse(Model model, @ModelAttribute("course") Courses course){
        coursesRepository.save(course);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayCourses");
        return modelAndView;
    }

    @RequestMapping("/viewStudents")
    public ModelAndView viewStudents(Model model, @RequestParam("id") Integer id, HttpSession session,
                                     @RequestParam(value = "error", required = false) String error){
        ModelAndView modelAndView = new ModelAndView("course_student.html");
        Optional<Courses> courses = coursesRepository.findById(id);
        session.setAttribute("courses", courses.get());
        modelAndView.addObject("courses", courses.get());
        modelAndView.addObject("person", new Person());
        return modelAndView;
    }

    @RequestMapping("/addStudentToCourse")
    public ModelAndView addStudentToCourse(Model model, @ModelAttribute("person") Person person, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Courses courses = (Courses) session.getAttribute("courses");
        person = personRepository.readByEmail(person.getEmail());
        if(person==null || person.getPersonId()<0){
            modelAndView.setViewName("redirect:/admin/viewStudents?id="+courses.getCourseId()+"&error=true");
            return modelAndView;
        }
        person.getCourses().add(courses);
        courses.getPersons().add(person);
        personRepository.save(person);
        session.setAttribute("courses", courses);
        modelAndView.setViewName("redirect:/admin/viewStudents?id="+courses.getCourseId());
        return modelAndView;
    }

    @RequestMapping("/deleteStudentFromCourse")
    public ModelAndView deleteStudentFromCourse(Model model, @RequestParam("personId") Integer personId, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Courses courses = (Courses) session.getAttribute("courses");
        Optional<Person> person = personRepository.findById(personId);
        courses.getPersons().remove(person.get());
        person.get().getCourses().remove(courses);
        personRepository.save(person.get());
        modelAndView.setViewName("redirect:/admin/viewStudents?id="+courses.getCourseId());
        return modelAndView;
    }
}
