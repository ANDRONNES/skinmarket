import {useState} from "react";
import type {Inventory} from "../types/types.ts";
import "../styles/Modal.css";

type State = "CHOICE" | "INSTANT_CONFIRM" | "MARKET_INPUT" | "MARKET_CONFIRM";

type Props = {
    item: Inventory;
    onClose: () => void;
    onInstantSell: (itemId: number) => Promise<void>;
    onListOnMarket: (itemId: number, price: string) => Promise<void>;
};

export default function SellItemModal({item, onClose, onInstantSell, onListOnMarket}: Props) {
    const [state, setState] = useState<State>("CHOICE");
    const [price, setPrice] = useState<string>("");
    const [inputError, setInputError] = useState<string | null>(null);

    const handleGoToMarketConfirm = () => {
        const parsedPrice = parseFloat(price);
        if (!price || isNaN(parsedPrice) || parsedPrice <= 0) {
            setInputError("Please enter a valid price greater than 0!");
            return;
        }
        setInputError(null);
        setState("MARKET_CONFIRM");
    };

    return (
        <div className="modal-backdrop" onClick={onClose}>
            <div className="modal-box" onClick={(e) => e.stopPropagation()}>
                <button className="close-x" onClick={onClose}>
                    &#10006;
                </button>

                {state === "CHOICE" && (
                    <>
                        <h3>Sell {item.itemName}</h3>
                        <p>Choose how you want to sell this item:</p>
                        <div className="modal-actions">
                            <button className="modal-btn primary" onClick={() => setState("INSTANT_CONFIRM")}>
                                Instant Sell for {item.instantSellPrice} $
                            </button>
                            <button className="modal-btn secondary" onClick={() => setState("MARKET_INPUT")}>
                                List on Market (Set your price)
                            </button>
                        </div>
                    </>
                )}

                {state === "INSTANT_CONFIRM" && (
                    <>
                        <h3>Confirm Instant Sell</h3>
                        <p>Are you sure you want to instantly
                            sell <strong>{item.itemName}</strong> for <strong>{item.instantSellPrice} $</strong>?</p>
                        <div className="modal-actions">
                            <button className="modal-btn danger" onClick={() => onInstantSell(item.itemInstanceId)}>
                                Yes, Sell Instantly
                            </button>
                            <button className="modal-btn secondary" onClick={() => setState("CHOICE")}>
                                Back
                            </button>
                        </div>
                    </>
                )}

                {state === "MARKET_INPUT" && (
                    <>
                        <h3>List on Market</h3>
                        <p>Enter the price you want to sell <strong>{item.itemName}</strong> for:</p>

                        <input
                            type="number"
                            className="price-input"
                            placeholder="0.00"
                            value={price}
                            onChange={(e) => setPrice(e.target.value)}
                        />
                        {inputError &&
                            <p style={{color: "red", fontSize: "0.85rem", marginTop: "0.5rem"}}>{inputError}</p>}

                        <div className="modal-actions">
                            <button className="modal-btn primary" onClick={handleGoToMarketConfirm}>
                                Continue
                            </button>
                            <button className="modal-btn secondary" onClick={() => setState("CHOICE")}>
                                Back
                            </button>
                        </div>
                    </>
                )}

                {state === "MARKET_CONFIRM" && (
                    <>
                        <h3>Confirm Listing</h3>
                        <p>Are you sure you want to list <strong>{item.itemName}</strong> on the public market
                            for <strong>{price} $</strong>?</p>
                        <div className="modal-actions">
                            <button className="modal-btn primary"
                                    onClick={() => onListOnMarket(item.itemInstanceId, price)}>
                                Yes, List Item
                            </button>
                            <button className="modal-btn secondary" onClick={() => setState("MARKET_INPUT")}>
                                Back
                            </button>
                        </div>
                    </>
                )}

            </div>
        </div>
    );
}