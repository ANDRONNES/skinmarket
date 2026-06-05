import "../styles/Market.css"
import {useInventory} from "../api/hooks/useInventory.ts";
import InventoryCard from "../components/InventoryCard.tsx";

export default function Market() {
    const {inventory, loading, error} = useInventory()

    // const handleBuy = async (listingId: number)=> {
    //     const result = await buyListing(listingId)
    //     if (result.success){
    //         alert("Item has bought successfully!")
    //     } else{
    //         alert(`Error: ${result.error}`)
    //     }
    // }
    if (loading) return <div style={{padding: '2rem'}}>Loading inventory...</div>;

    if (error) return <div style={{padding: '2rem', color: 'red'}}>Error: {error}</div>;
    return (
        <div className="market-container">
            <h2>Items in inventory</h2>
            {inventory.length === 0 ? (
                <h4>Your inventory is empty...</h4>
            ) : (
                <div className="listing-grid">
                    {inventory.map(inventoryItem => (
                        <InventoryCard
                            key={inventoryItem.itemInstanceId}
                            item={inventoryItem}
                            // onBuy={handleBuy}
                        />
                    ))}
                </div>
            )}
        </div>
    );
}