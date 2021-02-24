import React, {useCallback, useEffect, useRef, useState} from "react";
import {animated, useSpring} from 'react-spring'
import styled from 'styled-components'
import {MdClose} from "react-icons/all";
import Input from "react-validation/build/input";
import Form from "react-validation/build/form";

import CheckButton from "react-validation/build/button";
import SoloService from "../services/solo-service"

const Background = styled.div`
    width: 100%;
    height: 100%;
    background: rgba(0, 0,0 0, 0.8);
    position: fixed;
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1;
`

const ModalWrapper = styled.div`
    width: 300px;
    box-shadow: 0 5px 16px rgba(0, 0, 0, 0.2);
    background: #fff;
    color: #000;
    z-index: 1;
    border-radius: 10px;
`



const ModalImg = styled.img`
    width: 100%;
    height: 100%;
    border-radius: 10px 0 0 10px;
    background: #000;
    z-index: 1;

`

const ModalContent = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  line-height: 1.8;
  z-index: 1;
  color: #141414;
  p {
    margin-bottom: 1rem;
  }
  label{
      width: 100%;
    text-align: center;
  }
  button {
    padding: 10px 24px;
    background: #141414;
    color: #fff;
    border: none;
  }
`;
const Form1 = styled(Form)`
    width:100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
`

const label = styled.label`
    width: 100%;
    text-align: center;
`


const CloseModalButton = styled(MdClose)`
  cursor: pointer;
  position: absolute;
  top: 5px;
  right: 5px;
  width: 32px;
  height: 32px;
  padding: 0;
  z-index: 1;
`;





export const SoloExpenseModal = ({showModal, setShowModal}) =>{
    let today = new Date().toISOString().slice(0, 10)
    const modalRef = useRef()
    const form = useRef();
    const checkBtn = useRef();

    const [expenseName, setExpenseName] = useState("");
    const [expenseValue, setExpenseValue] = useState("");
    const [localDate, setLocalDate] = useState(today);
    const [isCyclic, setIsCyclic] = useState(false);
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState("");
    const onChangeExpenseName = (e) => {
        const expenseName = e.target.value;
        setExpenseName(expenseName);
    };

    const onChangeExpenseValue = (e) => {
        const expenseValue = e.target.value;
        setExpenseValue(expenseValue);
    };

    const onChangeLocalDate = (e) => {
        const localDate = e.target.value;
        setLocalDate(localDate);
    };

    const onChangeIsCyclic = (e) => {
        const isCyclic = e.target.checked;
        setIsCyclic(isCyclic);
    };


    const animation = useSpring({
        config:{
            duration:250
        },
        opacity: showModal ? 1 : 0,
        transform: showModal ? `translateY(0%)` : `translateY(-100%`,
        position:"fixed",
        top:0,
    })


    const closeModal= e => {
        if(modalRef.current === e.target){
            setShowModal(false)
        }
    }

    const keyPress = useCallback(
        e => {
            if (e.key === 'Escape' && showModal) {
                setShowModal(false);
            }
        },
        [setShowModal, showModal]
    )

    const handleExpenseSubmit = (e) => {
        e.preventDefault();

        setMessage("");
        setLoading(true);

        form.current.validateAll();

        if (checkBtn.current.context._errors.length === 0) {
            SoloService.addExpense(expenseName, expenseValue, localDate, isCyclic).then(
                () => {
                    // props.history.push("/profile");
                    window.location.reload();
                },
                (error) => {
                    const resMessage =
                        (error.response &&
                            error.response.data &&
                            error.response.data.message) ||
                        error.message ||
                        error.toString();

                    setLoading(false);
                    setMessage(resMessage);
                }
            );
        } else {
            setLoading(false);
        }
    };

    useEffect(
        () => {
            document.addEventListener('keydown', keyPress);
            return () => document.removeEventListener('keydown', keyPress);
        },
        [keyPress]
    );



    return(
        <>
            {showModal ? (
                <Background ref={modalRef} onClick={closeModal}>
                    <animated.div style={animation}>
                        <ModalWrapper showModal={showModal}>
                            {/*<ModalImg src={require('../public/modal.jpg')}*/}
                            {/*          alt='image'/>*/}
                            <ModalContent>
                                <p>ADD EXPENSE</p>

                                <Form1 onSubmit={handleExpenseSubmit} ref={form}>
                                    <div className="form-group">
                                        <label htmlFor="expenseName">Expense name</label>
                                        <Input
                                            type="text"
                                            className="form-control"
                                            name="expenseName"
                                            value={expenseName}
                                            onChange={onChangeExpenseName}
                                            isRequired="true"
                                            // validations={[required]}
                                        />
                                        <label htmlFor="expenseValue">Expense value</label>
                                        <Input
                                            type="text"
                                            className="form-control"
                                            name="expenseValue"
                                            value={expenseValue}
                                            onChange={onChangeExpenseValue}
                                            // validations={[required]}
                                        />
                                        <label htmlFor="localDate">Date</label>
                                        <Input
                                            type="text"
                                            className="form-control"
                                            name="localDate"
                                            value={localDate}
                                            onChange={onChangeLocalDate}
                                            // validations={[required]}
                                            disabled
                                        />
                                        <label htmlFor="isCyclic">Is it cyclic?</label>
                                        <Input
                                            type="checkbox"
                                            className="form-control"
                                            name="isCyclic"
                                            value={isCyclic}
                                            onChange={onChangeIsCyclic}
                                            // validations={[required]}
                                        />

                                    </div>
                                    <div className="form-group">
                                        <button className="btn btn-primary btn-block" disabled={loading}>
                                            {loading && (
                                                <span className="spinner-border spinner-border-sm"/>
                                            )}
                                            <span>Add expense</span>
                                        </button>
                                    </div>

                                    {message && (
                                        <div className="form-group">
                                            <div className="alert alert-danger" role="alert">
                                                {message}
                                            </div>
                                        </div>
                                    )}
                                    <CheckButton style={{ display: "none" }} ref={checkBtn} />
                                </Form1>








                            </ModalContent>
                            <CloseModalButton aria-label='Close modal' onClick={() => setShowModal(prev => !prev)}/>
                        </ModalWrapper>
                    </animated.div>
                </Background>
            ) : null}
        </>
    )
}