package com.miu.pmtbackendapi.service.favourite.impl;

import com.miu.pmtbackendapi.domain.favorite.FavouriteProperty;
import com.miu.pmtbackendapi.domain.property.Property;
import com.miu.pmtbackendapi.repo.favourite.FavouriteRepository;
import com.miu.pmtbackendapi.service.favourite.FavouritePropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavouritePropertyServiceImpl implements FavouritePropertyService {

    private final FavouriteRepository favouriteRepository;

    @Override
    public List<Property> getAllFavouritePropertiesOfUser(Long useId) {
        List<Property> favs = favouriteRepository.getAllFavouritePropertiesOfUser(useId);
        return favs;
    }

    @Override
    public Optional<FavouriteProperty> getAFavouriteProperty(Long favPropId) {
        return favouriteRepository.findById(favPropId);
    }

    @Override
    public FavouriteProperty addFavouriteProperty(FavouriteProperty favouriteProperty) {
        return favouriteRepository.save(favouriteProperty);
    }

    @Override
    public Boolean removeFavouriteProperty(Long favPropId) {
        Optional<FavouriteProperty> user = favouriteRepository.findById(favPropId);
        if (user.isPresent()) {
            favouriteRepository.deleteById(favPropId);
            return true;
        } else {
            return false;
        }
    }
}
