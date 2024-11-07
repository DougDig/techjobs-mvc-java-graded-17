package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Objects;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

//

    @PostMapping("results")
    public String displaySearchResults( Model model, @RequestParam("searchType") String searchType, @RequestParam("searchTerm") String searchTerm){

        ArrayList<Job> jobs = new ArrayList<>();

        if (Objects.equals(searchType, "all")) {
            if ((Objects.equals(searchTerm, "all") || searchTerm.isEmpty())) {
            jobs = new ArrayList<>(JobData.findAll());
            } else {jobs = JobData.findByColumnAndValue("all",searchTerm);}
        } else if (Objects.equals(searchType, "name")) {
            jobs = JobData.findByColumnAndValue("name", searchTerm);
        } else if (Objects.equals(searchType, "employer")) {
            jobs = JobData.findByColumnAndValue("employer", searchTerm);
        } else if (Objects.equals(searchType, "location")) {
            jobs = JobData.findByColumnAndValue("location", searchTerm);
        } else if (Objects.equals(searchType, "positionType")) {
            jobs = JobData.findByColumnAndValue("positionType", searchTerm);
        } else if (Objects.equals(searchType, "coreCompetency") | (Objects.equals(searchType, "skill"))) {
            jobs = JobData.findByColumnAndValue("coreCompetency", searchTerm);
        }

            model.addAttribute("searchType", searchType);
            model.addAttribute("jobs", jobs);
            model.addAttribute("columns", columnChoices);

        return "search";
    }

}

