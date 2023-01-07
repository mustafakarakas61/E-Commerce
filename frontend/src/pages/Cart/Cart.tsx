import React, { FC, ReactElement, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { ShoppingCartOutlined, ShoppingOutlined } from "@ant-design/icons";
import { Button, Col, Row, Typography } from "antd";
import { Link } from "react-router-dom";

import ContentTitle from "../../components/ContentTitle/ContentTitle";
import ContentWrapper from "../../components/ContentWrapper/ContentWrapper";
import { selectCartItems, selectIsCartLoading } from "../../redux-toolkit/cart/cart-selector";
import { fetchCart } from "../../redux-toolkit/cart/cart-thunks";
import {
    calculateCartPrice,
    removeProductById,
    resetCartState,
    setCartItemsCount
} from "../../redux-toolkit/cart/cart-slice";
import CartItem from "./CartItem/CartItem";
import Spinner from "../../components/Spinner/Spinner";
import CartTotalPrice from "./CartTotalPrice";
import { ORDER } from "../../constants/routeConstants";
import "./Cart.css";

const Cart: FC = (): ReactElement => {
    const dispatch = useDispatch();
    const products = useSelector(selectCartItems);
    const isCartLoading = useSelector(selectIsCartLoading);
    const [productInCart, setProductInCart] = useState(() => new Map());

    useEffect(() => {
        window.scrollTo(0, 0);
        const productsFromLocalStorage: Map<number, number> = new Map(
            JSON.parse(localStorage.getItem("products") as string)
        );

        dispatch(fetchCart(Array.from(productsFromLocalStorage.keys())));
        productsFromLocalStorage.forEach((value: number, key: number) => {
            setProductInCart(productInCart.set(key, value));
        });

        return () => {
            dispatch(resetCartState());
        };
    }, []);

    const deleteFromCart = (productId: number): void => {
        productInCart.delete(productId);

        if (productInCart.size === 0) {
            localStorage.removeItem("products");
            setProductInCart(new Map());
        } else {
            localStorage.setItem("products", JSON.stringify(Array.from(productInCart.entries())));
        }
        dispatch(removeProductById(productId));
        dispatch(setCartItemsCount(productInCart.size));
    };

    const onChangeProductItemCount = (productId: number, inputValue: number): void => {
        setProducts(productId, inputValue);
        dispatch(calculateCartPrice(products));
    };

    const setProducts = (productId: number, productCount: number): void => {
        setProductInCart(productInCart.set(productId, productCount));
        localStorage.setItem("products", JSON.stringify(Array.from(productInCart.entries())));
    };

    return (
        <ContentWrapper>
            {isCartLoading ? (
                <Spinner />
            ) : (
                <>
                    <div style={{ textAlign: "center" }}>
                        <ContentTitle icon={<ShoppingCartOutlined />} title={"Sepet"} />
                    </div>
                    <Row gutter={32}>
                        {products.length === 0 ? (
                            <Col span={24}>
                                <Typography.Title level={3} style={{ textAlign: "center" }}>
                                    Sepetiniz boş &#128542;
                                </Typography.Title>
                            </Col>
                        ) : (
                            <>
                                <Col span={16}>
                                    {products.map((product) => (
                                        <CartItem
                                            key={product.id}
                                            product={product}
                                            productInCart={productInCart.get(product.id)}
                                            onChangeProductItemCount={onChangeProductItemCount}
                                            deleteFromCart={deleteFromCart}
                                        />
                                    ))}
                                </Col>
                                <Col span={8}>
                                    <Row>
                                        <Col span={12}>
                                            <CartTotalPrice />
                                        </Col>
                                        <Col span={12}>
                                            <Link to={ORDER}>
                                                <Button type="primary" icon={<ShoppingOutlined />} size="large">
                                                    Onayla
                                                </Button>
                                            </Link>
                                        </Col>
                                    </Row>
                                </Col>
                            </>
                        )}
                    </Row>
                </>
            )}
        </ContentWrapper>
    );
};

export default Cart;
