import React from "react";
import {makeStyles} from '@material-ui/core/styles';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import {Paper} from "@material-ui/core";
import Friends from "./Friends"
import Invites from "./Invites"

const useStyles = makeStyles({
    root: {
        flexGrow: 1,

    },
});

const FriendsAndInvites = ()  =>{

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
                    <Tab label="Friends"  />
                    <Tab label="Invites" />

                </Tabs>
            </Paper>
            {value===0 && <Friends />}
            {value===1 && <Invites />}

        </>
    );

};
export default FriendsAndInvites;