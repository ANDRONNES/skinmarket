package com.skinmarket.project.mapper;

import com.skinmarket.project.dto.ListingDTO;
import com.skinmarket.project.model.entity.CharmInstance;
import com.skinmarket.project.model.entity.Listing;
import com.skinmarket.project.model.entity.SkinInstance;
import org.springframework.stereotype.Component;

@Component
public class ListingMapper implements DtoMapper<ListingDTO,Listing> {
    public ListingDTO toDto(Listing listing) {

        if(listing == null){
            return null;
        }

        var item = listing.getItemInstance();

        if (item instanceof SkinInstance skin) {
            return new ListingDTO(
                    listing.getListingId(),
                    listing.getPrice(),
                    item.getItemDefinition().getName(),
                    skin.getExterior(),
                    skin.getSkinFloat(),
                    skin.getPattern()
            );
        }

        if (item instanceof CharmInstance charm) {
            return new ListingDTO(
                    listing.getListingId(),
                    listing.getPrice(),
                    item.getItemDefinition().getName(),
                    null,
                    null,
                    charm.getPattern()
            );
        }

        throw new IllegalStateException("Unknown item type: " + item.getClass());
    }
}
