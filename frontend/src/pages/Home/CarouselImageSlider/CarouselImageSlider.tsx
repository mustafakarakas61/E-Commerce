import React, { FC, ReactElement } from "react";
import { Carousel,Typography } from "antd";
import { Link } from "react-router-dom";

import { PRODUCT } from "../../../constants/routeConstants";
import "./CarouselImageSlider.css";

export const sliderItems = [
    {
        id: "2",
        name: "Photo 2",
        url: "https://i.ibb.co/J3r2jW5/laptop.png"
    },
    {
        id: "14",
        name: "Photo 14",
        url: "https://cdn.akakce.com/bose/bose-quietcomfort-35-ii-gurultu-onleyici-kablosuz-kulak-ustu-z.jpg"
    },
    {
        id: "7",
        name: "Photo 7",
        url: "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-12-finish-unselect-gallery-1-202207_GEO_US?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1662129048006"
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
