package io.integral.sticky;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sticky")
public class StickyController {

    @GetMapping
    public void index() {


    }

}
