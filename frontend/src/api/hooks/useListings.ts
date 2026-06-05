import {useEffect, useState } from "react";
import type {Listing, BuyRequest} from "../../types/types.ts";
import agent from "../agent.ts";

export function useListings(){
    const [listings, setListings] = useState<Listing[]>([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const CURRENT_USERID = 1

    useEffect(() => {
        const getListings = async () => {
            try{
                setLoading(true);
                setError(null);
                const res = await agent.get<Listing[]>("/market/listings")
                setListings(res.data);
            } catch(err: any){
                console.error("error loading listing", err)
                setError("Error loading lisings")
            } finally {
                setLoading(false);
            }
        }

        getListings()
    },[])

    const buyListing = async (listingId: number) => {
        try{
            const requestData: BuyRequest = {
                buyerId: CURRENT_USERID
            }
            await agent.post(`/market/listings/${listingId}/buy`, requestData);
            setListings(prevListings => prevListings.filter(item => item.listingId !== listingId));
            return { success : true }
        } catch(err: any){
            const serverError = err.response?.data?.message || "Purchase failed";
            return { success: false, error: serverError}
        }
    };

    return { listings, loading, error, buyListing}
}