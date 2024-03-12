package com.miu.pmtbackendapi.service.address.impl;

import com.miu.pmtbackendapi.domain.address.Address;
import com.miu.pmtbackendapi.dtos.AddressDTO;
import com.miu.pmtbackendapi.exception.customexception.ItemNotFoundException;
import com.miu.pmtbackendapi.repo.address.AddressRepo;
import com.miu.pmtbackendapi.service.address.AddressService;
import jakarta.transaction.Transactional;
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

public class AddressServiceImpl implements AddressService {

    private final AddressRepo addressRepo;

    private final ModelMapper modelMapper;
    @Override
    public List<AddressDTO> getAllAddresses() {
        Iterable<Address> addresses = addressRepo.findAll();
        List<AddressDTO> addressDTOS = new ArrayList<>();
        for(Address address : addresses){
            AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);
            addressDTOS.add(addressDTO);
        }
        return addressDTOS;
    }

    @Override
    public AddressDTO getAddressById(Long id) throws ItemNotFoundException {
        Optional<Address> find = addressRepo.findById(id);
        if(find.isPresent()){
            Address address = find.get();
            AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);
            return addressDTO;
        }
        else
            throw new ItemNotFoundException("Address Not Found !!!");
    }

    @Transactional
    @Override
    public AddressDTO createAddress(Address address) {
        Address newAddress = addressRepo.save(address);
        AddressDTO addressDTO = modelMapper.map(newAddress, AddressDTO.class);
        return addressDTO;
    }

    @Override
    public Boolean deleteAddressById(Long id) {
        Optional<Address> find = addressRepo.findById(id);
        if(find.isPresent()){
            addressRepo.delete(find.get());
            return true;
        }
        else
            return false;
    }

    @Override
    public AddressDTO updateAddress(Long id, Address address) {
        Optional<Address> find = addressRepo.findById(id);
        if(find.isPresent()){
            Address found = find.get();
            found.setCity(address.getCity());
            found.setZip(address.getZip());
            found.setCountry(address.getCountry());
            found.setState(address.getState());
            found.setStreet(address.getStreet());
            addressRepo.save(found);
            AddressDTO addressDTO = modelMapper.map(found, AddressDTO.class);
            return addressDTO;
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found");
    }
}
