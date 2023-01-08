import React, { FC, ReactElement } from "react";
import { Button, Col, Divider, Rate, Row, Space, Typography } from "antd";
import { ShoppingCartOutlined } from "@ant-design/icons";

import Description from "./Description/Description";
import { FullProductResponse } from "../../../types/types";

type PropsType = {
    product?: Partial<FullProductResponse>;
    reviewsLength: number;
    addToCart: () => void;
};

const ProductInfo: FC<PropsType> = ({ product, reviewsLength, addToCart }): ReactElement => {
    return (
        <Row>
            <Col span={12} className={"product-image-wrapper"}>
                <img src={product?.filename} alt={product?.productTitle} className={"product-image"} />
            </Col>
            <Col span={12}>
                <Row className={"product-header"}>
                    <Col>
                        <Typography.Title level={3}>{product?.productTitle}</Typography.Title>
                        <Typography.Title level={4}>{product?.producer}</Typography.Title>
                        <Typography.Text>{product?.type}</Typography.Text>
                    </Col>
                </Row>
                <Row>
                    <Col className={"product-rate"} span={8}>
                        <Rate allowHalf disabled value={product?.productRating} />
                        <Typography.Text>{reviewsLength} yorum</Typography.Text>
                    </Col>
                </Row>
                <Row>
                    <Typography.Text type="success">Stokta var</Typography.Text>
                </Row>
                <Row style={{ marginTop: 16 }}>
                    <Col span={5}>
                        <Space align={"baseline"}>
                            <Typography.Text>{product?.price}.00₺</Typography.Text>
                        </Space>
                    </Col>
                    <Col span={4}>
                        <Button icon={<ShoppingCartOutlined />} onClick={addToCart}>
                            Sepete ekle
                        </Button>
                    </Col>
                </Row>
                <Divider />
                <Row>
                    <Col span={8}>
                        <Description title={"Ürün tipi:"} />
                        <Description title={"Üretim yılı:"} />
                        <Description title={"Şehir:"} />
                        <Description title={"Renkler:"} />
                    </Col>
                    <Col span={16}>
                        <Description title={product?.productType} />
                        <Description title={product?.year} />
                        <Description title={product?.city} />
                        <Description title={product?.colors} />
                    </Col>
                </Row>
            </Col>
        </Row>
    );
};

export default ProductInfo;
