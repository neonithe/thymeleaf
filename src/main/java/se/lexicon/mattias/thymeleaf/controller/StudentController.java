package se.lexicon.mattias.thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import se.lexicon.mattias.thymeleaf.model.Student;
import se.lexicon.mattias.thymeleaf.service.StudentDAO;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller //, method = {RequestMethod.GET, RequestMethod.PUT}
@RequestMapping(value = "/student", method = RequestMethod.GET)
public class StudentController {

    @Autowired
    StudentDAO dao;


    @PostConstruct
    public void init() {

        dao.save(new Student("Mattias","ma@no.com"));
        dao.save(new Student("Johan","ja@no.com"));
        dao.save(new Student("Sofia","ss@no.com"));
        dao.save(new Student("Hanna","hs@no.com"));
        dao.save(new Student("Martin","ma@no.com"));
        dao.save(new Student("Jonas","ja@no.com"));
        dao.save(new Student("Micke","ss@no.com"));
        dao.save(new Student("Linn","hs@no.com"));
        dao.save(new Student("Niklas","ma@no.com"));
        dao.save(new Student("Jocke","ja@no.com"));
        dao.save(new Student("Ewa","ss@no.com"));
        dao.save(new Student("Birger","hs@no.com"));
        dao.save(new Student("Kenny","ma@no.com"));
        dao.save(new Student("Anna","ja@no.com"));
        dao.save(new Student("Patricia","ss@no.com"));
        dao.save(new Student("Stefan","hs@no.com"));

    }
    @GetMapping()
    public String getAll(Model model){

        List<Student> students = dao.findAll();
        model.addAttribute("students", students);

        return "student_view.html";
    }

    @GetMapping("/test")
    public String test(){
        return "/studentdir/student_form.html";
    }

    @GetMapping("/form")
    public String form(Model model){

        //Create model attribute to bind form data
        Student student = new Student();
        model.addAttribute("student", student);

        return "/studentdir/student_form.html";
    }

    /** Functions Save, delete, update  ( Save with error handling )**/

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("student") Student student, BindingResult errors, Model model){

        if(errors.hasErrors()) {
            return "/studentdir/student_form.html";
        }
        // Save the student
        dao.save(student);
        // Redirect
        return "redirect:/student";
    }

    @GetMapping(value="/delete")
    public String delete(@RequestParam("studentId") int id){

        // Remove the Student
        dao.deleteById(id);
        // Redirect
        return "redirect:/redirect";
    }

    @PostMapping(value="/update")
    public String update(@RequestParam("studentId") int id, Model model){

        // Get student > DB
        Student student = getObject(id);

        // Send the information to the form
        model.addAttribute("student", student);

        // Open Form
        return "/studentdir/student_form.html";
    }




    /** Search for names / email **/

    @GetMapping("/search")
    public String search(@RequestParam("searchvalue") String name, Model model) {

        List<Student> list = null;

        list = dao.findAllByName(name);

        if(dao.findAllByName(name).isEmpty()) {
            list = dao.findAllByEmail(name);
        }

        model.addAttribute("resultvalue", list);

        return "search.html";
    }




    /** Sorting the list of students **/

    @GetMapping("/sortNameAsc")
    public String sortNameAsc(Model model) {

        // Get the list from DB and sort them
        List<Student> list = dao.findAllByOrderByNameAsc();

        // Add to the spring model
        model.addAttribute("students", list);

        return "student_view.html";
    }

    @GetMapping("/sortNameDesc")
    public String sortNameDesc(Model model) {

        // Get the list from DB and sort them
        List<Student> list = dao.findAllByOrderByNameDesc();

        // Add to the spring model
        model.addAttribute("students", list);

        return "student_view.html";
    }

    @GetMapping("/sortId")
    public String sortId(Model model) {

        // Get the list from DB and sort them
        List<Student> list = dao.findAllByOrderByIdAsc();

        // Add to the spring model
        model.addAttribute("students", list);

        return "student_view.html";
    }

    /** convert from Optional to object **/
    public Student getObject(int id){

        Optional<Student> tempList = dao.findById(id);

        Student student = null;

        if(tempList.isPresent()) {
            student =tempList.get();
        }
        return student;
    }


}
