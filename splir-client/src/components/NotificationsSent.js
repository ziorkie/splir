import React, {useEffect, useState} from "react";
import {makeStyles} from '@material-ui/core/styles';
import ListItem from '@material-ui/core/ListItem';
import NotificationsService from "../services/notifications-service";
import Accordion from "@material-ui/core/Accordion";
import AccordionSummary from "@material-ui/core/AccordionSummary";
import Typography from "@material-ui/core/Typography";
import AccordionDetails from "@material-ui/core/AccordionDetails";


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

const NotificationsSent = () => {
    const [content, setContent] = useState([]);
    const [buttonHelper, setButtonHelper] = useState(false);

    useEffect(() => {
        NotificationsService.getSentNotifications().then(
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

    const handleGetUserDetails = (id) => {
        setContent(content.filter(obj=>obj.id!==id))
    };


return (
    <div >
        {content.map((con)=>(
            <Accordion>
                <AccordionSummary
                    // expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel1a-content"
                    id="panel1a-header"
                >
                    <Typography >
                        Debtor: {con.debtorUsername}
                    </Typography>
                </AccordionSummary>
                <AccordionDetails>
                    <Typography>

                        {con.message.split('need')[0]}

                        {con.seen!==false ?
                    <button onClick={()=>{NotificationsService.removeNotification(con.id);handleGetUserDetails(con.id)}}>I received</button>:null}
                    </Typography>
                </AccordionDetails>
            </Accordion>
        ))}
    </div>
);
};
export default NotificationsSent;