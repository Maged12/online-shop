import React, { useState } from "react";
import Card from "../../UI/card/Card";
import { Link } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { IProductsTable as Props } from "../../../interfaces/Itable";
import classes from "./EditProduct.module.scss";
import { Icon } from "@iconify/react";
import Button from "../../UI/button/Button";
import Input from "../../UI/input/Input";

const EditProduct: React.FC<{ product?: Props; }> = (props) => {
  const { t } = useTranslation();

  const [image, setImage] = useState<File | null>(null);

  const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>, id: string) => {
    if (e.target.files && e.target.files[0]) {
      setImage(e.target.files[0]);
      uploadImage(e.target.files[0], id);
    }
  };

  const uploadImage = async (imageFile: File, id: string) => {
    const formData = new FormData();
    formData.append("image", imageFile);

    try {
      const response = await fetch(`http://localhost:8081/api/products/update/${id}/image`, {
        method: 'POST',
        body: formData,
      });

      if (!response.ok) {
        throw new Error('Failed to upload image');
      }

      const result = await response.json();
      console.log('Image uploaded successfully:', result);
    } catch (error) {
      console.error('Error uploading image:', error);
    }
  };

  return (
    <div className={classes.edit__container}>
      <div className={classes.edit__left}>
        <Card>
          <div className={classes.img_wrapper}>
            <img
              className={classes.pic}
              src={image ? URL.createObjectURL(image) : props.product?.imageUrl}
              alt="productName pic"
            />
          </div>
          <div className={classes.productName__info}>
            <div>
              <div className={classes.title}>{t("proName")}</div>
              <div className={classes.value}>
                {t(`${props.product?.name}`)}
              </div>
            </div>
            <div>
              <div className={classes.title}>{t("category")}</div>
              <div className={classes.value}>
                {t(`${props.product?.categoryDto.name}`)}
              </div>
            </div>
            <div>
              <div className={classes.title}>{t("price")}</div>
              <div className={classes.value}>
                {t(`${props.product?.price}`)}
              </div>
            </div>
          </div>
        </Card>
      </div>

      <div className={classes.edit__right}>
        <Card>
          <div className={classes.productName__edit}>
            <h3 className={classes.subTitle}>
              <Icon icon="fluent:edit-16-regular" width="24" />
              {t("edit")}
            </h3>
            <div className={classes.img_wrapper}>
              <div className={classes.upload_icon}>
                <Icon icon="akar-icons:cloud-upload" />
              </div>
              <div className={classes.file_input_control}>
                <input
                  className={classes.file_input}
                  type="file"
                  id="pic"
                  name="pic"
                  accept="image/png, image/jpeg"
                  onChange={(e) => handleImageChange(e, props.product?.id.toString() ?? "")}
                />
              </div>
              <img
                className={classes.pic}
                src={image ? URL.createObjectURL(image) : props.product?.imageUrl}
                alt="productName pic"
              />
            </div>
            <form
              onSubmit={(e) => {
                e.preventDefault();
                // Additional form submission logic if needed
              }}
            >
              <Input
                id="proName"
                type="text"
                placeholder={props.product?.name}
              />
              <Input
                id="category"
                type="text"
                placeholder={props.product?.categoryDto.name}
              />
              <Input
                id="price"
                type="text"
                placeholder={props.product?.price}
              />
              <div className={classes.btn__wrapper}>
                <Button type="submit">{t("upload")}</Button>
                <Link to="/productNames">
                  <Button outline={true}>{t("cancel")}</Button>
                </Link>
              </div>
            </form>
          </div>
        </Card>
      </div>
    </div>
  );
};

export default EditProduct;
