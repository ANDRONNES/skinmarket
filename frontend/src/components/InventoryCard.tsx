import type {Inventory} from "../types/types.ts";
import "../styles/ListingCard.css"

type Props = {
    item: Inventory
    // onBuy: (listingId : number) => void;
}

export default function ListingCard({item}: Props) {

    const hasInstantSell = parseFloat(item.instantSellPrice) > 0;

    return (
        <div className="card-container">
            {item.imageUrl === null ? (
                <div className="img-placeholder">
                    <span>No image</span>
                </div>
            ) : (
                <img src={item.imageUrl} alt={item.itemName} className="item-img"/>
            )}
            <div className="info-container">
                <h3>
                    {item.itemName}
                    {item.skinExterior !== null && ` (${item.skinExterior})`}
                </h3>
                <div className="specs">
                    {item.skinFloat !== null && (
                        <span>Float: {item.skinFloat} </span>
                    )}
                    {item.pattern !== null && (
                        <span>Pattern: {item.pattern}</span>
                    )}
                </div>
                <div className="buy-section">
                    {hasInstantSell ? (
                        <span className="price-tag">
                                <p>Instant sell price:</p>
                            {item.instantSellPrice} $
                            </span>
                    ) : (
                        <span className="no-orders-tag">
                            No active buy orders
                            </span>
                    )}
                    <button
                        className="sell-btn"
                        // onClick={() => onInstantSell?.(item.itemInstanceId)}
                    >
                        Sell
                    </button>
                </div>
            </div>
        </div>
    )
}