package com.exampl.traveler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonController {
    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }



    @GetMapping("/users")
    public String getPersons(Model model) {
        model.addAttribute("persons", personRepository.findAll());
        return "Persons";
    }
}
