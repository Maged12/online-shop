import { useState } from "react";
import { FaCaretUp } from "react-icons/fa";
import { useGlobalContext } from "../../GlobalContext/GlobalContext";
import "./DeliveryItem.css";

const DeliveryItem = ({ order }) => {
  const [expanded, setExpanded] = useState(false);
  const date = new Date(order.orderDate); // Use orderDate from order
  const currentDate = new Date();
  const formattedDate = date.toLocaleDateString("en-US", {
    month: "long",
    day: "numeric",
    year: "numeric",
  });

  const numberOfDays = () => {

    if (currentDate.getTime() > (date.getTime() + 1000 * 3600 * 24 * 2)) {
      return "0";
    }
    return Math.ceil(((date.getTime() + 1000 * 3600 * 24 * 2) - currentDate.getTime()) / (1000 * 3600 * 24));
  };

  const toggleExpanded = () => {
    setExpanded(!expanded);
  };

  const checkFlair = (status) => {
    if (status === "Pending") {
      return "flair danger-flair";
    } else if (status === "Accepted") {
      return "flair warning-flair";
    } else {
      return "flair success-flair";
    }
  };

  const { modal, orders } = useGlobalContext();

  const handleOpenCancelModal = (orderId) => {
    modal.openCancelModal();
    orders.setOrderToBeCanceled(orderId);
  };

  return (
    <div className="sub-container delivery-item-container">
      <div className="delivery-overview">
        <div className="delivery-summary">
          <div className="delivery-order-number">
            <h2 className="delivery-item-title order-main" title={order.id}>
              Order: <span>#</span>{order.id}
            </h2>
            <div className="delivery-items">
              <h5>Item Count: {order.orderItems.length}</h5>
              <h5>Total Cost: ${order.totalAmount}</h5>
              {/* Assuming delivery_type should be added to your order object */}
              <h5>Delivery Type: {order.delivery_type || "Standard"}</h5>
              <h6>Total cost includes delivery fee</h6>
            </div>
          </div>
          <div className="delivery-progress">
            <h3 className="delivery-item-title">Complete</h3>
            <h4>
              {order.status}{" "}
              <span className={checkFlair(order.status)}>
                {order.status}
              </span>
            </h4>
            <progress
              className="progress-bar"
              value={order.status === "Pending" ? 0 : order.status === "Accepted" ? 50 : 100}
              max="100"
            ></progress>
          </div>
          <div className="delivery-date">
            <h3 className="delivery-item-title">Expected Completion</h3>
            {(order.status !== "Delivered" &&
              order.status !== "Cancelled" && <h4>Order Create Date :{formattedDate}</h4>) ||
              (order.status === "Delivered" && (
                <h4 className="is-delivered">Delivered</h4>
              )) ||
              (order.status === "Cancelled" && (
                <h4 className="is-cancelled">Cancelled</h4>
              ))}

            {(order.status !== "Delivered" &&
              order.status !== "Cancelled" && (
                <h4>Expected Delivery Days: {numberOfDays()} day(s)</h4>
              )) ||
              ""}
          </div>
        </div>
        <div
          className={expanded ? "fully-expanded isExpanded" : "fully-expanded"}
        >
          <div className="products-in-delivery">
            <h3>Products in Delivery</h3>
            <div className="delivery-products">
              {order.orderItems.map((item) => {
                const { product } = item;
                return (
                  <div className="delivery-products-item" key={item.id}>
                    <img src={product.imageUrl} alt={product.name} width="50" />
                    <h5>Product Name: {product.name}</h5>
                    <h5>Description: {product.description}</h5>
                    <h5>Price: ${item.price}</h5>
                    <h5>Quantity: {item.quantity}</h5>
                  </div>
                );
              })}
            </div>
          </div>
          {order.status !== "Delivered" && order.status !== "Cancelled" && (
            <div className="danger-zone">
              <h3 className="danger-zone-text">Danger Zone</h3>
              <div className="danger-zone-buttons">
                <button
                  className="btn-rounded danger-zone-button"
                  onClick={() => {
                    handleOpenCancelModal(order.id);
                  }}
                >
                  Cancel Order
                </button>
                <button
                  className="btn-rounded danger-zone-button report-issue"
                  onClick={() => {
                    window.location.href = `mailto:www.minisylar3@gmail.com?subject=Reporting Order #${order.id}`;
                  }}
                >
                  Report Issue
                </button>
              </div>
            </div>
          )}
        </div>
      </div>

      <div className="expand-collapse-delivery">
        <button onClick={toggleExpanded}>
          {expanded ? "Collapse" : "Expand"}
          <span>
            <FaCaretUp
              className={
                expanded ? "caret-delivery" : "caret-delivery caret-expanded"
              }
            ></FaCaretUp>
          </span>
        </button>
      </div>
    </div>
  );
};

export default DeliveryItem;
