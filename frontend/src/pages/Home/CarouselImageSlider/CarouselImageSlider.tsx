import React, { FC, ReactElement } from "react";
import { Carousel,Typography } from "antd";
import { Link } from "react-router-dom";

import { PRODUCT } from "../../../constants/routeConstants";
import "./CarouselImageSlider.css";

export const sliderItems = [
    {
        id: "85",
        name: "Photo 1",
        url: "https://i.ibb.co/bXdDdB9/1920x900-px-40867-1495607-wallhere-com.jpg"
    },
    {
        id: "46",
        name: "Photo 2",
        url: "https://i.ibb.co/C0vbNcy/dior-ENG.jpg"
    }
];

const CarouselImageSlider: FC = (): ReactElement => {
    return (
        <div className={"carousel-item-wrapper"}>
            <Typography.Title level={3} className={"carousel-item-slider-title"}>
                ÖNE ÇIKARILANLAR
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
