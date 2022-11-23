package com.strange.collaboration.subject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    public List<Subject> findAllSubjects() throws Exception {
        return subjectRepository.findAll();
    }

    public Optional<Subject> findSubjectDetailById(Long id) throws Exception {
        Optional<Subject> subject=subjectRepository.findById(id);
        log.info(subject.toString());

        return subject;
    }

    public void createSubject(Subject subject) throws  Exception{
        subjectRepository.save(subject);
    }

    public void updateSubject(Subject subject) throws Exception{
        subjectRepository.save(subject);
    }

    public void deleteSubjectById(Long id) throws Exception{
        subjectRepository.deleteById(id);
    }
}
