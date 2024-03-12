package com.miu.pmtbackendapi.service.impl;

import com.miu.pmtbackendapi.domain.Hello;
import com.miu.pmtbackendapi.exception.customexception.ItemNotFoundException;
import com.miu.pmtbackendapi.repo.HelloRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HelloServiceImpl {
    private final HelloRepo helloRepo;

    public Hello getHello(Long id) throws ItemNotFoundException {
        Optional<Hello> he = helloRepo.findById(id);
        if (he.isPresent()) {
            return he.get();
        }
        throw new ItemNotFoundException("Hello Not found");
    }

    public Hello saveHello(Hello hello) {
        return helloRepo.save(hello);
    }
}
