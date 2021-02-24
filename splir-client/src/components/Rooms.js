import React, {useEffect, useState} from "react";
import {makeStyles} from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import {Link} from "react-router-dom";

import UserService from "../services/user-service";
import RoomsService from "../services/rooms-service";
import {CreateRoomModal} from "./CreateRoomModal";
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

const Rooms = () => {
    const [content, setContent] = useState([]);
    const [showCreateRoomModal, setShowCreateRoomModal] = useState(false);
    const [details, setDetails] = useState("");

    const getUserDetails = async (id) => {
        const details = await UserService.getUserDetails(id);
        return details.data.username;
    }


    const openCreateRoomModal = () =>{
        setShowCreateRoomModal(prev => !prev)
    }

    useEffect(() => {
        RoomsService.getRooms().then(
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
            <header className="jumbotron">
                <Button onClick={openCreateRoomModal}>create room</Button>
                <CreateRoomModal showModal={showCreateRoomModal}
                                   setShowModal={setShowCreateRoomModal}/>
                {content.map(con=>(
                    <List component="nav" aria-label="secondary mailbox folders">
                        <ListItem button component={Link} to={{pathname:"/room", state:{con}}} >
                            <ListItemText primary={con.name} secondary={"Total amount: " +con.totalExpense+ " zł"}/>
                        </ListItem>
                    </List>
                )
            )}
            </header>
        </div>
    );
};

export default Rooms;