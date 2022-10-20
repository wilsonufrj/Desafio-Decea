import React, { useState,useEffect,createContext } from "react";
import { useNavigate } from "react-router-dom";

export const AuthContext = createContext();

export const AuthProvider = ({children}) => {

    const [user, setUser] = useState(null);
    const navigate = useNavigate();
    const [loading,setLoading] = useState(true)

    useEffect(()=>{
        const recoveredUser = localStorage.getItem('user')

        if(recoveredUser){
            setUser(JSON.parse(recoveredUser));
        }

        setLoading(false);
    },[])

    const login = (email, password) => {
        console.log('login auth', { email, password })

        const loggedUser = {
            id:"123",
            email
        }

        if(password === "secret"){
            localStorage.setItem("user",JSON.stringify(loggedUser));
            setUser(loggedUser)
            navigate("/home");
        }
    };

    const logout = () => {
        console.log('deslogado');
        localStorage.removeItem('user')
        setUser(null);
        navigate("/")
    }

    return (
        <AuthContext.Provider value={{ authenticated: !!user, loading,user, login,logout }}>
            {children}
        </AuthContext.Provider>
    )
}