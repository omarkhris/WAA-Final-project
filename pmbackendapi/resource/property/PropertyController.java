package com.miu.pmtbackendapi.resource.property;


import com.miu.pmtbackendapi.domain.address.Address;
import com.miu.pmtbackendapi.domain.dao.PropertySearchDao;
import com.miu.pmtbackendapi.domain.dao.SearchRequest;
import com.miu.pmtbackendapi.domain.enums.HomeConditionEnum;
import com.miu.pmtbackendapi.domain.enums.PropertyStatusEnum;
import com.miu.pmtbackendapi.domain.enums.PropertyTypeEnum;
import com.miu.pmtbackendapi.domain.property.Property;
import com.miu.pmtbackendapi.domain.propertydetail.PropertyDetail;
import com.miu.pmtbackendapi.exception.customexception.ItemNotFoundException;
import com.miu.pmtbackendapi.service.property.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/properties")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PropertyController {

    private final PropertyService propertyService;


    @GetMapping("/")
    ResponseEntity<?> getAllProperties() {
        return new ResponseEntity<>(propertyService.getAllProperties(), HttpStatus.OK);
    }
    private final PropertySearchDao propertySearchDao;


    @GetMapping("/{id}")
    ResponseEntity<?> getPropertyById(@PathVariable("id") Long id) throws ItemNotFoundException {
        return new ResponseEntity<>(propertyService.
                getPropertyById(id), HttpStatus.OK);
    }


    @PostMapping(value = "/")

    ResponseEntity<?> createNewProperty(@RequestBody Property property) {
        return new ResponseEntity<>(propertyService.createProperty(property), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePropertyById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(propertyService.deletePropertyById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updatePropertyById(@PathVariable("id") Long id, @RequestBody Property property) {
        return new ResponseEntity<>(propertyService.updateProperty(id, property), HttpStatus.OK);
    }

    @GetMapping("/owner/{userId}")
    ResponseEntity<?> getPropertyByOwnerId(@PathVariable("userId") Long ownerId) throws ItemNotFoundException {
        return new ResponseEntity<>(propertyService.getPropertyByOwner(ownerId), HttpStatus.OK);
    }

    // TODO  search and filter


    @GetMapping("/property/")

    ResponseEntity<?> filterPropertyByParams(@RequestParam(value = "street", required = false) String street,
                                             @RequestParam(value = "city", required = false) String city,
                                             @RequestParam(value = "state", required = false) String state,
                                             @RequestParam(value = "zip", required = false) String zip,
                                             @RequestParam(value = "country", required = false) String country,
                                             @RequestParam(value = "propertyType", required = false) PropertyTypeEnum propertyType,
                                             @RequestParam(value = "roomNum", required = false) Integer roomNumber,
                                             @RequestParam(value = "price", required = false) Double propertyPrice) {


        return new ResponseEntity<>(propertyService.getPropertiesByParam(street, city, state, zip, country, propertyType, roomNumber, propertyPrice), HttpStatus.FOUND);
    }


    @GetMapping("/search/")
    ResponseEntity<?> searchForAProperty(
            @RequestParam(name = "street", required = false) String street,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "state", required = false) String state,
            @RequestParam(name = "zip", required = false) String zip,
            @RequestParam(name = "country", required = false) String country,
            @RequestParam(name = "propertyType", required = false) PropertyTypeEnum propertyType,
            @RequestParam(name = "numberOfRooms", required = false) Integer numberOfRooms,
            @RequestParam(name = "propertyPrice", required = false) Double price,
            @RequestParam(name = "mortgageBalance", required = false) Double mortgageBalance,
            @RequestParam(name = "partialBathroomNum", required = false) Integer partialBathroomNum,
            @RequestParam(name = "fullBathroomNum", required = false) Integer fullBathroomNum,
            @RequestParam(name = "lotSize", required = false) Double lotSize,
            @RequestParam(name = "homeSize", required = false) Double homeSize,
            @RequestParam(name = "yearBuild", required = false) String yearBuild,
            @RequestParam(name = "hasTenant", required = false) Boolean hasTenant,
            @RequestParam(name = "homeCondition", required = false) HomeConditionEnum homeCondition,
            @RequestParam(name = "statusEnum", required = false) PropertyStatusEnum statusEnum) {

        SearchRequest searchRequest = new SearchRequest();
        PropertyDetail propertyDetail = new PropertyDetail();
        Address address = new Address();
        Property property = new Property();
        propertyDetail.setPropertyType(propertyType);
        propertyDetail.setPropertyPrice(price);
        propertyDetail.setRoomNum(numberOfRooms);
        propertyDetail.setMortgageBalance(mortgageBalance);
        propertyDetail.setYearBuild(yearBuild);
        propertyDetail.setLotSize(lotSize);
        propertyDetail.setHomeSize(homeSize);
        propertyDetail.setHomeCondition(homeCondition);
        propertyDetail.setPartialBathroomNum(partialBathroomNum);
        propertyDetail.setFullBathroomNum(fullBathroomNum);
        propertyDetail.setHasTenant(hasTenant);
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZip(zip);
        address.setCountry(country);

        property.setStatusEnum(statusEnum);
        
        searchRequest.setPropertyDetail(propertyDetail);
        searchRequest.setAddress(address);
        searchRequest.setStatusEnum(property.getStatusEnum());

        List<Property> results = propertySearchDao.findAllByCriteria(searchRequest);

        return new ResponseEntity<>(results, HttpStatus.OK);


    }
}
