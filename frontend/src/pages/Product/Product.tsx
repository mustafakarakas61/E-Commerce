import React, { FC, ReactElement, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { Form } from "antd";
import SockJS from "sockjs-client";
import { CompatClient, Stomp } from "@stomp/stompjs";

import ContentWrapper from "../../components/ContentWrapper/ContentWrapper";
import {
    selectIsProductLoaded,
    selectIsProductLoading,
    selectProduct,
    selectProductError,
    selectProductErrorMessage,
    selectReviews
} from "../../redux-toolkit/product/product-selector";
import { selectIsReviewAdded, selectReviewErrors } from "../../redux-toolkit/user/user-selector";
import { fetchProduct, fetchReviewsByProductId } from "../../redux-toolkit/product/product-thunks";
import { resetInputForm } from "../../redux-toolkit/user/user-slice";
import { WEBSOCKET_URL } from "../../constants/urlConstants";
import { resetProductState, setReview } from "../../redux-toolkit/product/product-slice";
import Spinner from "../../components/Spinner/Spinner";
import ErrorMessage from "./ErrorMessage/ErrorMessage";
import ProductInfo from "./ProductInfo/ProductInfo";
import ProductReviews from "./ProductReviews/ProductReviews";
import { addReviewToProduct } from "../../redux-toolkit/user/user-thunks";
import { useCart } from "../../hooks/useCart";
import "./Product.css";

let stompClient: CompatClient | null = null;

export interface ReviewData {
    author: string;
    message: string;
    rating: number;
}

const Product: FC = (): ReactElement => {
    const dispatch = useDispatch();
    const [form] = Form.useForm();
    const params = useParams<{ id: string }>();
    const product = useSelector(selectProduct);
    const reviews = useSelector(selectReviews);
    const isProductLoading = useSelector(selectIsProductLoading);
    const isProductLoaded = useSelector(selectIsProductLoaded);
    const isProductError = useSelector(selectProductError);
    const errorMessage = useSelector(selectProductErrorMessage);
    const reviewErrors = useSelector(selectReviewErrors);
    const isReviewAdded = useSelector(selectIsReviewAdded);
    const { addToCart } = useCart(product?.id!);

    useEffect(() => {
        // GraphQL example
        // dispatch(fetchProductByQuery(params.id));
        dispatch(fetchProduct(params.id));
        dispatch(resetInputForm());
        window.scrollTo(0, 0);
        const socket = new SockJS(WEBSOCKET_URL);
        stompClient = Stomp.over(socket);
        stompClient.connect({}, () => {
            stompClient?.subscribe("/topic/reviews/" + params.id, (response: any) => {
                dispatch(setReview(JSON.parse(response.body)));
            });
        });

        return () => {
            stompClient?.disconnect();
            dispatch(resetProductState());
        };
    }, []);

    useEffect(() => {
        if (isProductLoaded) {
            dispatch(fetchReviewsByProductId(params.id));
        }
    }, [isProductLoaded]);

    useEffect(() => {
        form.resetFields();
    }, [isReviewAdded]);

    const addReview = (data: ReviewData): void => {
        dispatch(addReviewToProduct({ productId: params.id, ...data }));
    };

    return (
        <ContentWrapper>
            {isProductLoading ? (
                <Spinner />
            ) : (
                <>
                    {isProductError ? (
                        <ErrorMessage errorMessage={errorMessage} />
                    ) : (
                        <>
                            <ProductInfo product={product} reviewsLength={reviews.length} addToCart={addToCart} />
                            <ProductReviews
                                reviews={reviews}
                                reviewErrors={reviewErrors}
                                addReview={addReview}
                                form={form}
                            />
                        </>
                    )}
                </>
            )}
        </ContentWrapper>
    );
};

export default Product;
