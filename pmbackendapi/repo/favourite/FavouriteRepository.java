package com.miu.pmtbackendapi.repo.favourite;

import com.miu.pmtbackendapi.domain.favorite.FavouriteProperty;
import com.miu.pmtbackendapi.domain.property.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteRepository extends JpaRepository<FavouriteProperty, Long> {

    @Query("SELECT p from FavouriteProperty f JOIN User u JOIN Property p where u.userId=:userId")
    List<Property> getAllFavouritePropertiesOfUser(Long userId);

}
