import HomeView from "./views/HomeView";
import ErrorView from "./views/ErrorView";
import CartView from "./views/CartView";
import DeliveryView from "./views/DeliveryView";
import { useEffect } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import Modal from "./components/Modals/Modal";
import CancelOrder from "./components/Modals/CancelOrder";
import "react-toastify/dist/ReactToastify.css";
import RequestCookie from "./components/CookieBanner/CookieBanner";
import { useGlobalContext } from "./components/GlobalContext/GlobalContext";
import NavBar from "./components/NavBar/NavBar";
import ShopFooter from "./components/Footer/ShopFooter";


function App() {
  console.log("App loaded");

  let { store } = useGlobalContext();
  let { modal } = useGlobalContext();
  useEffect(() => {
    if (store.state.categories.length > 0) return;
    store.getProducts();
  }, []);
  return (
    <div>
      <BrowserRouter>
        <header>
          <NavBar></NavBar>
        </header>
        <Routes>
          <Route path="/" element={<HomeView />} />
          <Route path="/cart" element={<CartView />} />
          <Route path="/delivery" element={<DeliveryView />} />
          <Route path="*" element={<ErrorView />} />
        </Routes>
        <footer>
          <ShopFooter></ShopFooter>
        </footer>
      </BrowserRouter>
      {modal.opened && (
        <Modal
          header={modal.isRegister ? "Create Account" : "Login"}
          submitAction="/"
          buttonText={modal.isRegister ? "Create Account" : "Login"}
          isRegister={modal.isRegister}
        />
      )}
      {modal.isCancelModal && <CancelOrder></CancelOrder>}
      <ToastContainer />
      {/* <RequestCookie /> */}
    </div>
  );
}

export default App;
