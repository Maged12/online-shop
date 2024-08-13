import React from "react";
import { useTranslation } from "react-i18next";
import useFetch from "../hook/useFetch";
import CustomTable from "../components/tables/customTable/CustomTable";
import { IcustomersTable } from "../interfaces/Itable";
import { customers, customersHeader } from "../constants/tables";
import LoadingSpinner from "../components/UI/loadingSpinner/LoadingSpinner";
import Button from "../components/UI/button/Button";
const url =
  "https://admin-panel-79c71-default-rtdb.europe-west1.firebasedatabase.app/customers.json";
function Customers() {
  const { t } = useTranslation();
  const { data, error, status } = useFetch<IcustomersTable[]>(url);
  let customerTable;

  if (status === "loading") {
    customerTable = <LoadingSpinner />;
  }

  if (error) {
    customerTable = (
      <CustomTable limit={10} headData={customersHeader} bodyData={customers} />
    );
  }

  if (status === "fetched" && data) {
    customerTable = (
      <CustomTable limit={10} headData={customersHeader} bodyData={data} />
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
          {t("Admins")}
        </h2>
        <Button>Add Admin</Button>
      </div>
      {customerTable}
    </section>
  );
}

export default Customers;
