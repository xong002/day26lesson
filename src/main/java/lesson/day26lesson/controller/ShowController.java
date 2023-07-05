package lesson.day26lesson.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lesson.day26lesson.model.Show;
import lesson.day26lesson.repository.ShowsRepository;

@Controller
@RequestMapping("/")
public class ShowController {

    @Autowired
    ShowsRepository showRepo;

    @GetMapping
    public String homePage(Model model) {
        Show show = new Show();
        model.addAttribute("show", show);
        return "search";
    }

    @GetMapping(path="/api/show", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody //Can have any response (html/json/etc.). If dont put, it will assume return a view (based on return string). Only for @Controller
    public ResponseEntity<String> getShowAsJson(@RequestParam String showName){
        List<Document> result = showRepo.findShowsByName(showName);
        Document doc = result.get(0);
        return ResponseEntity.ok().body(doc.toJson());
    }

    @GetMapping("/search")
    public String searchResult(@ModelAttribute Show show, Model model) {
        List<Document> resultList = showRepo.findShowsByName(show.getName());
        List<Show> showList = new ArrayList<>();
        if (resultList.isEmpty()) {
            return "notfound";
        }
        for (Document d : resultList) {
            Show s = new Show();
            s.setId(d.getInteger("id"));
            s.setName(d.getString("name"));
            s.setLanguage(d.getString("language"));
            s.setRuntime(d.getInteger("runtime"));
            try {
                s.setAverageRating(d.get("rating", Document.class).getDouble("average"));
            } catch (ClassCastException ex) {
                s.setAverageRating(Double.valueOf(d.get("rating", Document.class).getInteger("average")));
            }
            showList.add(s);
        }
        model.addAttribute("showList", showList);
        return "result";
    }
}
