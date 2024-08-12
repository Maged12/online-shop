import { FaShoppingCart } from "react-icons/fa";
import { Link, useNavigate } from "react-router-dom";
import "./Account.css";
import { useGlobalContext } from "../../GlobalContext/GlobalContext";

const Account = () => {
  let { auth, store, modal } = useGlobalContext();
  const cartTotal = store.state.cartQuantity;
  const navigate = useNavigate();

  const handleCartClick = (e) => {
    if (auth.state.user == null) {
      e.preventDefault();
      modal.openModal(false);
    } else {
      navigate("/cart");
    }
  };

  const handleLogout = () => {
    auth.logout();
  };

  return (
    <div className="account">
      <div className="cart">
        <Link to={"/cart"} className="contains-link-to-accounts" onClick={handleCartClick}>
          {auth.state.user == null ? (
            <span className="account-user">Guest</span>
          ) : (
            <span className="account-user">
              {auth.state.user.name}
            </span>
          )}
          <span className="account-details">
            <FaShoppingCart />
            <span className="items-in-cart">{cartTotal}</span>
          </span>
        </Link>
      </div>
      <div className="login">
        {auth.state.user == null ? (
          <button
            className="btn-rounded small-rounded"
            onClick={() => modal.openModal(false)}
          >
            Login
          </button>
        ) : (
          <button className="btn-rounded small-rounded" onClick={handleLogout}>
            Logout
          </button>
        )}
      </div>
    </div>
  );
};

export default Account;
