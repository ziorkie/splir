import React, {useEffect, useState} from 'react';
import './App.css';
import styled from 'styled-components'
import AuthService from "./services/auth-service";
import "bootstrap/dist/css/bootstrap.min.css";
import {Link, Route, Switch} from "react-router-dom";
import BoardUser from "./components/BoardUser";
import Login from "./components/Login";
import Register from "./components/Register";
import Profile from "./components/Profile";
import Home from "./components/Home";
import Rooms from "./components/Rooms"
import Notifications from "./components/Notifications";

import Room from "./components/Room"
import FriendsAndInvites from "./components/FriendsAndInvites";



const App = () => {
    // const [showModeratorBoard, setShowModeratorBoard] = useState(false);
    // const [showAdminBoard, setShowAdminBoard] = useState(false);
    const [currentUser, setCurrentUser] = useState(undefined);

    useEffect(() => {
        const user = AuthService.getCurrentUser();

        if (user) {
            setCurrentUser(user);
            // setShowModeratorBoard(user.roles.includes("ROLE_MODERATOR"));
            // setShowAdminBoard(user.roles.includes("ROLE_ADMIN"));
        }
    }, []);

    const Container = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    `


    const logOut = () => {
        AuthService.logout();
    };


    return (
        <div>
            <nav className="navbar navbar-expand navbar-dark bg-dark">
                <Link to={"#"} className="navbar-brand">
                    SPLIR
                </Link>
                <div className="navbar-nav mr-auto">
                    <li className="nav-item">
                        <Link to={"/"} className="nav-link">
                            Home
                        </Link>
                    </li>
                    {currentUser &&(
                    <li className="nav-item">
                        <Link to={"/rooms"} className="nav-link">
                            Rooms
                        </Link>
                    </li>
                    )}
                    {currentUser &&(
                    <li className="nav-item">
                        <Link to={"/friends"} className="nav-link">
                            Friends
                        </Link>
                    </li>
                        )}
                    {currentUser &&(
                    <li className="nav-item">
                        <Link to={"/notifications"} className="nav-link">
                            Notifications
                        </Link>
                    </li>
                        )}

                    {currentUser && (
                        <li className="nav-item">
                            <Link to={"/user"} className="nav-link">
                                User
                            </Link>
                        </li>



                    )}
                </div>

                {currentUser ? (



                    <div className="navbar-nav ml-auto">



                        <li className="nav-item">
                            <Link to={"/profile"} className="nav-link">
                                {currentUser.username}
                            </Link>
                        </li>
                        <li className="nav-item">
                            <a href="/login" className="nav-link" onClick={logOut}>
                                LogOut
                            </a>
                        </li>
                    </div>
                ) : (
                    <div className="navbar-nav ml-auto">
                        <li className="nav-item">
                            <Link to={"/login"} className="nav-link">
                                Login
                            </Link>
                        </li>

                        <li className="nav-item">
                            <Link to={"/register"} className="nav-link">
                                Sign Up
                            </Link>
                        </li>

                    </div>
                )}
            </nav>

            <div className="container mt-3">
                <Switch>

                    <Route exact path="/" component={Home} />
                    <Route exact path="/login" component={Login} />
                    <Route exact path="/register" component={Register} />
                    <Route exact path="/profile" component={Profile} />
                    <Route exact path="/rooms" component={Rooms}/>
                    <Route exact path="/room" component={Room}/>
                    <Route exact path="/notifications" component={Notifications}/>
                    <Route exact path="/friends" component={FriendsAndInvites}/>

                    <Route path="/user" component={BoardUser} />
                    {/*<Route path="/mod" component={BoardModerator} />*/}
                    {/*<Route path="/admin" component={BoardAdmin} />*/}
                </Switch>
            </div>
        </div>
    );
};


export default App;
