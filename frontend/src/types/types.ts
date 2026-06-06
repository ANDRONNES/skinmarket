type Listing = {
    listingId: number;
    price: string;
    itemName: string;
    skinExterior: string | null;
    skinFloat: number | null;
    pattern: number | null;
    imageUrl: string | null;
}

type BuyRequest = {
    buyerId: number
}

type PriceRequest = {
    price: string
}

type Inventory = {
    itemInstanceId: number;
    itemName: string;
    skinExterior: string | null;
    skinFloat: number | null;
    pattern: number | null;
    instantSellPrice: string;
    imageUrl: string | null;
}

export type {Listing, BuyRequest, PriceRequest, Inventory}