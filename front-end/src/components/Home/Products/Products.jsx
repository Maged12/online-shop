import { useGlobalContext } from "../../GlobalContext/GlobalContext";
import Product from "./Product/Product";
import { memo } from "react";
import Skeleton from "react-loading-skeleton";
import "./Products.css";

const Products = () => {
  const { store } = useGlobalContext();

  // Iterate through each category and sort products within each category
  const categories = store.state.products.map((category) => ({
    ...category,
    productDtos: category.productDtos.slice().sort((a, b) => a.name.localeCompare(b.name)),
  }));

  return (
    <div className="sub-container" id="products">
      {store.state.products.length > 0 ? (
        categories.map((category) => (
          <div key={category._id} className="category">
            <h2>{category.name || "UnKnown"}</h2>
            <div className="contains-product">
              {category.productDtos.map((product) => (
                <Product key={product._id} product={product} />
              ))}
            </div>
          </div>
        ))
      ) : (
        <div className="skeleton">
          <Skeleton height={250} />
        </div>
      )}
    </div>
  );
};

export default memo(Products);
