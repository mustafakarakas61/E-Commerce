import React, { FC, ReactElement, useEffect, useState } from "react";
import { Card, Col, InputNumber, Row, Typography } from "antd";

import { ProductResponse } from "../../../types/types";
import RemoveButton from "./RemoveButton";
import CartItemInfo from "./CartItemInfo";

type PropsType = {
    product: ProductResponse;
    productInCart: number;
    onChangeProductItemCount: (productId: number, inputValue: number) => void;
    deleteFromCart: (productId: number) => void;
};

const CartItem: FC<PropsType> = ({
    product,
    productInCart,
    onChangeProductItemCount,
    deleteFromCart
}): ReactElement => {
    const [productCount, setProductCount] = useState(1);

    useEffect(() => {
        setProductCount(productInCart);
    }, []);

    const handleProductsCount = (value: number | null): void => {
        setProductCount(value!);
        onChangeProductItemCount(product.id, value!);
    };

    return (
        <Card className={"cart-item"}>
            <Row gutter={16}>
                <CartItemInfo product={product} />
                <Col span={8}>
                    <Row gutter={8}>
                        <Col span={12}>
                            <InputNumber
                                min={1}
                                max={99}
                                value={productCount}
                                onChange={handleProductsCount}
                            />
                        </Col>
                        <Col span={12}>
                            <RemoveButton productId={product.id} deleteFromCart={deleteFromCart} />
                        </Col>
                    </Row>
                    <Row style={{ marginTop: 16 }}>
                        <Typography.Title level={4}>{product.price * productCount}₺</Typography.Title>
                    </Row>
                </Col>
            </Row>
        </Card>
    );
};

export default CartItem;
