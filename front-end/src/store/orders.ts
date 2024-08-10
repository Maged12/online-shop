import { useReducer, Dispatch } from "react";

interface State {
  orders: any[];
  order_to_be_canceled: string | null;
}

const initialState: State = {
  orders: [],
  order_to_be_canceled: null,
};

const actions = Object.freeze({
  GET_ORDERS: "GET_ORDERS" as const,
  GET_ORDER_TO_BE_CANCELED: "GET_ORDER_TO_BE_CANCELED" as const,
});

type Action =
  | { type: typeof actions.GET_ORDERS; orders: any[]; }
  | { type: typeof actions.GET_ORDER_TO_BE_CANCELED; order_id: string | null; };

const reducer = (state: State, action: Action): State => {
  switch (action.type) {
    case actions.GET_ORDERS:
      return { ...state, orders: action.orders };
    case actions.GET_ORDER_TO_BE_CANCELED:
      return { ...state, order_to_be_canceled: action.order_id };
    default:
      return state;
  }
};

const useOrders = () => {
  const [state, dispatch] = useReducer(reducer, initialState);

  const getOrders = async (user_id: string) => {
    const response = await fetch(
      `url/get-orders/${user_id}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
        mode: "cors",
        credentials: "include",
      }
    );

    const data = await response.json();
    if (data.error) {
      return data.error;
    }
    dispatch({ type: actions.GET_ORDERS, orders: data.orders });
    return data.orders;
  };

  const setOrderToBeCanceled = (order_id: string) => {
    dispatch({ type: actions.GET_ORDER_TO_BE_CANCELED, order_id });
  };

  const cancelOrder = async (order_id: string) => {
    const response = await fetch(
      `url/cancel-order`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        mode: "cors",
        credentials: "include",
        body: JSON.stringify({ order_id }),
      }
    );

    const data = await response.json();

    if (data.error) {
      return data.error;
    }

    dispatch({ type: actions.GET_ORDER_TO_BE_CANCELED, order_id: null });
    getOrders(data.user_id);

    return data;
  };

  return { state, getOrders, setOrderToBeCanceled, cancelOrder };
};

export default useOrders;