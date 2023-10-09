package org.olexec.controller;

import org.olexec.service.ExecuteStringSourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RunCodeController {
    private final Logger logger = LoggerFactory.getLogger(RunCodeController.class);

    private final ExecuteStringSourceService executeStringSourceService;

    @Autowired
    RunCodeController(ExecuteStringSourceService executeStringSourceService) {
        this.executeStringSourceService = executeStringSourceService;
    }

    private static final String DEFAULT_SOURCE = """
             public class Run {
                 public static void main(String[] args) {
                     System.out.println("Hi OnlineExecutor!");
                 }
             }
            """;

    @GetMapping(path = {"/"})
    public String entry(Model model) {
        model.addAttribute("lastSource", DEFAULT_SOURCE);
        return "ide";
    }

    @PostMapping(path = {"/run"})
    public String runCode(@RequestParam("source") String source,
                          @RequestParam("systemIn") String systemIn, Model model) {
        String runResult = executeStringSourceService.execute(source, systemIn);
        runResult = runResult.replaceAll(System.lineSeparator(), "<br/>"); // 处理html中换行的问题

        model.addAttribute("lastSource", source);
        model.addAttribute("lastSystemIn", systemIn);
        model.addAttribute("runResult", runResult);
        return "ide";
    }
}
