package com.nw.spring.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nw.spring.model.Person;
import com.nw.spring.repo.PersonRepository;

@Controller
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping("/signup")
	public String signUpPerson(Person person) {
		return "add-person";
	}
	
	@GetMapping("/list")
	public String allPerson(Model model) {
		model.addAttribute("persons", personRepository.findAll());
		return "index";
	}
	
	@PostMapping("/add")
	public String addPerson(Model model, @Valid Person person, BindingResult result) {
		if (result.hasErrors()) {
			return "add-person";
		}
		personRepository.save(person);
		return "redirect:/list";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePerson(Model model, @PathVariable long id) {
		Person p = personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + " Invalid person."));
		personRepository.delete(p);
		model.addAttribute("persons", personRepository.findAll());
		return "index";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(Model model, @PathVariable long id) {
		Person p = personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + " Invalid person."));
		model.addAttribute("person", p);
		return "update-person";
	}
	
	@PostMapping("/update/{id}")
	public String processUpdatePerson(@PathVariable long id, @Valid Person person, BindingResult result, Model model) {
		if (result.hasErrors()) {
			person.setId(id);
			return "update-person";
		}
		personRepository.save(person);
		model.addAttribute("persons", personRepository.findAll());
		return "index";
	}

}
