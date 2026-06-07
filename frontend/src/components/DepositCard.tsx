import type {ItemDefinition} from "../types/types.ts";
import "../styles/ListingCard.css"

type Props = {
    item: ItemDefinition
    onDeposit: () => void;
}

export default function DepositCard({item, onDeposit}: Props) {
    return (
        <div className="card-container">
            {item.imageUrl === null ? (
                <div className="img-placeholder">
                    <span>No image</span>
                </div>
            ) : (
                <img src={item.imageUrl} alt={item.name} className="item-img"/>
            )}
            <div className="info-container">
                <h3>
                    {item.name}
                </h3>
                <div className="buy-section">
                    <button className="buy-btn" onClick={onDeposit}>
                        Deposit
                    </button>
                </div>
            </div>
        </div>
    )
}