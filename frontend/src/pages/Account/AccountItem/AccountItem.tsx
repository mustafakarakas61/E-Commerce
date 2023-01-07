import React, { FC, ReactElement } from "react";
import { useSelector } from "react-redux";
import { Typography } from "antd";

import { selectIsUserLoading, selectUserFromUserState } from "../../../redux-toolkit/user/user-selector";
import Spinner from "../../../components/Spinner/Spinner";

const AccountItem: FC = (): ReactElement => {
    const usersData = useSelector(selectUserFromUserState);
    const loading = useSelector(selectIsUserLoading);

    return (
        <>
            {loading ? (
                <Spinner />
            ) : (
                <Typography.Title level={5} style={{ textAlign: "center" }}>
                    Hoşgeldin {usersData?.firstName} {usersData?.lastName} ! &#128526;
                </Typography.Title>
            )}
        </>
    );
};

export default AccountItem;
