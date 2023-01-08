import React, { FC, ReactElement } from "react";
import { Carousel, Typography } from "antd";

import { brandsItem } from "./SliderBrandsData";
import SliderBrandsItem from "./SliderBrandsItem/SliderBrandsItem";
import "./SliderBrands.css";

const SliderBrands: FC = (): ReactElement => {
    return (
        <div className={"brands-wrapper"}>
            <Typography.Title level={3} className={"brands-wrapper-title"}>
                KATEGORİLER
            </Typography.Title>
            <Carousel className={"ant-row2"} autoplay>
                <SliderBrandsItem brands={brandsItem.slice(0, 4)} />
                <SliderBrandsItem brands={brandsItem.slice(4, 8)} />
            </Carousel>
        </div>
    );
};

export default SliderBrands;
