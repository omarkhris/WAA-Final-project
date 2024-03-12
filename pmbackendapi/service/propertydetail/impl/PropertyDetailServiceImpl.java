package com.miu.pmtbackendapi.service.propertydetail.impl;

import com.miu.pmtbackendapi.domain.address.Address;
import com.miu.pmtbackendapi.domain.propertydetail.PropertyDetail;
import com.miu.pmtbackendapi.dtos.PropertyDetailDTO;
import com.miu.pmtbackendapi.exception.customexception.ItemNotFoundException;
import com.miu.pmtbackendapi.repo.address.AddressRepo;
import com.miu.pmtbackendapi.repo.propertydetail.PropertyDetailRepository;
import com.miu.pmtbackendapi.service.propertydetail.PropertyDetailService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyDetailServiceImpl implements PropertyDetailService {

    private final ModelMapper modelMapper;

    private final PropertyDetailRepository propertyDetailRepo;

    private final AddressRepo addressRepo;


    @Override
    public List<PropertyDetailDTO> getAllPropertyDetails() {
        Iterable<PropertyDetail> propertyDetails = propertyDetailRepo.findAll();
        List<PropertyDetailDTO> propertyDetailDTOS = new ArrayList<>();
        for (PropertyDetail propertyDetail : propertyDetails) {
            PropertyDetailDTO propertyDto = modelMapper.map(propertyDetail, PropertyDetailDTO.class);
            propertyDetailDTOS.add(propertyDto);
        }
        return propertyDetailDTOS;
    }

    @Override
    public PropertyDetailDTO getPropertyDetailById(Long id) throws ItemNotFoundException {
        Optional<PropertyDetail> find = propertyDetailRepo.findById(id);
        if (find.isPresent()) {
            PropertyDetail propertyDetail = find.get();
            PropertyDetailDTO propertyDetailDTO = modelMapper.map(propertyDetail, PropertyDetailDTO.class);
            return propertyDetailDTO;
        } else
            throw new ItemNotFoundException("PropertyDetail Not Found !!!");
    }

    @Override
    public PropertyDetailDTO createPropertyDetail(PropertyDetail propertyDetail) {
        Address address = propertyDetail.getAddress();
        addressRepo.save(address);
        propertyDetail.setAddress(address);
        PropertyDetail newPropertyDetail = propertyDetailRepo.save(propertyDetail);
        PropertyDetailDTO propertyDetailDTO = modelMapper.map(newPropertyDetail, PropertyDetailDTO.class);
        return propertyDetailDTO;
    }

    @Override
    public Boolean deletePropertyDetailById(Long id) {
        Optional<PropertyDetail> find = propertyDetailRepo.findById(id);
        if (find.isPresent()) {
            propertyDetailRepo.delete(find.get());
            return true;
        } else
            return false;
    }

    @Override
    public PropertyDetailDTO updatePropertyDetail(Long id, PropertyDetail propertyDetail) {
        Optional<PropertyDetail> find = propertyDetailRepo.findById(id);
        if (find.isPresent()) {
            PropertyDetail found = find.get();
            found.setPropertyPrice(propertyDetail.getPropertyPrice());
            found.setPropertyType(propertyDetail.getPropertyType());
            found.setAddress(propertyDetail.getAddress());
            found.setHasTenant(propertyDetail.getHasTenant());
            found.setHomeCondition(propertyDetail.getHomeCondition());
            found.setLotSize(propertyDetail.getLotSize());
            found.setHomeSize(propertyDetail.getHomeSize());
            found.setFullBathroomNum(propertyDetail.getFullBathroomNum());
            found.setYearBuild(propertyDetail.getYearBuild());
            found.setPartialBathroomNum(propertyDetail.getPartialBathroomNum());
            found.setRoomNum(propertyDetail.getRoomNum());
            found.setMortgageBalance(propertyDetail.getMortgageBalance());
            propertyDetailRepo.save(found);
            PropertyDetailDTO propertyDetailDTO = modelMapper.map(found, PropertyDetailDTO.class);
            return propertyDetailDTO;
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found");
    }
}

