import { FaStar } from "react-icons/fa";
import { toast } from "react-toastify";
import placeholder from "../../../../assets/images/img_placeholder.png";
import { useGlobalContext } from "../../../GlobalContext/GlobalContext";
import "./Product.css";

const Product = ({ product }) => {
  let { store, auth, modal } = useGlobalContext();
  let stars = [];
  for (let i = 0; i < product?.rating; i++) {
    stars.push(<FaStar key={i} />);
  }
  const isInCart = product?.addedToCart;

  const handleAddToCart = () => {
    if (store.state.cartQuantity > 10) {
      toast.warning("You can only add 10 items to cart");
      return;
    }
    store.addToCart(product?.id);
    toast.success(`${product?.name} added to cart`);
  };

  const handleRemoveFromCart = () => {
    store.removeFromCart(product?.id);
    toast.error(`${product?.name} removed from cart`);
  };

  return (
    <div className="product-container">
      <div className="image">
        <img
          src={product?.imageUrl || placeholder}
          alt="Product Image"
          width={"100%"}
        />
      </div>
      <div className="product-details">
        <div className="name-add-to-cart"></div>
        <div className="price">
          <div className="name-price-product">
            <h4>{product?.name}</h4>
            <h5 style={{ fontSize: '1rem' }}>
              $ <span className="actual-product-price">{product?.price}</span>
            </h5>
          </div>
          <h5>{product?.description}</h5>
          <div className="star-rating">
            <div className="star">{stars}</div>
            <span>({parseInt(Math.random() * 100)} Reviews)</span>
          </div>
        </div>
        <div>
          {isInCart === false ? (
            <button
              className="add-to-cart"
              onClick={handleAddToCart}
            >
              Add to Cart
            </button>
          ) : (
            <button
              className="add-to-cart"
              onClick={handleRemoveFromCart}
            >
              Remove from cart
            </button>
          )}
        </div>
      </div>
      <div className="heart"></div>
    </div>
  );
};

export default Product;
