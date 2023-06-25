package ua.goit.vic.note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("/note")
@RequiredArgsConstructor
@Controller
public class NoteController {
    private final NoteService ns;

    @GetMapping("/create")
    public String create(){
        return ("note/create");
    }

    @PostMapping("/create")
    public RedirectView createNote(@ModelAttribute Note note){
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/note/list");
        ns.add(note);
        return redirectView;
    }

    @GetMapping("/list")
    public ModelAndView getNotes(){
        ModelAndView modelAndView = new ModelAndView("note/note");
        modelAndView.addObject("notes", ns.listAll());
        return modelAndView;
    }

    @GetMapping("/update")
    public String edit(Model model, @RequestParam long id){
        Note note = ns.getById(id);
        model.addAttribute("note", note);
        return ("note/update");
    }

    @PostMapping("/update")
    public RedirectView editNote(@ModelAttribute Note note){
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/note/list");
        ns.update(note);
        return redirectView;
    }

    @GetMapping("/delete")
    public RedirectView delete(@RequestParam long id){
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/note/list");
        ns.deleteById(id);
        return  redirectView;
    }
}
