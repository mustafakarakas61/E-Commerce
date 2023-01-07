import React, { FC, ReactElement } from "react";
import { Card, Col, Typography } from "antd";

import { ProductResponse } from "../../../types/types";
import "./OrderItem.css";

type PropsType = {
    product: ProductResponse;
    quantity?: number;
};

const OrderItem: FC<PropsType> = ({ product, quantity }): ReactElement => {
    return (
        <Col span={12}>
            <Card
                className={"menu-card"}
                cover={<img className={"menu-card-image"} alt={product.productTitle} src={product.filename} />}
            >
                <div className={"menu-content"}>
                    <Typography.Text strong>{product.producer}</Typography.Text>
                    <Typography.Text strong>{product.productTitle}</Typography.Text>
                    <Typography.Text strong>Fiyat: {product.price}₺</Typography.Text>
                    <Typography.Text strong>Adet: {quantity}</Typography.Text>
                </div>
            </Card>
        </Col>
    );
};

export default OrderItem;
