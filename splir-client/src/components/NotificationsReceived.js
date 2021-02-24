import React, {useEffect, useState} from "react";
import {makeStyles} from '@material-ui/core/styles';
import ListItem from '@material-ui/core/ListItem';

import UserService from "../services/user-service";
import NotificationsService from "../services/notifications-service";
import Accordion from '@material-ui/core/Accordion';
import AccordionSummary from '@material-ui/core/AccordionSummary';
import AccordionDetails from '@material-ui/core/AccordionDetails';
import Typography from '@material-ui/core/Typography';


const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
        maxWidth: 360,
        backgroundColor: theme.palette.background.paper,
    },
    heading: {
        fontSize: theme.typography.pxToRem(15),
        fontWeight: theme.typography.fontWeightRegular,
    },
}));



function ListItemLink(props) {
    return <ListItem button component="a" {...props} />;
}

// export default function SimpleList() {
//     const classes = useStyles();}


const NotificationsReceived = () => {
    const [content, setContent] = useState([]);
    const [buttonHelper, setButtonHelper] = useState(false);
    const [details, setDetails] = useState([]);

    const getDetail = async (id) => {
        const response =  await UserService.getPaymentDetails(id)
        return response.data;
    };

    const getDetailsAsync = async (list) => {
        return Promise.all(
            list.map(obj => {
                const { senderId } = obj;
                return getDetail(senderId)
            })
        );
    };

    useEffect(() => {
        NotificationsService.getNotifications().then(
            (response) => {
                setContent(response.data);
                getDetailsAsync(response.data).then(details => {
                    setDetails(details)});
            },
            (error) => {
                const _content =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message)
                error.message ||
                error.toString();

                setContent(_content);
            }
        );
    }, []);


    const handleGetUserDetails = (id) => {
        setContent(content.filter(obj=>obj.id!==id))
    };


    return (
        <div >
            {content.map((con, index)=>(
                con.seen!==true ?
            <Accordion>
                <AccordionSummary
                    // expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel1a-content"
                    id="panel1a-header"
                >
                    <Typography >
                        {con.message}
                    </Typography>
                </AccordionSummary>
                <AccordionDetails>
                    <Typography>

                        {details.length &&
                           "BLIK phone number:"+ details[index].phoneNumber+ " Bank account number: " + details[index].accountNumber+" "
                        }


                        <button onClick={()=>{NotificationsService.setSeen(con.id);handleGetUserDetails(con.id)}}>I returned</button>
                    </Typography>
                </AccordionDetails>
            </Accordion>
                    :null
                ))}
        </div>
    );
};

export default NotificationsReceived;