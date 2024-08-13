import React, { useContext, useRef } from "react";
import LoginContext from "../../store/loginContext";
import langContextObj from "../../store/langContext";
import { images } from "../../constants";
import Input from "../UI/input/Input";
import Button from "../UI/button/Button";
import { useTranslation } from "react-i18next";
import classes from "./Login.module.scss";
import { useNavigate } from "react-router-dom";
import { useUser } from "../../store/UserContext";

function LoginBox() {
  const loginCtx = useContext(LoginContext);
  const { setUser } = useUser(); // Access setUser function from UserContext
  const langCtx = useContext(langContextObj);
  const userNameRef = useRef<HTMLInputElement>(null);
  const passwordRef = useRef<HTMLInputElement>(null);
  const errorMessageRef = useRef<HTMLSpanElement>(null);
  const navigate = useNavigate();
  const { t } = useTranslation();
  const baseUrl = process.env.REACT_APP_BASE_URL;

  async function loginHandler(e: React.FormEvent) {
    e.preventDefault();

    const userName = userNameRef.current?.value;
    const password = passwordRef.current?.value;

    if (userName && password) {
      const myHeaders = new Headers();
      myHeaders.append("Content-Type", "application/json");

      const raw = JSON.stringify({
        email: userName,
        password: password,
      });

      const requestOptions: RequestInit = {
        method: "POST",
        headers: myHeaders,
        body: raw,
        redirect: "follow",
      };

      try {
        const response = await fetch(`${baseUrl}/auth/login`, requestOptions);
        if (response.ok) {
          const result = await response.json();
          console.log(result.user);

          if (result.user.role == 'ADMIN') {
            setUser(result.user, result.jwtToken);
            loginCtx.toggleLogin();
            navigate("/");
          }
          else {
            throw new Error("Login failed");
          }
        } else {
          errorMessageRef.current?.setAttribute(
            "style",
            "display: inline-block;opacity: 1"
          );
        }
      } catch (error) {
        console.error("Error:", error);
      }
    } else {
      if (userNameRef.current) {
        userNameRef.current.focus();
      } else if (passwordRef.current) {
        passwordRef.current.focus();
      }
      errorMessageRef.current?.setAttribute(
        "style",
        "display: inline-block;opacity: 1"
      );
    }
  }

  return (
    <div
      className={`${classes.container} ${langCtx.lang === "fa" ? classes.rtl : ""}`}
    >
      <div className={classes.loginBox}>
        <div className={classes.logo}>
          <img src={images.logo} alt="digikala" />
        </div>
        <h2 className={classes.title}>{t("loginPage")}</h2>
        <form onSubmit={loginHandler}>
          <Input
            ref={userNameRef}
            type={"text"}
            id={"userName"}
            placeholder={"username"}
          />
          <Input
            ref={passwordRef}
            type={"password"}
            id={"pass"}
            placeholder={"password"}
          />
          <span ref={errorMessageRef} className={classes.errorMessage}>
            {t("errorMessage")}
          </span>
          <Button type="submit">{t("login")}</Button>
        </form>
      </div>

      <div className={classes.keyPic}>
        <img
          src={require("../../assets/images/Revenue-cuate.svg").default}
          alt="illustrator key"
        />
      </div>
    </div>
  );
}

export default LoginBox;
