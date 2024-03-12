package com.miu.pmtbackendapi.service.commonadpater;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Adapter {
    private final ModelMapper modelMapper;

    public <T, U> U convertObject(T object, Class<U> targetClass) {
        return modelMapper.map(object, targetClass);
    }

}
