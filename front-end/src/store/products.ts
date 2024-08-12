import { useReducer } from "react";
import { toast } from "react-toastify";
import { getUserFromLocalStorage } from "../helpers/checkExpiration";
import { Action, Category, initialState, Order, Product, State, UserResponse } from "./interfaces";

const ActionTypes = {
  ADD_TO_CART: "ADD_TO_CART",
  GET_PRODUCTS: "GET_PRODUCTS",
  REMOVE_FROM_CART: "REMOVE_FROM_CART",
  CLEAR_CART: "CLEAR_CART",
  ADD_QUANTITY: "ADD_QUANTITY",
  REDUCE_QUANTITY: "REDUCE_QUANTITY",
} as const;

const baseUrl = process.env.REACT_APP_BASE_URL;

const calculateCartTotals = (cart: Product[]) => {
  const cartTotal = cart.reduce((total, item) => total + item.price * (item.quantity || 1), 0);
  const cartQuantity = cart.reduce((total, item) => total + (item.quantity || 0), 0);
  return { cartTotal, cartQuantity };
};

const reducer = (state: State, action: Action): State => {
  console.log("state", state);
  console.log("action", action);

  switch (action.type) {
    case ActionTypes.GET_PRODUCTS:
      const { cartTotal, cartQuantity } = calculateCartTotals(action.backed_up_cart || []);
      const categories = action.categories || [];
      return {
        ...state,
        categories,
        cart: action.backed_up_cart || [],
        cartQuantity,
        cartTotal,
      };

    case ActionTypes.ADD_TO_CART: {
      const categoryContainingProduct = state.categories.find(category =>
        category.productDtos.some(product => product.id === action.product)
      );
      if (!categoryContainingProduct) return state;
      const productToAdd = categoryContainingProduct.productDtos.find(product => product.id === action.product);
      if (!productToAdd) return state;
      productToAdd.addedToCart = true;
      productToAdd.quantity = 1;

      return {
        ...state,
        cart: [...state.cart, productToAdd],
        cartQuantity: state.cartQuantity + 1,
        cartTotal: state.cartTotal + productToAdd.price,
      };
    }

    case ActionTypes.REMOVE_FROM_CART: {
      const categoryContainingProduct = state.categories.find(category =>
        category.productDtos.some(product => product.id === action.product)
      );
      if (!categoryContainingProduct) return state;
      const productToRemove = categoryContainingProduct.productDtos.find(product => product.id === action.product);
      if (!productToRemove) return state;
      productToRemove.addedToCart = false;
      const newCart = state.cart.filter(product => product.id !== action.product);
      const { cartTotal, cartQuantity } = calculateCartTotals(newCart);
      return {
        ...state,
        categories: state.categories.map(category => ({
          ...category,
          products: category.productDtos.map(p =>
            p.id === productToRemove.id ? { ...p, addedToCart: false } : p
          ),
        })),
        cart: newCart,
        cartQuantity,
        cartTotal,
      };
    }

    case ActionTypes.ADD_QUANTITY: {
      const productToAddQuantity = state.cart.find(product => product.id === action.product);
      if (!productToAddQuantity) return state;

      productToAddQuantity.quantity = (productToAddQuantity.quantity || 0) + 1;

      return {
        ...state,
        cartTotal: parseFloat((state.cartTotal + productToAddQuantity.price).toFixed(2)),
      };
    }

    case ActionTypes.REDUCE_QUANTITY: {
      const productToReduceQuantity = state.cart.find(product => product.id === action.product);
      if (!productToReduceQuantity || (productToReduceQuantity.quantity || 0) <= 1) return state;

      productToReduceQuantity.quantity! -= 1;

      return {
        ...state,
        cartTotal: parseFloat((state.cartTotal - productToReduceQuantity.price).toFixed(2)),
      };
    }

    case ActionTypes.CLEAR_CART:
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

  const addToCart = (product: string) => dispatch({ type: ActionTypes.ADD_TO_CART, product });

  const removeFromCart = (product: string) => dispatch({ type: ActionTypes.REMOVE_FROM_CART, product });

  const clearCart = () => dispatch({ type: ActionTypes.CLEAR_CART });

  const handleData = async () => {
    try {
      const response = await fetch(`${baseUrl}/categories`);
      const apiData: Category[] = await response.json();

      const transformedData: Category[] = apiData.map((item: any, index: number) => ({
        ...item,
        productDtos: item.productDtos.map((product: Product) => (
          {
            ...product,
            rating: 5,
            times_bought: 0,
            product_image: product.imageUrl,
            addedToCart: false,
          }
        )
        )
      }));

      const cart: Product[] = [];
      dispatch({ type: ActionTypes.GET_PRODUCTS, categories: transformedData, backed_up_cart: cart });
    } catch (err) {
      toast.error("There was a problem processing the products data");
    }
  };

  const getProducts = () => handleData();

  const addQuantity = (product: string) => dispatch({ type: ActionTypes.ADD_QUANTITY, product });

  const reduceQuantity = (product: string) => dispatch({ type: ActionTypes.REDUCE_QUANTITY, product });

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

    try {
      const user: UserResponse = getUserFromLocalStorage();

      const order = {
        totalAmount: payload.cost_after_delivery_rate,
        userId: payload.user_id,
        orderItem: payload.items.map(item => ({
          productId: item.id,
          quantity: item.quantity,
          price: item.price,
        })),
      };

      const response = await fetch(`${baseUrl}/orders`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${user.jwtToken}`
        },
        mode: "cors",
        credentials: "include",
        body: JSON.stringify(
          order
        ),
      });

      const data = await response.json();
      if (data.error) {
        toast.error("You must be logged in to place an order");
        return { showRegisterLogin: true };
      }

      toast.success(data.message);
      clearCart();
      return true;
    } catch (error) {
      toast.error("There was an error confirming the order");
      return false;
    }
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
