package com.miu.pmtbackendapi.domain.favorite.response;

import com.miu.pmtbackendapi.domain.favorite.FavouriteProperty;
import lombok.Data;

import java.util.List;

@Data
public class FavouriteProperties {
    List<FavouriteProperty> properties;
}


