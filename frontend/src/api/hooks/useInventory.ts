import {useEffect, useState } from "react";
import type {Inventory} from "../../types/types.ts";
import agent from "../agent.ts";
import { useParams } from "react-router";

export function useInventory(){
    const [inventory, setInventory] = useState<Inventory[]>([])
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState<string | null>(null);

    const {userId} = useParams<{ userId : string}>();

    useEffect(() => {
        const getInventory = async()  => {
            try{
                setLoading(true);
                setError(null);
                const res = await agent.get<Inventory[]>(`/inventory/${userId}`);
                setInventory(res.data);
            } catch(err: any){
                console.error("error loading listing", err)
                setError("Error loading lisings")
            } finally {
                setLoading(false);
            }
        }
        getInventory();
    },[])

    return {inventory, loading, error}
}