import "./Product.css";
import placeholder from "../../../../assets/images/img_placeholder.png";
import { FaStar } from "react-icons/fa";
import { toast } from "react-toastify";
import { useGlobalContext } from "../../../GlobalContext/GlobalContext";

const Product = ({ product }) => {
  let { store } = useGlobalContext();
  let stars = [];
  for (let i = 0; i < product?.rating; i++) {
    stars.push(<FaStar key={i} />);
  }
  const isInCart = product?.addedToCart;
  return (
    <div className="product-container">
      <div className="image">
        <img
          src={product?.product_image || placeholder}
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
          {isInCart == false ? (
            <button
              className="add-to-cart"
              onClick={() => {
                if (store.state.cartQuantity > 10) {
                  toast.warning("You can only add 10 items to cart");
                  return;
                }
                store.addToCart(product?._id);
                toast.success(`${product?.name} added to cart`);
              }}
            >
              Add to Cart
            </button>
          ) : (
            <button
              className="add-to-cart"
              onClick={() => {
                store.removeFromCart(product?._id);
                toast.error(`${product?.name} removed from cart`);

              }}
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
