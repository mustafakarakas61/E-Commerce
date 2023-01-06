import React, {FC, ReactElement, useEffect} from "react";

import CarouselImageSlider from "./CarouselImageSlider/CarouselImageSlider";
import SliderBrands from "./SliderBrands/SliderBrands";
import HomePageTheme from "./HomePageTheme/HomePageTheme";
import ProductCardsSlider from "./ProductCardsSlider/ProductCardsSlider";

const Home: FC = (): ReactElement => {
    
    useEffect(() => {
        window.scrollTo(0, 0);
    }, []);
    
    return (
        <div>
            <CarouselImageSlider />
            <SliderBrands />
            <HomePageTheme />
            <ProductCardsSlider />
        </div>
    );
};

export default Home;
