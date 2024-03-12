package com.miu.pmtbackendapi.repo.property;

import com.miu.pmtbackendapi.domain.enums.PropertyTypeEnum;
import com.miu.pmtbackendapi.domain.property.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    @Query("select p from Property p where p.propertyId=:id")
    Property getPropertyByPropertyId(Long id);

    Optional<List<Property>> findPropertyByOwner_UserId(Long userId);

    @Query(value = "SELECT p FROM Property p " +
            "WHERE (:street is null OR p.address.street = :street) " +
            "OR (:city is null OR p.address.city = :city) " +
            "OR (:state is null OR p.address.state = :state)" +
            "OR (:zip is null OR p.address.zip = :zip)" +
            "OR (:country is null OR p.address.country = :country)" +
            "OR (:propertyType is null OR p.propertyDetail.propertyType = :propertyType)" +
            "OR (:roomNum is null OR p.propertyDetail.roomNum = :roomNum)" +
            "OR (:price is null OR p.propertyDetail.propertyPrice = :price)")
    List<Property> findPropertiesByParam(@Param("street") String street,
                                         @Param("city") String city,
                                         @Param("state") String state,
                                         @Param("zip") String zip,
                                         @Param("country") String country,
                                         @Param("propertyType") PropertyTypeEnum propertyType,
                                         @Param("roomNum") Integer roomNum,
                                         @Param("price") Double price);


    default List<Property> filterPropertiesByCriteria(String street, String city,
                                                      String state, String zip, String country,
                                                      PropertyTypeEnum propertyType, Integer roomNum,
                                                      Double propertyPrice) {
//        EntityManager entityManager = new E
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        return null;
    }

}
