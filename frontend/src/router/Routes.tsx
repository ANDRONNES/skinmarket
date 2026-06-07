import {createBrowserRouter, Navigate} from "react-router";
import App from "../layout/App.tsx";
import Market from "../pages/Market.tsx";
import Inventory from "../pages/Inventory.tsx";
import Deposit from "../pages/Deposit.tsx";

export const router = createBrowserRouter([
    {
        path: "/",
        element: <App/>,
        children: [
            {path: "/", element: <Navigate to={"/market"} replace/>},
            {path: "/market", element: <Market/>},
            {path: "/inventory/:userId", element: <Inventory/>},
            {path: "/deposit/", element: <Deposit />},
            {path: "*", element: <Navigate replace to=""/>},
        ]
    }
])