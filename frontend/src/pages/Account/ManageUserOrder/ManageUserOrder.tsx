import React, { FC, ReactElement, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { Card, Col, Row, Table } from "antd";
import { InfoCircleOutlined, ShoppingOutlined } from "@ant-design/icons";

import {
    selectIsOrderLoaded,
    selectIsOrderLoading,
    selectOrder,
    selectOrderItems
} from "../../../redux-toolkit/order/order-selector";
import { fetchOrderById, fetchOrderItemsByOrderId } from "../../../redux-toolkit/order/order-thunks";
import { resetOrderState } from "../../../redux-toolkit/order/order-slice";
import ContentTitle from "../../../components/ContentTitle/ContentTitle";
import Spinner from "../../../components/Spinner/Spinner";
import AccountDataItem from "../../../components/AccountDataItem/AccountDataItem";
import { OrderItemResponse } from "../../../types/types";
import "./ManageUserOrder.css";

const ManageUserOrder: FC = (): ReactElement => {
    const dispatch = useDispatch();
    const params = useParams<{ id: string }>();
    const order = useSelector(selectOrder);
    const orderItems = useSelector(selectOrderItems);
    const isOrderLoading = useSelector(selectIsOrderLoading);
    const isOrderLoaded = useSelector(selectIsOrderLoaded);
    const { id, email, firstName, lastName, totalPrice, postIndex, phoneNumber, date, city, address } = order;

    useEffect(() => {
        dispatch(fetchOrderById(params.id));

        return () => {
            dispatch(resetOrderState());
        };
    }, []);

    useEffect(() => {
        if (isOrderLoaded) {
            dispatch(fetchOrderItemsByOrderId(params.id));
        }
    }, [isOrderLoaded]);

    return (
        <>
            {isOrderLoading ? (
                <Spinner />
            ) : (
                <>
                    <div style={{ textAlign: "center" }}>
                        <ContentTitle title={`Sipariş #${id}`} titleLevel={4} icon={<ShoppingOutlined />} />
                    </div>
                    <Row>
                        <Col span={24}>
                            <Card>
                                <Row gutter={32}>
                                    <Col span={12}>
                                        <InfoCircleOutlined className={"manage-user-icon"} />
                                        <ContentTitle title={"Müşteri Bilgisi"} titleLevel={5} />
                                        <AccountDataItem title={"Ad"} text={firstName} />
                                        <AccountDataItem title={"Soyad"} text={lastName} />
                                        <AccountDataItem title={"Şehir"} text={city} />
                                        <AccountDataItem title={"Adres"} text={address} />
                                        <AccountDataItem title={"Email"} text={email} />
                                        <AccountDataItem title={"Telefon"} text={phoneNumber} />
                                        <AccountDataItem title={"Posta kodu"} text={postIndex} />
                                    </Col>
                                    <Col span={12}>
                                        <InfoCircleOutlined className={"manage-user-icon"} />
                                        <ContentTitle title={"Sipariş bilgisi"} titleLevel={5} />
                                        <AccountDataItem title={"Sipariş id"} text={id} />
                                        <AccountDataItem title={"Tarih"} text={date} />
                                        <ContentTitle title={`Sipariş Özeti: ${totalPrice}.0₺`} titleLevel={4} />
                                    </Col>
                                </Row>
                                <Row style={{ marginTop: 16 }}>
                                    <Col span={24}>
                                        <Table
                                            rowKey={"id"}
                                            pagination={false}
                                            dataSource={orderItems}
                                            columns={[
                                                {
                                                    title: "Ürün id",
                                                    dataIndex: "id",
                                                    key: "id"
                                                },
                                                {
                                                    title: "Üretici",
                                                    dataIndex: "producer",
                                                    key: "producer",
                                                    render: (_, order: OrderItemResponse) => order.product.producer
                                                },
                                                {
                                                    title: "Ürün ismi",
                                                    dataIndex: "productTitle",
                                                    key: "productTitle",
                                                    render: (_, order: OrderItemResponse) => order.product.productTitle
                                                },
                                                {
                                                    title: "Adet",
                                                    dataIndex: "quantity",
                                                    key: "quantity"
                                                },
                                                {
                                                    title: "Fiyat",
                                                    dataIndex: "price",
                                                    key: "price",
                                                    render: (_, order: OrderItemResponse) => `${order.product.price}.0₺`
                                                },
                                                {
                                                    title: "Miktar",
                                                    dataIndex: "amount",
                                                    key: "amount",
                                                    render: (_, order: OrderItemResponse) => `${order.amount}.0 ₺`
                                                }
                                            ]}
                                        />
                                    </Col>
                                </Row>
                            </Card>
                        </Col>
                    </Row>
                </>
            )}
        </>
    );
};

export default ManageUserOrder;
