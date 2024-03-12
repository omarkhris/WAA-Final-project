package com.miu.pmtbackendapi.service.logging.impl;

import com.miu.pmtbackendapi.domain.logging.ExceptionEntity;
import com.miu.pmtbackendapi.repo.logging.ExceptionEntityRepo;
import com.miu.pmtbackendapi.service.logging.ExceptionEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExceptionEntityServiceImpl implements ExceptionEntityService {

    private final ExceptionEntityRepo exceptionEntityRepo;

    @Override
    public void saveExceptionEntity(ExceptionEntity exception) {
        exceptionEntityRepo.save(exception);
    }
}
