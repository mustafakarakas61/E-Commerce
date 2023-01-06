import React, { FC, ReactElement } from "react";
import { Row } from "antd";

import ProductCard from "../../../../components/ProductCard/ProductCard";
import { ProductResponse } from "../../../../types/types";

type PropsType = {
    products: Array<ProductResponse>;
};

const ProductCardsSliderItem: FC<PropsType> = ({ products }): ReactElement => {
    return (
        <Row gutter={[16, 16]} style={{ margin: 10, marginTop: 10, marginBottom: 10 }}>
            {products.slice(0, 4).map((product) => (
                <ProductCard key={product.id} product={product} colSpan={6} />
            ))}
        </Row>
    );
};

export default ProductCardsSliderItem;
