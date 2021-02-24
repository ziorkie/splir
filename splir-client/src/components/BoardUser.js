import React, {useEffect, useState} from "react";

import UserService from "../services/user-service";

const BoardUser = () => {
    const [content, setContent] = useState({});

    useEffect(() => {
        UserService.getUserBoard().then(
            response => {
                setContent(response.data);
                return content;
            },
            (error) => {
                const _content =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();

                setContent(_content);
            }
        );
    }, []);

    return (
        <div className="container">
            <header className="jumbotron">
                <h3>Id: {content.id}</h3>
                <h3>Name: {content.name}</h3>
                <h3>Username: {content.username}</h3>

            </header>
        </div>
    );
};

export default BoardUser;