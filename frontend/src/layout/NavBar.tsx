import {NavLink} from "react-router";
import "../styles/NavBar.css"

export default function NavBar() {
    const CURRENT_USERID = 1;

    return (
        <>
            <header className="nav">
                <div className="link-container">
                    <NavLink to="/market">
                        MARKET
                    </NavLink>
                    <NavLink to={`/inventory/${CURRENT_USERID}`}>
                        INVENTORY
                    </NavLink>
                    <NavLink to="/deposit">
                            DEPOSIT
                    </NavLink>
                </div>

                <div className={"label"}>
                    <NavLink to="/market">
                        <h1>
                            SKIN MARKET
                        </h1>
                    </NavLink>
                </div>
            </header>
        </>
    )
}