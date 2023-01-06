import React, { FC, ReactElement, useEffect } from "react";
import { useDispatch } from "react-redux";

import { fetchProductsByIds } from "../../../redux-toolkit/products/products-thunks";
import { resetProductsState } from "../../../redux-toolkit/products/products-slice";
import "./ProductCardsSlider.css";

export const productsIds = [26, 43, 46, 106, 34, 76, 82, 85, 27, 39, 79, 86];//todo product id kontrol et

const ProductCardsSlider: FC = (): ReactElement => {
    const dispatch = useDispatch();

    useEffect(() => {
        // GraphQL example
        // dispatch(fetchProductsByIdsQuery(productsId));
        dispatch(fetchProductsByIds(productsIds));

        return () => {
            dispatch(resetProductsState());
        };
    }, []);

    return (
        <div className={"Bottom-padding"}>
        </div>
    );
};

export default ProductCardsSlider;
