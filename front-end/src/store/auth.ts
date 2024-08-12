import { useReducer } from "react";
import { toast } from "react-toastify";
import {
  setExpirationDate,
  getUserFromLocalStorage,
} from "../helpers/checkExpiration";

interface User {
  id: number;
  name: string;
  email: string;
  expirationDate?: Date;
}

interface State {
  user: User | null;
  jwtToken: string | null;
}

const initialState: State = {
  user: getUserFromLocalStorage()?.user || null,
  jwtToken: getUserFromLocalStorage()?.jwtToken || null,
};

const baseUrl = process.env.REACT_APP_BASE_URL;

const actions = Object.freeze({
  SET_USER: "SET_USER" as const,
  LOGOUT: "LOGOUT" as const,
});

type Action =
  | { type: typeof actions.SET_USER; user: User; jwtToken: string; }
  | { type: typeof actions.LOGOUT; };

const reducer = (state: State, action: Action): State => {
  switch (action.type) {
    case actions.SET_USER:
      return { ...state, user: action.user, jwtToken: action.jwtToken };
    case actions.LOGOUT:
      return { ...state, user: null, jwtToken: null };
    default:
      return state;
  }
};

const useAuth = () => {
  const [state, dispatch] = useReducer(reducer, initialState);

  const register = async (userInfo: { username: string; email: string; password: string; }) => {
    try {
      const response = await fetch(`${baseUrl}/auth/register`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        mode: "cors",
        credentials: "include",
        body: JSON.stringify(userInfo),
      });

      const data = await response.json();
      if (data.error) {
        toast.error(data.error);
      } else if (data.user) {
        data.user.expirationDate = setExpirationDate(7);
        localStorage.setItem("user", JSON.stringify({ user: data.user, jwtToken: data.jwtToken }));
        dispatch({ type: actions.SET_USER, user: data.user, jwtToken: data.jwtToken });
        toast.success("Registration successful");
      }
    } catch (error) {
      toast.error("There was a problem registering, try again");
    }
  };

  const login = async (userInfo: { email: string; password: string; }) => {
    try {
      const response = await fetch(`${baseUrl}/auth/login`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        mode: "cors",
        credentials: "include",
        body: JSON.stringify(userInfo),
      });

      const data = await response.json();
      if (data.error) {
        toast.error(data.error);
      } else if (data.user) {
        data.user.expirationDate = setExpirationDate(7);
        localStorage.setItem("user", JSON.stringify({ user: data.user, jwtToken: data.jwtToken }));
        dispatch({ type: actions.SET_USER, user: data.user, jwtToken: data.jwtToken });
        toast.success("Login successful");
      }
    } catch (error) {
      toast.error("There was a problem logging in, try again");
    }
  };

  const logout = async () => {
    localStorage.removeItem("user");
    dispatch({ type: actions.LOGOUT });
  };

  return { state, register, login, logout };
};

export default useAuth;
