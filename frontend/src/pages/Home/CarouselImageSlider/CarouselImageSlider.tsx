import React, { FC, ReactElement } from "react";
import { Carousel,Typography } from "antd";
import { Link } from "react-router-dom";

import { PRODUCT } from "../../../constants/routeConstants";
import "./CarouselImageSlider.css";

export const sliderItems = [
    {
        id: "2",
        name: "Photo 2",
        url: "https://i.ibb.co/zfpWQgB/resim-2023-01-08-210418848.png"
    },
    {
        id: "14",
        name: "Photo 14",
        url: "https://i.ibb.co/myYpy9n/image.png"
    },
    {
        id: "1",
        name: "Photo 1",
        url: "https://i.ibb.co/ChDRfps/resim-2023-01-08-212027801.png"
    }
];

const CarouselImageSlider: FC = (): ReactElement => {
    return (
        <div className={"carousel-item-wrapper"}>
            <Typography.Title level={3} className={"carousel-item-slider-title"}>
                ÖNE ÇIKANLAR
            </Typography.Title>
            <Carousel>
                {sliderItems.map((item) => (
                    <div key={item.id} className={"carousel-item-wrapper"}>
                        <Link to={`${PRODUCT}/${item.id}`} className={"carousel-link"} />
                        <img src={item.url} alt={item.name} />
                    </div>
                ))}
            </Carousel>
        </div>
    );
};

export default CarouselImageSlider;
