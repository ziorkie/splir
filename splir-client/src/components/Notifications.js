import React from "react";
import {makeStyles} from '@material-ui/core/styles';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import {Paper} from "@material-ui/core";
import NotificationsReceived from "./NotificationsReceived";
import NotificationsSent from "./NotificationsSent";

const useStyles = makeStyles({
    root: {
        flexGrow: 1,

    },
});

const Notifications = ()  =>{

    const classes = useStyles();
    const [value, setValue] = React.useState(0);

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };
    return(

        <>
            <Paper className={classes.root}>
                <Tabs
                    value={value}
                    onChange={handleChange}
                    centered
                    textColor="primary"   >
                    <Tab label="RECEIVED"  />
                    <Tab label="SENT" />

                </Tabs>
            </Paper>
            {value===0 && <NotificationsReceived />}
            {value===1 && <NotificationsSent />}

        </>
    );

};
export default Notifications;