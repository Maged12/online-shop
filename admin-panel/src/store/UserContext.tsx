import React, { createContext, useState, useEffect, useContext } from 'react';

interface User {
    id: number;
    name: string;
    email: string;
    role: string;
}

interface UserContextType {
    user: User | null;
    jwtToken: string | null;
    setUser: (user: User, token: string) => void;
    logout: () => void;
}

const UserContext = createContext<UserContextType | undefined>(undefined);

export const UserProvider: React.FC<{ children: React.ReactNode; }> = ({ children }) => {
    const [user, setUserState] = useState<User | null>(null);
    const [jwtToken, setJwtToken] = useState<string | null>(null);

    const setUser = (user: User, token: string) => {
        setUserState(user);
        setJwtToken(token);
        localStorage.setItem('jwtToken', token);
        localStorage.setItem('user', JSON.stringify(user));
    };

    const logout = () => {
        setUserState(null);
        setJwtToken(null);
        localStorage.removeItem('jwtToken');
        localStorage.removeItem('user');
    };

    useEffect(() => {
        const savedToken = localStorage.getItem('jwtToken');
        const savedUser = localStorage.getItem('user');

        if (savedToken && savedUser) {
            setJwtToken(savedToken);
            setUserState(JSON.parse(savedUser));
        }
    }, []);

    return (
        <UserContext.Provider value={{ user, jwtToken, setUser, logout }}>
            {children}
        </UserContext.Provider>
    );
};

export const useUser = (): UserContextType => {
    const context = useContext(UserContext);
    if (context === undefined) {
        throw new Error('useUser must be used within a UserProvider');
    }
    return context;
};
