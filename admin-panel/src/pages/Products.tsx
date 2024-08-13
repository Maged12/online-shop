import React, { useState, useRef, useEffect } from "react";
import { useTranslation } from "react-i18next";
import useFetch from "../hook/useFetch";
import CustomTable from "../components/tables/customTable/CustomTable";
import { IProductsTable } from "../interfaces/Itable";
import { productsHeader } from "../constants/tables";
import LoadingSpinner from "../components/UI/loadingSpinner/LoadingSpinner";
import Button from "../components/UI/button/Button";
import Dropdown from "../components/UI/dropdown/Dropdown";

const url =
  "http://localhost:8081/api/products";

const dropdownOptions = [
  { label: "all", value: "all" },
  { label: "digital", value: "digital" },
  { label: "clothing", value: "clothing" },
  { label: "beauty", value: "beauty" },
];
function Products() {
  const { t } = useTranslation();
  const [selected, setSelected] = useState(dropdownOptions[0].value);
  const { data, error, status } = useFetch<IProductsTable[]>(url);
  const products = data;
  let productsTable;
  let tableData: IProductsTable[] | undefined;

  function selectedChangeHandler(e: React.ChangeEvent<HTMLSelectElement>) {
    setSelected(() => e.target.value);
  }

  if (status === "loading") {
    productsTable = <LoadingSpinner />;
  }

  if (error) {
    //if fetch has error:
    //select data from local file ("../constants/tables.ts")
    switch (selected) {
      case "digital":
        tableData = products?.filter((item) => item.categoryDto.name === selected);
        break;
      case "clothing":
        tableData = products?.filter((item) => item.categoryDto.name === selected);
        break;
      case "beauty":
        tableData = products?.filter((item) => item.categoryDto.name === selected);
        break;
      default:
        tableData = products;
    }

    productsTable = (
      <CustomTable headData={productsHeader} bodyData={tableData!} limit={10} />
    );
  }

  if (status === "fetched" && data) {
    switch (selected) {
      case "digital":
        tableData = data?.filter((item) => item.categoryDto.name === selected);
        break;
      case "clothing":
        tableData = data?.filter((item) => item.categoryDto.name === selected);
        break;
      case "beauty":
        tableData = data?.filter((item) => item.categoryDto.name === selected);
        break;
      default:
        tableData = data;
    }

    productsTable = (
      <CustomTable
        selectedCategory={selected}
        headData={productsHeader}
        bodyData={tableData}
        limit={10}
      />
    );
  }

  return (
    <section>
      <div style={{
        display: "flex",
        alignItems: "center",
        justifyContent: "space-between",
        lineHeight: "1rem",
        margin: "2rem"
      }}>
        <h2 style={{ fontWeight: 700 }}>
          {t("products")}
        </h2>
        <Button onClick={() => window.location.reload()}>Add Product </Button>
      </div>
      {/* <Dropdown
        dropdownData={dropdownOptions}
        onChange={selectedChangeHandler}></Dropdown> */}
      {productsTable}
    </section >
  );
  //window.location.reload()
}


export default Products;
