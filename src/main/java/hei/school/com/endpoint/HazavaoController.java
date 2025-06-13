package hei.school.com.endpoint;

import hei.school.com.service.ChatGptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hazavao")
public class HazavaoController {
    private final ChatGptService chatGptService;

    public HazavaoController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @GetMapping
    public String hazavao(@RequestParam String teny) {
        return chatGptService.getMalagasyDefinition(teny);
    }
}
