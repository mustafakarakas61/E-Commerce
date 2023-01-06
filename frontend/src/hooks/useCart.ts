import { useHistory } from "react-router-dom";
import { CART } from "../constants/routeConstants";

interface UseCart {
    addToCart: () => void;
}

export const useCart = (productId: number): UseCart => {
    const history = useHistory();

    const addToCart = (): void => {
        let data: string | null = localStorage.getItem("products");
        let cart: Map<number, any> = data ? new Map(JSON.parse(data as string)) : new Map();

        if (cart.has(productId as number)) {
            cart.set(productId as number, cart.get(productId as number) + 1);
        } else {
            cart.set(productId as number, 1);
        }
        localStorage.setItem("products", JSON.stringify(Array.from(cart.entries())));
        history.push(CART);
    };

    return { addToCart };
};
