package com.miu.pmtbackendapi.domain.dao;

import com.miu.pmtbackendapi.domain.address.Address;
import com.miu.pmtbackendapi.domain.enums.HomeConditionEnum;
import com.miu.pmtbackendapi.domain.enums.PropertyStatusEnum;
import com.miu.pmtbackendapi.domain.enums.PropertyTypeEnum;
import com.miu.pmtbackendapi.domain.property.Property;
import com.miu.pmtbackendapi.domain.propertydetail.PropertyDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class PropertySearchDao {


    private final EntityManager em;

    public List<Property> findAllByCriteria(SearchRequest searchRequest) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Property> criteriaQuery = criteriaBuilder.createQuery(Property.class);

        List<Predicate> predicates = new ArrayList<>();

        Root<Property> root = criteriaQuery.from(Property.class);

        if (searchRequest.getAddress() != null) {
            Join<Property, Address> addressJoin = root.join("address", JoinType.LEFT);
            List<Predicate> addressPredicates = new ArrayList<>();
            if (searchRequest.getAddress().getStreet() != null) {
                String searchTerm = "%" + searchRequest.getAddress().getStreet().toLowerCase() + "%";
                addressPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(addressJoin.get("street")), searchTerm));
            }
            if (searchRequest.getAddress().getCountry() != null) {
                String searchTerm = "%" + searchRequest.getAddress().getCountry().toLowerCase() + "%";
                addressPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(addressJoin.get("country")), searchTerm));
            }
            if (searchRequest.getAddress().getZip() != null) {
                String searchTerm = "%" + searchRequest.getAddress().getZip().toLowerCase() + "%";
                addressPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(addressJoin.get("zip")), searchTerm));
            }
            if (searchRequest.getAddress().getState() != null) {
                String searchTerm = "%" + searchRequest.getAddress().getState().toLowerCase() + "%";
                addressPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(addressJoin.get("state")), searchTerm));
            }
            if (searchRequest.getAddress().getCity() != null) {
                String searchTerm = "%" + searchRequest.getAddress().getCity().toLowerCase() + "%";
                addressPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(addressJoin.get("city")), searchTerm));
            }

            Predicate anyAttributePredicate = criteriaBuilder.and(addressPredicates.toArray(new Predicate[0]));
            predicates.add(anyAttributePredicate);


        }

        if (searchRequest.getPropertyDetail() != null) {
            Join<Property, PropertyDetail> propertyDetailJoin = root.join("propertyDetail", JoinType.LEFT);
            List<Predicate> propertyDetailPredicate = new ArrayList<>();


            if (searchRequest.getPropertyDetail().getFullBathroomNum() != null)
                propertyDetailPredicate.add(criteriaBuilder.equal(propertyDetailJoin.get("fullBathroomNum"), searchRequest.getPropertyDetail().getFullBathroomNum()));
            if (searchRequest.getPropertyDetail().getHasTenant() != null) {
                boolean hasTenant = searchRequest.getPropertyDetail().getHasTenant();
                Predicate tenantPredicate = criteriaBuilder.equal(propertyDetailJoin.get("hasTenant"), hasTenant);
                predicates.add(tenantPredicate);
            }
            if (searchRequest.getPropertyDetail().getHomeCondition() != null) {
                String searchTerm = "%" + searchRequest.getPropertyDetail().getHomeCondition().toString().toLowerCase() + "%";
                propertyDetailPredicate.add(criteriaBuilder.like(criteriaBuilder.lower(propertyDetailJoin.get("homeCondition")), searchTerm));
            }
            if (searchRequest.getPropertyDetail().getHomeSize() != null)
                propertyDetailPredicate.add(criteriaBuilder.equal(propertyDetailJoin.get("homeSize"), searchRequest.getPropertyDetail().getHomeSize()));
            if (searchRequest.getPropertyDetail().getLotSize() != null)
                propertyDetailPredicate.add(criteriaBuilder.equal(propertyDetailJoin.get("lotSize"), searchRequest.getPropertyDetail().getLotSize()));
            if (searchRequest.getPropertyDetail().getMortgageBalance() != null)
                propertyDetailPredicate.add(criteriaBuilder.equal(propertyDetailJoin.get("mortgageBalance"), searchRequest.getPropertyDetail().getMortgageBalance()));

            if (searchRequest.getPropertyDetail().getPartialBathroomNum() != null)
                propertyDetailPredicate.add(criteriaBuilder.equal(propertyDetailJoin.get("partialBathroomNum"), searchRequest.getPropertyDetail().getPartialBathroomNum()));
            if (searchRequest.getPropertyDetail().getPropertyPrice() != null)
                propertyDetailPredicate.add(criteriaBuilder.equal(propertyDetailJoin.get("propertyPrice"), searchRequest.getPropertyDetail().getPropertyPrice()));

            if (searchRequest.getPropertyDetail().getPropertyType() != null) {
                String searchTerm = "%" + searchRequest.getPropertyDetail().getPropertyType().toString().toLowerCase() + "%";
                propertyDetailPredicate.add(criteriaBuilder.like(criteriaBuilder.lower(propertyDetailJoin.get("propertyType")), searchTerm));
            }

            if (searchRequest.getPropertyDetail().getRoomNum() != null)
                propertyDetailPredicate.add(criteriaBuilder.equal(propertyDetailJoin.get("roomNum"), searchRequest.getPropertyDetail().getRoomNum()));

            if (searchRequest.getPropertyDetail().getYearBuild() != null) {
                String searchTerm = "%" + searchRequest.getPropertyDetail().getYearBuild().toLowerCase() + "%";
                Predicate yearBuildPredicate = criteriaBuilder.like(criteriaBuilder.lower(propertyDetailJoin.get("yearBuild")), searchTerm);
                propertyDetailPredicate.add(yearBuildPredicate);
            }
            Predicate anyDetailAttributePredicate = criteriaBuilder.and(propertyDetailPredicate.toArray(new Predicate[0]));
            predicates.add(anyDetailAttributePredicate);

        }

        if (searchRequest.getStatusEnum() != null) {
            String statusValue = searchRequest.getStatusEnum().getValue();
            if (statusValue != null) {
                Predicate statusPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("statusEnum").as(String.class)), "%" + statusValue.toLowerCase() + "%");
                predicates.add(statusPredicate);
            }
        }


        Predicate[] allPredicates = predicates.toArray(new Predicate[0]);
        criteriaQuery.where(criteriaBuilder.and(allPredicates));

        TypedQuery<Property> query = em.createQuery(criteriaQuery);
        List<Property> results = query.getResultList();

        return results;

    }
}
