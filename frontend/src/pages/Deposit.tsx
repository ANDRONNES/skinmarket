import "../styles/Market.css"
import DepositCard from "../components/DepositCard.tsx";
import {useDeposit} from "../api/hooks/useDeposit.ts";

export default function Deposit() {
    const {deposits, loading, error, depositItem} = useDeposit();

    const handleDeposit = async (itemDefinitionId: number) => {
        const result = await depositItem(itemDefinitionId)
        if (result.success) {
            alert("Item has deposited successfully!")
        } else {
            alert(`Error: ${result.error}`)
        }
    }
    if (loading) return <div style={{padding: '2rem'}}>Loading market...</div>;

    if (error) return <div style={{padding: '2rem', color: 'red'}}>Error: {error}</div>;
    return (
        <div className="market-container">
            <h2>Deposit Items</h2>
            {deposits.length === 0 ? (
                <h4>No skins to deposit right now</h4>
            ) : (
                <div className="listing-grid">
                    {deposits.map(deposit => (
                        <DepositCard
                            key={deposit.itemDefinitionId}
                            item={deposit}
                            onDeposit={() => handleDeposit(deposit.itemDefinitionId)}
                        />
                    ))}
                </div>
            )}
        </div>
    );
}