import {useEffect, useState} from "react";
import type {DepositItemRequest} from "../../types/types.ts";
import agent from "../agent.ts";

export function useDeposit() {
    const [deposits, setDeposit] = useState<DepositItemRequest[]>([])
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState<string | null>(null);

    const CURRENT_USERID = 1

    useEffect(() => {
        const getDeposits = async () => {
            try {
                setLoading(true);
                setError(null);
                const res = await agent.get<DepositItemRequest[]>("/deposit/items")
                setDeposit(res.data);
            } catch (err: any) {
                console.error("error loading deposits", err)
                setError("Error loading deposits")
            } finally {
                setLoading(false);
            }
        }

        getDeposits()
    }, [])

    const depositItem = async (itemDefinitionId: number) => {
        try {
            const itemId: DepositItemRequest = {itemDefinitionId};

            await agent.post(`/deposit/items/${CURRENT_USERID}`, itemId)
            return {success: true}
        } catch (err: any) {
            const serverError = err.response?.data?.message || "Deposit failed";
            return {success: false, error: serverError}
        }
    }

    return{deposits, loading, error, depositItem}
}