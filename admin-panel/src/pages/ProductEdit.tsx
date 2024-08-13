import React from "react";
import { useTranslation } from "react-i18next";
import useFetch from "../hook/useFetch";
import { useParams } from "react-router-dom";
import EditProduct from "../components/edit/editProduct/EditProduct";
import { IProductsTable } from "../interfaces/Itable";
import LoadingSpinner from "../components/UI/loadingSpinner/LoadingSpinner";

const url = (id: string) => `http://localhost:8081/api/products/${id}`;
function ProductEdit() {
  const { t } = useTranslation();
  const params = useParams();
  let { productId } = params;



  let productEdit;

  const { data, error, status } = useFetch<IProductsTable>(
    url(productId ?? "1")
  );
  let productInfo: IProductsTable = data!;

  if (status === "loading") {
    productEdit = <LoadingSpinner />;
  }

  if (error) {
    productEdit = <EditProduct product={productInfo} />;
  }

  if (status === "fetched" && data) {
    productEdit = <EditProduct product={data} />;
  }

  return (
    <section>
      <h2 className="title">{t("editProduct")}</h2>
      {productEdit}
    </section>
  );
}

export default ProductEdit;
