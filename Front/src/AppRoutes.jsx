import { BrowserRouter as Routers, Route, Routes, Navigate } from "react-router-dom"
import React, { useContext } from 'react'

import LoginPage from "./pages/login"
import Home from "./pages/Home"

import { AuthProvider, AuthContext } from "./context/AuthContext"

const AppRoutes = () => {

  const Private = ({ children }) => {
    const { authenticated,loading } = useContext(AuthContext);

    if(loading){
      return <div className="loading" >Carregando....</div>
    }

    if (!authenticated)
      return <Navigate to="/" />

    return children;
  }

  return (
    <Routers>
      <AuthProvider>
        <Routes>
          <Route exact path="/" element={<LoginPage />} />
          <Route exact path="/home" element={
          <Private>
            <Home />
          </Private>} />
        </Routes>
      </AuthProvider>
    </Routers>
  )
}

export default AppRoutes;
