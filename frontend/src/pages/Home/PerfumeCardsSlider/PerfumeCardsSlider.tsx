import React, { FC, ReactElement, useEffect } from "react";
import { useDispatch } from "react-redux";

import { fetchPerfumesByIds } from "../../../redux-toolkit/perfumes/perfumes-thunks";
import { resetPerfumesState } from "../../../redux-toolkit/perfumes/perfumes-slice";
import "./PerfumeCardsSlider.css";

export const perfumesIds = [26, 43, 46, 106, 34, 76, 82, 85, 27, 39, 79, 86];

const PerfumeCardsSlider: FC = (): ReactElement => {
    const dispatch = useDispatch();

    useEffect(() => {
        // GraphQL example
        // dispatch(fetchPerfumesByIdsQuery(perfumesId));
        dispatch(fetchPerfumesByIds(perfumesIds));

        return () => {
            dispatch(resetPerfumesState());
        };
    }, []);

    return (
        <div className={"Bottom-padding"}>
        </div>
    );
};

export default PerfumeCardsSlider;
