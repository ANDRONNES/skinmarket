import type {Listing} from "../types/types.ts";
import "../styles/ListingCard.css"

type Props = {
    listing: Listing
    onBuy: (listingId: number) => void;
}

export default function ListingCard({listing, onBuy}: Props) {
    return (
        <div className="card-container">
            {listing.imageUrl === null ? (
                <div className="img-placeholder">
                    <span>No image</span>
                </div>
            ) : (
                <img src={listing.imageUrl} alt={listing.itemName} className="item-img"/>
            )}
            <div className="info-container">
                <h3>
                    {listing.itemName}
                    {listing.skinExterior !== null && ` (${listing.skinExterior})`}
                </h3>
                <div className="specs">
                    {listing.skinFloat !== null && (
                        <span>Float: {listing.skinFloat} </span>
                    )}
                    {listing.pattern !== null && (
                        <span>Pattern: {listing.pattern}</span>
                    )}
                </div>
                <div className="buy-section">
                    <span className="price-tag">{listing.price} $</span>
                    <button className="buy-btn" onClick={() => onBuy(listing.listingId)}>
                        Buy
                    </button>
                </div>
            </div>
        </div>
    )
}