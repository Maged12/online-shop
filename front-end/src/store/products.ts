import { useReducer } from "react";

import { toast } from "react-toastify";
import { Action, data, initialState, Order, Product, State } from "./interfaces";





const actions = Object.freeze({
  ADD_TO_CART: "ADD_TO_CART",
  GET_PRODUCTS: "GET_PRODUCTS",
  REMOVE_FROM_CART: "REMOVE_FROM_CART",
  CLEAR_CART: "CLEAR_CART",
  ADD_QUANTITY: "ADD_QUANTITY",
  REDUCE_QUANTITY: "REDUCE_QUANTITY",
  PREFILL_CART: "PREFILL_CART",
});

const reducer = (state: State, action: Action): State => {
  console.log("state", state);
  console.log("action", action);

  switch (action.type) {
    case actions.GET_PRODUCTS:
      if (action.backed_up_cart?.length === 0) {
        return { ...state, products: action.products || [] };
      }
      const cartTotal = action.backed_up_cart?.reduce(
        (acc, item) => acc + item.price,
        0
      ) || 0;
      const cartQuantity = action.backed_up_cart?.reduce(
        (acc, item) => acc + (item.quantity || 0),
        0
      ) || 0;

      const updatedProducts = (action.products || []).map((product) => {
        const cartItem = action.backed_up_cart?.find(
          (item) => item._id === product._id
        );
        return cartItem ? { ...cartItem, addedToCart: true } : product;
      });

      return {
        ...state,
        products: updatedProducts,
        cart: action.backed_up_cart || [],
        cartQuantity,
        cartTotal,
      };

    case actions.ADD_TO_CART:
      const productToAdd = state.products.find(
        (product) => product._id === action.product
      );
      if (productToAdd) {
        productToAdd.addedToCart = true;
        productToAdd.quantity = 1;
        // localforage.setItem("cartItems", [...state.cart, productToAdd]);

        return {
          ...state,
          cart: [...state.cart, productToAdd],
          cartQuantity: state.cartQuantity + 1,
          cartTotal: state.cartTotal + productToAdd.price,
        };
      }
      return state;

    case actions.REMOVE_FROM_CART:
      const productToRemove = state.products.find(
        (product) => product._id === action.product
      );
      if (productToRemove) {
        const newCart = state.cart.filter(
          (product) => product._id !== action.product
        );
        const updatedProduct = { ...productToRemove, addedToCart: false };
        // localforage.setItem("cartItems", newCart);

        const newCartTotal = newCart.reduce(
          (total, item) => total + item.price * (item.quantity || 1),
          0
        );

        return {
          ...state,
          products: state.products.map((p) =>
            p._id === productToRemove._id ? updatedProduct : p
          ),
          cart: newCart,
          cartQuantity: state.cartQuantity - 1,
          cartTotal: newCartTotal,
        };
      }
      return state;

    case actions.ADD_QUANTITY:
      const productToAddQuantity = state.cart.find(
        (product) => product._id === action.product
      );
      if (productToAddQuantity) {
        productToAddQuantity.quantity = (productToAddQuantity.quantity || 0) + 1;

        return {
          ...state,
          cartTotal: state.cartTotal + productToAddQuantity.price,
        };
      }
      return state;

    case actions.REDUCE_QUANTITY:
      const productToReduceQuantity = state.cart.find(
        (product) => product._id === action.product
      );
      if (productToReduceQuantity && productToReduceQuantity.quantity && productToReduceQuantity.quantity > 1) {
        productToReduceQuantity.quantity -= 1;

        return {
          ...state,
          cartTotal: state.cartTotal - productToReduceQuantity.price,
        };
      }
      return state;

    case actions.CLEAR_CART:
      // localforage.setItem("cartItems", []);

      return {
        ...state,
        cart: [],
        order: [],
        cartTotal: 0,
        cartQuantity: 0,
      };

    default:
      return state;
  }
};

const useStore = () => {
  const [state, dispatch] = useReducer(reducer, initialState);

  const addToCart = (product: string) => {
    dispatch({ type: actions.ADD_TO_CART, product });
  };

  const removeFromCart = (product: string) => {
    dispatch({ type: actions.REMOVE_FROM_CART, product });
  };

  const clearCart = () => {
    dispatch({ type: actions.CLEAR_CART });
  };

  async function handleData() {
    try {
      const response = await fetch('http://localhost:8080/api/products');
      const apiData = await response.json();

      // Duplicate each product 3 times
      const transformedData: Product[] = apiData.flatMap((item: any, index: number) => {
        // Creating an array with 3 identical products for each API item
        return Array.from({ length: 3 }, (_, i) => ({
          _id: `${index + 1}-${i + 1}`, // Generate a unique ID for each duplicate
          name: item.name,
          description: item.description,
          rating: 5, // Assuming a default rating of 5
          price: item.price,
          times_bought: 0, // Assuming no times bought initially
          __v: 0, // Assuming no versioning initially
          product_image: item.image || 'default_image_url.jpg', // Provide a default image if none
        }));
      });

      const modifiedData = transformedData.map((product) => ({
        ...product,
        addedToCart: false,
      }));

      // (await localforage.getItem<Product[]>("cartItems")) ||
      const cart: Product[] = [];

      dispatch({
        type: actions.GET_PRODUCTS,
        products: modifiedData,
        backed_up_cart: cart,
      });
    } catch (err) {
      toast.error("There was a problem processing the products data");
    }
  }


  const getProducts = () => {
    console.log("get products called");
    handleData();
  };

  const addQuantity = (product: string) => {
    dispatch({ type: actions.ADD_QUANTITY, product });
  };

  const reduceQuantity = (product: string) => {
    dispatch({ type: actions.REDUCE_QUANTITY, product });
  };

  const confirmOrder = async (order: Order) => {
    const payload = {
      items: state.cart,
      totalItemCount: state.cartQuantity,
      delivery_type: order.DeliveryType,
      delivery_type_cost: order.DeliveryTypeCost,
      cost_before_delivery_rate: state.cartTotal,
      cost_after_delivery_rate: order.costAfterDelieveryRate,
      promo_code: order.promo_code || "",
      contact_number: order.phoneNumber,
      user_id: order.user_id,
    };
    const response = await fetch(
      `url/place-order`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        mode: "cors",
        credentials: "include",
        body: JSON.stringify(payload),
      }
    );
    const data = await response.json();
    if (data.error) {
      toast.error("You must be logged in to place an order");
      return { showRegisterLogin: true };
    }
    toast.success(data.message);
    clearCart();
    return true;
  };

  return {
    state,
    addToCart,
    removeFromCart,
    clearCart,
    getProducts,
    addQuantity,
    reduceQuantity,
    confirmOrder,
  };
};

export default useStore;