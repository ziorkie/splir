import * as React from 'react';
import {useEffect, useState} from 'react';
import {DataGrid} from '@material-ui/data-grid';
import axios from "axios";


const User=()=>{

    const[user,setUser] = useState([]);
    const[userr,setUserr] = useState({});
    // const rows = [
    //     {
    //         id: user[0].name,
    //         username: 'defunkt',
    //         age: 38,
    //     },
    // ];

    function f() {
        axios.get('http://localhost:8080/user/getall')
            .then(res=>{
            setUser(res.data)
            })
    }
    useEffect(()=>{
        f();
    },[]);

    return (
        <div style={{ height: 250, width: '100%' }}>

            <DataGrid

                columns={[
                    { field: 'id' },
                    { field: 'username', width: 200 },
                    { field: 'name' },
                ]}
                rows={user.map(usr=>(
                    {
                        id:usr.id,
                        username:usr.username,
                        name:usr.name,
                    }
                )
                )}


            />
        </div>
    );
}

export default User;