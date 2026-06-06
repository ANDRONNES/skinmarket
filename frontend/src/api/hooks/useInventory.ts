import {useEffect, useState} from "react";
import type {Inventory, PriceRequest} from "../../types/types.ts";
import agent from "../agent.ts";
import {useParams} from "react-router";

export function useInventory() {
    const [inventory, setInventory] = useState<Inventory[]>([])
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState<string | null>(null);

    const {userId} = useParams<{ userId: string }>();

    const getInventory = async (isSilent = false) => {
        try {
            if (!isSilent) {
                setLoading(true);
            }
            setError(null);
            const res = await agent.get<Inventory[]>(`/inventory/${userId}`);
            setInventory(res.data);
        } catch (err: any) {
            console.error("error loading listing", err)
            setError("Error loading lisings")
        } finally {
            setLoading(false);
        }
    };


    useEffect(() => {
        getInventory();
    }, [])

    const listOnMarket = async (itemInstanceId: number, price: string) => {
        try {
            const priceRequest: PriceRequest = {price};

            await agent.post(`/inventory/${userId}/items/${itemInstanceId}/list`, priceRequest);
            setInventory(prevInventory => prevInventory
                .filter(item => item.itemInstanceId !== itemInstanceId));
            return {success: true}
        } catch (err: any) {
            const serverError = err.response?.data?.message || "List on market failed";
            return {success: false, error: serverError}
        }
    }

    const instantSell = async (itemInstanceId: number) => {
        try {
            await agent.post(`/inventory/${userId}/items/${itemInstanceId}/instant`)
            setInventory(prevInventory => prevInventory
                .filter(item => item.itemInstanceId !== itemInstanceId))
            await getInventory(true)
            return {success: true}
        } catch (err: any) {
            const serverError = err.response?.data?.message || "Instant sell on market failed";
            return {success: false, error: serverError}
        }
    }


    return {inventory, loading, error, listOnMarket, instantSell}
}