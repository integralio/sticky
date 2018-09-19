package io.integral.sticky;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/sticky")
public class StickyController {

    private final StickyRepository repository;

    public StickyController(StickyRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Sticky> getStickies() {
        return repository.findAll();
    }

    @PostMapping
    @Transactional
    public ResponseEntity saveSticky(@RequestBody String json) {

        Sticky sticky;

        try {
            sticky = Sticky.createFromJson(json);
        } catch (IOException ex) {
            return ResponseEntity.badRequest().build();
        }

        repository.save(sticky);

        return ResponseEntity.ok().build();
    }

}
