package com.webapp.io_seatimecount.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactsController {
    @GetMapping("/contacts")
    public String contacts(Model model) {
        model.addAttribute("Success", "\u2063");

        return "contacts";
    }

    @PostMapping("/contacts")
    public String getInfoFromForm(Model model) {
            model.addAttribute("Success", "We will contact you soon!");
        return "contacts";
    }
}
