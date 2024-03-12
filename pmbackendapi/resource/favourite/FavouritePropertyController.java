package com.miu.pmtbackendapi.resource.favourite;

import com.miu.pmtbackendapi.domain.favorite.FavouriteProperty;
import com.miu.pmtbackendapi.domain.user.response.UserResponse;
import com.miu.pmtbackendapi.exception.customexception.ItemNotFoundException;
import com.miu.pmtbackendapi.service.favourite.FavouritePropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/favourite")
public class FavouritePropertyController {

    private final FavouritePropertyService favouritePropertyService;

    @GetMapping("/property/{userId}")
    public ResponseEntity<?> getAllFavouritePropertiesOfUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(favouritePropertyService.getAllFavouritePropertiesOfUser(userId));
    }

    @GetMapping("/{favId}")
    public ResponseEntity<?> getAFavouriteProperty(@PathVariable("favId") Long favId) throws ItemNotFoundException {

        Optional<FavouriteProperty> fav = favouritePropertyService.getAFavouriteProperty(favId);

        if (fav.isPresent()) {
            return ResponseEntity.ok().body(fav.get());
        } else {
            throw new ItemNotFoundException("User has no such favourite property.");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> addFavouriteProperty(FavouriteProperty favouriteProperty) {
        return ResponseEntity.ok(favouritePropertyService.addFavouriteProperty(favouriteProperty));
    }

    @DeleteMapping("/{favId}")
    public ResponseEntity<?> removeFavouriteProperty(@PathVariable("favId") Long favPropId) throws ItemNotFoundException {
        Boolean removed = favouritePropertyService.removeFavouriteProperty(favPropId);

        if (removed) {
            return ResponseEntity.ok(new ResponseEntity<>("Property removed from favourite.", HttpStatus.OK));
        } else {
            throw new ItemNotFoundException("User has no such favourite property.");
        }
    }
}
