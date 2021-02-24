import React, {useEffect, useState} from "react";
import {makeStyles} from '@material-ui/core/styles';
import ListItem from '@material-ui/core/ListItem';

import UserService from "../services/user-service";
import RoomsService from "../services/rooms-service";
import {DataGrid} from "@material-ui/data-grid";
import {GroupExpenseModal} from "./GroupExpenseModal";
import {RoomAddUserModal} from "./RoomAddUserModal"
import {User} from "./User"
import styled from "styled-components";
import axios from "axios";


const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
        maxWidth: 360,
        backgroundColor: theme.palette.background.paper,
    },
}));

const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("user"))
}

function f() {
    return axios.get('http://localhost:8080/user/getall')

}

function ListItemLink(props) {
    return <ListItem button component="a" {...props} />;
}

// export default function SimpleList() {
//     const classes = useStyles();}

const Room = (props) => {
    const [content, setContent] = useState([]);
    const [expenses, setExpenses]= useState([]);
    const [roomUsers, setRoomUsers] = useState([]);

    const [showGroupExpenseModal, setShowGroupExpenseModal] = useState(false);
    const [showRoomAddUserModal, setShowRoomAddUserModal] = useState(false);
    const [averageExpense, setAverageExpense] = useState("");
    const [totalExpense, setTotalExpense] = useState("");
    const [currentUser, setCurrentUser] = useState({});

    const openGroupExpenseModal = () =>{
        setShowGroupExpenseModal(prev => !prev)
    }
    const openRoomAddUserModal = () =>{
        setShowRoomAddUserModal(prev => !prev)
    }

    useEffect(() => {
        setContent(props.location.state.con)
        RoomsService.getRoomExpenses(props.location.state.con.id).then(
            (response) => {
                console.table(response.data)
                setExpenses(response.data);

            },
            (error) => {
                const _expenses =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();
                setExpenses(_expenses);
            }
        );

        RoomsService.getExpensePerUser(props.location.state.con.id).then(
            (response) => {
                setAverageExpense(response.data);
            },
            (error) => {
                const _averageExpense =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();
                setAverageExpense(_averageExpense);
            }
        );

        RoomsService.getRoomUsers(props.location.state.con.id).then(
            (response) => {
                setRoomUsers(response.data);
            },
            (error) => {
                const _roomUsers =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();
                setRoomUsers(_roomUsers);
            }
        );

        UserService.getUserBoard().then(
            response => {
                setCurrentUser(response.data);
                return currentUser;
            },
            (error) => {
                const _currentUser =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();

                setContent(_currentUser);
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
        <div className="col-md-12">

        <div className="container" style={{display:'grid',  'grid-template-columns': '1fr 1fr' }}>
            <div className="jumbotron">
                <h3>ROOM:{content.name}</h3>
                <h2>Expense per user:{(averageExpense*1).toFixed(2)}</h2>

                <Button onClick={openGroupExpenseModal}>Add expense</Button>
                <GroupExpenseModal showModal={showGroupExpenseModal}
                                   setShowModal={setShowGroupExpenseModal}
                                   roomId={props.location.state.con.id}/>

                <Button onClick={openRoomAddUserModal}>Add user</Button>
                <RoomAddUserModal showModal={showRoomAddUserModal}
                                  setShowModal={setShowRoomAddUserModal}
                                  roomId={props.location.state.con.id}/>


                {props.location.state.con.administratorId === currentUser.id ? (
                    <div>
                        <Button onClick={()=>
                        {RoomsService.closeRoom(props.location.state.con.id)
                        window.location.href="/rooms"}}>Close room</Button>

                    </div>

                ):(null) }

            </div>
            <div><h1>Room users:</h1>{roomUsers.map(usr=>(<h1>{usr.username}</h1>))}</div>

            <div style={{ height: 600, width: '100%', 'grid-column': '1/3' }}>
            <DataGrid
                columns={[{ field: 'userId', width:100 }, {field: 'expenseName', width:300}, {field: 'expenseValue', width:300}]}
                rows={expenses.map(exp=>({
                    id: exp.id,
                    userId: exp.userId,
                    expenseName: exp.expenseName,
                    expenseValue: exp.expenseValue
                }))

                }

            />
            </div>



        </div>
        </div>
    );
};

export default Room;