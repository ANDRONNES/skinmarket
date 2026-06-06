import {useState} from "react";
import {useInventory} from "../api/hooks/useInventory.ts";
import type {Inventory} from "../types/types.ts";
import SellModal from "../components/SellModal.tsx";
import InventoryCard from "../components/InventoryCard.tsx";

export default function InventoryPage() {

    const {inventory, loading, instantSell, listOnMarket} = useInventory();
    const [itemToSell, setItemToSell] = useState<Inventory | null>(null);

    const handleInstantSellSubmit = async (itemId: number) => {
        const res = await instantSell(itemId);
        if (res.success) {
            alert("Sold instantly!");
            setItemToSell(null);
        } else {
            alert(`Error: ${res.error}`);
        }
    };

    const handleListOnMarketSubmit = async (itemId: number, price: string) => {
        const res = await listOnMarket(itemId, price);
        if (res.success) {
            alert("Successfully listed on market!");
            setItemToSell(null);
        } else {
            alert(`Error: ${res.error}`);
        }
    };

    if (loading) return <div>Loading...</div>;

    return (
        <div className="market-container">
            <h2>My Inventory</h2>
            <div className="listing-grid">
                {inventory.map(item => (
                    <InventoryCard
                        key={item.itemInstanceId}
                        item={item}
                        onSell={() => setItemToSell(item)}
                    />
                ))}
            </div>

            {itemToSell && (
                <SellModal
                    key={itemToSell.itemInstanceId}
                    item={itemToSell}
                    onClose={() => setItemToSell(null)}
                    onInstantSell={handleInstantSellSubmit}
                    onListOnMarket={handleListOnMarketSubmit}
                />
            )}
        </div>
    );
}