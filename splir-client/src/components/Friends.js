import React, {useEffect, useState} from "react";
import {makeStyles} from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';

import FriendsService from "../services/friends-service";
import styled from "styled-components";


const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
        maxWidth: 360,
        backgroundColor: theme.palette.background.paper,
    },
}));

function ListItemLink(props) {
    return <ListItem button component="a" {...props} />;
}

// export default function SimpleList() {
//     const classes = useStyles();}

const Friends = () => {
    const [content, setContent] = useState([]);


    useEffect(() => {
        FriendsService.getFriends().then(
            (response) => {
                setContent(response.data);
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


    const Button = styled.button`
    min-width: 100px;
    padding: 0.3em;
    border-radius: 4px;
    border:none;
    background: #141414;
    color: #fff;
    cursor: pointer;
    `

    return (
        <div className="container">



            <header className="flex-box">
                <h1>Your friends:</h1>
                <List component="nav" aria-label="secondary mailbox folders">
                    {content.map((con)=>(
                            <ListItem>
                                <ListItemText primary={con.username}/>
                                <Button onClick={()=>{FriendsService.deleteFriend(con.userId)
                                    setContent(content.filter(obj=>obj.userId !==con.userId))
                                }
                                }>DELETE</Button>
                            </ListItem>
                        )
                    )}
                </List>
            </header>
        </div>
    );
};

export default Friends;