package com.miu.pmtbackendapi.service.favourite;

import com.miu.pmtbackendapi.domain.favorite.FavouriteProperty;
import com.miu.pmtbackendapi.domain.favorite.response.FavouriteProperties;
import com.miu.pmtbackendapi.domain.property.Property;

import java.util.List;
import java.util.Optional;

public interface FavouritePropertyService {

    //    FavouriteProperties getAllFavouritePropertiesOfUser(Long userId); //change it to DOT Object
    List<Property> getAllFavouritePropertiesOfUser(Long userId);

    Optional<FavouriteProperty> getAFavouriteProperty(Long favPropId);

    FavouriteProperty addFavouriteProperty(FavouriteProperty favouriteProperty);

    Boolean removeFavouriteProperty(Long favPropId);
}
