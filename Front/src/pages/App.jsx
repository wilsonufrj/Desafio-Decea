import React from "react";

import Home from "./Home";


import { AuthProvider } from "../context/AuthContext";

import LoginPage from "./login";


import AppRoutes from "../AppRoutes";


const App =()=>{
    return(
        <div>
            <AppRoutes/>
        </div>
    )
}

export default App;