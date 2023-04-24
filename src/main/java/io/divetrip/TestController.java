package io.divetrip;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {
    private final LibraryService libraryService;

    @GetMapping("/test")
    public String test() {
        libraryService.run();
        return "OK";
    }


}
