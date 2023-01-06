export const getAllProductsByQuery = `
    {
        products {
            id
            productTitle
            producer
            price
            filename
            productRating
        }
    }
`;

export const getProductByQuery = (id: string) => `
    {
        product(id: ${id}) {
            id
            productTitle
            producer
            year
            city
            productType
            colors
            filename
            price
            type
            productRating
            reviews {
                id
                author
                message
                date
                rating
            }
        }
    }
`;

export const geProductsByIdsQuery = (ids: Array<number>) => `
    {
        productsIds(ids: [${ids}]) {
            id
            productTitle
            producer
            price
            filename
            productRating
        }
    }
`;
