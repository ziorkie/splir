import React, {useEffect, useState} from "react";
import {makeStyles} from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import InvitationService from "../services/invitation-service"
import {InviteModal} from "./InviteModal";

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
    const [receivedInvites, setReceivedInvites] = useState([]);

    const [showModal, setShowModal] = useState(false);

    const openModal = () =>{
        setShowModal(prev => !prev)
    }




    useEffect(() => {
        InvitationService.myReceivedInvites().then(
            (response) => {
                setReceivedInvites(response.data);
            },
            (error) => {
                const _content =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();

                setReceivedInvites(_content);
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

                <Button onClick={openModal}>send invite</Button>
                <InviteModal showModal={showModal}
                             setShowModal={setShowModal}/>

            <header className="flex-box">
                <h1>Pending invites:</h1>
                <List component="nav" aria-label="secondary mailbox folders">
                    {receivedInvites.map((rec)=>(
                            <ListItem>
                                <ListItemText primary={rec.username}/>
                                <Button onClick={()=>{InvitationService.acceptInvite(rec.userId)
                                setReceivedInvites(receivedInvites.filter(obj=>obj.userId !==rec.userId))
                                }

                                }>ACCEPT</Button>
                                <Button onClick={()=>{InvitationService.deleteInvite(rec.userId)
                                    setReceivedInvites(receivedInvites.filter(obj=>obj.userId !==rec.userId))
                                }

                                }>REJECT</Button>
                            </ListItem>

                        )
                    )}
                </List>
            </header>
        </div>
    );
};

export default Friends;