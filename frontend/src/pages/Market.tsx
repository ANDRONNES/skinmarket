import {useListings} from "../api/hooks/useListings.ts";
import ListingCard from "../components/ListingCard";
import "../styles/Market.css"

export default function Market() {
    const {listings, loading, error, buyListing} = useListings();

    const handleBuy = async (listingId: number) => {
        const result = await buyListing(listingId)
        if (result.success) {
            alert("Item has bought successfully!")
        } else {
            alert(`Error: ${result.error}`)
        }
    }
    if (loading) return <div style={{padding: '2rem'}}>Loading market...</div>;

    if (error) return <div style={{padding: '2rem', color: 'red'}}>Error: {error}</div>;
    return (
        <div className="market-container">
            <h2>Active Market Listings</h2>
            {listings.length === 0 ? (
                <h4>No skins listed on market right now</h4>
            ) : (
                <div className="listing-grid">
                    {listings.map(marketListing => (
                        <ListingCard
                            key={marketListing.listingId}
                            listing={marketListing}
                            onBuy={handleBuy}
                        />
                    ))}
                </div>
            )}
        </div>
    );
}