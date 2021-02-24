import React, {useEffect, useState} from "react";

import SoloService from "../services/solo-service";
import {DataGrid} from "@material-ui/data-grid";
import styled from "styled-components";
import {SoloExpenseModal} from "./SoloExpenseModal"

const Home = () => {
    const [content, setContent] = useState([]);
    const [showSoloExpenseModal, setShowSoloExpenseModal] = useState(false);

    const openSoloExpenseModal = () =>{
        setShowSoloExpenseModal(prev => !prev)
    }


    useEffect(() => {
        SoloService.getExpenses().then(
            (response) => {
                setContent(response.data);
            },
            (error) => {
                const _content =
                    (error.response && error.response.data) ||
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

    const totalValue = () =>{
        let expVal = 0.0
        content.map(con=>(expVal+=con.expenseValue))
        return expVal

    }
    return (
        <div className="container">

            YOUR EXPENSES FOR CURRENT MONTH: {totalValue()}
            <Button onClick={openSoloExpenseModal}>Add expense</Button>
            <SoloExpenseModal showModal={showSoloExpenseModal}
                               setShowModal={setShowSoloExpenseModal}/>
            <div style={{ height: 600, width: '100%', margin:'1em 0' }}>
                <DataGrid
                    columns={[{field: 'Expense name', width:300}, {field: 'Expense value', width:150}, {field: 'Creation date', width: 150}, {field: 'Cyclic?', width:150}]}
                    rows={content.map(con=>({
                        id: con.id,
                        'Expense name': con.expenseName,
                        'Expense value': con.expenseValue,
                        'Creation date': con.createDate,
                        'Cyclic?': con.cyclic
                    }))

                    }

                />
            </div>
        </div>
    );
};

export default Home;