import React, { FC, ReactElement } from "react";
import { Button, Card, Col, Rate, Typography } from "antd";
import { Link } from "react-router-dom";
import Meta from "antd/lib/card/Meta";
import { DeleteOutlined, EditOutlined, ShoppingCartOutlined } from "@ant-design/icons";

import { ProductResponse } from "../../types/types";
import { ACCOUNT_ADMIN_PRODUCTS, PRODUCT } from "../../constants/routeConstants";
import { useCart } from "../../hooks/useCart";
import "./ProductCard.css";

type PropsType = {
    product: ProductResponse;
    colSpan: number;
    edit?: boolean;
    onOpenDelete?: (product: ProductResponse) => void;
};

const ProductCard: FC<PropsType> = ({ product, colSpan, edit, onOpenDelete }): ReactElement => {
    const { addToCart } = useCart(product.id);

    const onClickAddToCart = (event: any) => {
        event.preventDefault();
        addToCart();
    };

    return (
        <Col span={colSpan}>
            <Link to={`${PRODUCT}/${product.id}`}>
                <Card
                    className={"product-card"}
                    cover={<img className={"producte-card-image"} alt={product.productTitle} src={product.filename} />}
                    hoverable
                    actions={
                        edit
                            ? [
                                  <Link to={`${ACCOUNT_ADMIN_PRODUCTS}/${product.id}`}>
                                      <Button icon={<EditOutlined />}>Edit</Button>
                                  </Link>,
                                  <Button icon={<DeleteOutlined />} onClick={() => onOpenDelete!(product)} danger>
                                      Delete
                                  </Button>
                              ]
                            : [
                                  <Button icon={<ShoppingCartOutlined />} onClick={onClickAddToCart}>
                                      Add to cart
                                  </Button>
                              ]
                    }
                >
                    <div className={"product-card-rate"}>
                        <Rate defaultValue={product.productRating === 0 ? 5 : product.productRating} disabled />
                        <Typography.Text>{product.reviewsCount} reviews</Typography.Text>
                    </div>
                    <Meta title={product.productTitle} description={product.producer} style={{ textAlign: "center" }} />
                    <Typography.Text className={"product-card-price"}>{product.price}.00₺</Typography.Text>
                </Card>
            </Link>
        </Col>
    );
};

export default ProductCard;
