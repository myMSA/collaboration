package com.strange.cooperation.subject;

import com.strange.cooperation.sample.Sample;
import com.strange.cooperation.sample.SampleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    SampleService sampleService;

    @Autowired
    SubjectService subjectService;

    /**
     * Method 0-1 ; getSubjectsIndex of Controller whose response is html with data
     * Method 0-2 ; get Detail hypertext of one Subject
     */

    @GetMapping("/index")
    @Operation(summary = "Get Subjects index.html with model")
    public String getSubjectsIndex(Model model) throws Exception {

        model.addAttribute("subjects",subjectService.findAllSubjects());

        return "subjects/index";
    }

    /**
     * Method 1 ; getSubjectsHome of Controller whose response is html which has javascript that call api
     * Method 2 ; getSubjects that api rest controller whose response is json
     * Method (AS-IS) ; getSubjectDetail Controller whose response is html with data
     * Method 3.0(TO-BE) ; getSubjectDetail Controller that api rest controller whose response is json
     */

    @GetMapping("/home")
    @Operation(summary = "Get Subjects Home.html that send http msg to get all subject")
    public String getSubjectsHome() throws Exception {

        return "subjects/home";
    }

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @Operation(summary = "Get All Subject")
    public List<Subject> getSubjects() throws Exception {

        return subjectService.findAllSubjects();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Subject Detail")
    public String getSubjectDetail(Model model, @PathVariable Long id) throws Exception {

        subjectService.findSubjectDetailById(id).ifPresent(subject -> model.addAttribute("subject", subject));

        return "subjects/subjectDetail";
    }

    /**
     * Method 1 ; get Subject write
     * Method 2 ; POST
     * Method 3 ; Patch
     */

    @GetMapping("/write")
    @Operation(summary = "Get subject write form for POST subject")
    public String getWriteForm(Model model,Subject subject, @RequestParam(value = "id" , required = false) Long id) throws Exception {

        if(id != null){
            subjectService.findSubjectDetailById(id).ifPresent(subjectValue -> model.addAttribute("subject", subjectValue));
        } else {
            id = -1l;
        }

        return "subjects/write";
    }

    @PostMapping("/write")
    @Operation(summary = "Post Subject")
    public String postSubject(@ModelAttribute Subject subject) throws Exception {
        log.info(subject.toString());
        subjectService.createSubject(subject);

        return "redirect:/subjects/index";
    }

    @PatchMapping("/write")
    @Operation(summary = "Patch SubjectDetail")
    public String updateSubjectDetail(@ModelAttribute Subject subject,@RequestParam(value = "id" , required = true) Long id) throws Exception {
        log.info(subject.toString());
        subjectService.updateSubject(subject);

        return "redirect:/subjects/index";
    }

    /**
     * Method 1 ; Delete
     */

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Subject")
    public void deleteSubject(@PathVariable Long id) throws Exception {
        log.info(id.toString());
        subjectService.deleteSubjectById(id);

    }

}
