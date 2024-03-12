package com.miu.pmtbackendapi.resource;

import com.miu.pmtbackendapi.domain.Hello;
import com.miu.pmtbackendapi.exception.customexception.ItemNotFoundException;
import com.miu.pmtbackendapi.service.impl.HelloServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
@AllArgsConstructor
public class HelloController {

    private final HelloServiceImpl helloService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getHello(@PathVariable(value = "id") Long id) throws ItemNotFoundException {
        return ResponseEntity.ok(helloService.getHello(id));
    }

    @PostMapping("/")
//    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<?> saveHello(@RequestBody Hello hello) {
        return ResponseEntity.ok(helloService.saveHello(hello));
    }
}
