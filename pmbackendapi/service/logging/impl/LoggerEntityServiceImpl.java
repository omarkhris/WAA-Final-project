package com.miu.pmtbackendapi.service.logging.impl;

import com.miu.pmtbackendapi.repo.logging.LoggerEntityRepo;
import com.miu.pmtbackendapi.domain.logging.LoggerEntity;
import com.miu.pmtbackendapi.service.logging.LoggerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggerEntityServiceImpl implements LoggerEntityService {


    private final LoggerEntityRepo loggerEntityRepo;

    @Override
    public void saveLoggerEntity(LoggerEntity loggerEntity) {
        loggerEntityRepo.save(loggerEntity);
    }
}
