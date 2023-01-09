import React, { FC, ReactElement } from "react";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { Table } from "antd";
import { AsyncThunk } from "@reduxjs/toolkit";

import { HeaderResponse, OrderResponse } from "../../types/types";
import { ACCOUNT_USER_ORDERS } from "../../constants/routeConstants";
import { selectTotalElements } from "../../redux-toolkit/orders/orders-selector";
import { useTablePagination } from "../../hooks/useTablePagination";

type PropsType = {
    orders: Array<OrderResponse>;
    loading: boolean;
    fetchOrders: AsyncThunk<HeaderResponse<OrderResponse>, number, {}>;
};

const OrdersTable: FC<PropsType> = ({ orders, loading, fetchOrders }): ReactElement => {
    const totalElements = useSelector(selectTotalElements);
    const handleTableChange = useTablePagination<OrderResponse, number>(fetchOrders);

    return (
        <Table
            rowKey={"id"}
            onChange={handleTableChange}
            loading={loading}
            pagination={{
                total: totalElements,
                position: ["bottomRight", "topRight"]
            }}
            dataSource={orders}
            columns={[
                {
                    title: "Sipariş No",
                    dataIndex: "id",
                    key: "id"
                },
                {
                    title: "Tarih",
                    dataIndex: "date",
                    key: "date",
                    sorter: (a, b) => a.date.localeCompare(b.date)
                },
                {
                    title: "Alıcı",
                    dataIndex: "firstName",
                    key: "firstName",
                    render: (_, order: OrderResponse) => `${order.firstName} ${order.lastName}`
                },
                {
                    title: "Email",
                    dataIndex: "email",
                    key: "email"
                },
                {
                    title: "Toplam, ₺", //todo toplama bak
                    dataIndex: "totalPrice",
                    key: "totalPrice",
                    sorter: (a, b) => a.totalPrice - b.totalPrice
                },
                {
                    title: "Alışveriş",
                    dataIndex: "operations",
                    key: "operations",
                    render: (_, order: OrderResponse) => <Link to={`${ACCOUNT_USER_ORDERS}/${order.id}`}>Daha fazla</Link>
                }
            ]}
        />
    );
};

export default OrdersTable;
