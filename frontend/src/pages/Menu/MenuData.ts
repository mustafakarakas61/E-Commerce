import { ProductPrice } from "../../types/types";

export const types: Array<{ name: string }> = [
    { name: "Elektrikli ev aletleri" },
    { name: "Elektronik" },
    { name: "Ev ve Yaşam" },
    { name: "Giyim" },
    { name: "Kozmetik" },
    { name: "Kitaplar" },
    { name: "Spor" },
    { name: "Hediyelik" }
];

export const productTypes: Array<{ name: string }> = [
    { name: "Ayakkabı" },
    { name: "Bilgisayar" },
    { name: "Buzdolabı" }
];

export const price: Array<ProductPrice> = [
    { id: 1, name: "Aralık Yok", array: [1, 9999] },
    { id: 2, name: "100 - 200₺", array: [100, 200] },
    { id: 3, name: "200 - 300₺", array: [200, 300] },
    { id: 4, name: "300 - 400₺", array: [300, 400] },
    { id: 5, name: "400 - 1000+₺", array: [400, 3000] }
];
