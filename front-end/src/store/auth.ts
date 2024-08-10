import { useReducer } from "react";
import { toast } from "react-toastify";
import {
  setExpirationDate,
  getUserFromLocalStorage,
} from "../helpers/checkExpiration";

interface User {
  expirationDate?: Date;
  [key: string]: any;
}

interface State {
  user: User | null;
}

const initialState: State = {
  user: getUserFromLocalStorage() || null,
};

const actions = Object.freeze({
  SET_USER: "SET_USER" as const,
  LOGOUT: "LOGOUT" as const,
});

type Action =
  | { type: typeof actions.SET_USER; user: User; }
  | { type: typeof actions.LOGOUT; };

const reducer = (state: State, action: Action): State => {
  switch (action.type) {
    case actions.SET_USER:
      return { ...state, user: action.user };
    case actions.LOGOUT:
      return { ...state, user: null };
    default:
      return state;
  }
};

const useAuth = () => {
  const [state, dispatch] = useReducer(reducer, initialState);

  const register = async (userInfo: User) => {
    try {
      const response = await fetch(`url/register`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        mode: "cors",
        credentials: "include",
        body: JSON.stringify(userInfo),
      });

      const user = await response.json();
      if (user.error) {
        toast.error(user.error);
      }
      if (user.user) {
        dispatch({ type: actions.SET_USER, user: user.user });
        user.user.expirationDate = setExpirationDate(7);
        localStorage.setItem("user", JSON.stringify(user.user));
        toast.success("Registration successful");
        // login user
      }
    } catch (error) {
      toast.error("There was a problem registering, try again");
    }
  };

  const login = async (userInfo: User) => {
    try {
      const response = await fetch(`url/login`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        mode: "cors",
        credentials: "include",
        body: JSON.stringify(userInfo),
      });
      const user = await response.json();
      if (user.error) {
        toast.error(user.error);
      }
      if (user.user) {
        dispatch({ type: actions.SET_USER, user: user.user });
        user.user.expirationDate = setExpirationDate(7);
        localStorage.setItem("user", JSON.stringify(user.user));
        toast.success("Login successful");
      }
    } catch (error) {
      toast.error("There was a problem logging in, try again");
    }
  };

  const logout = async () => {
    await fetch(`url/logout`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      credentials: "include",
    });
    localStorage.removeItem("user");
    dispatch({ type: actions.LOGOUT });
  };

  return { state, register, login, logout };
};

export default useAuth;