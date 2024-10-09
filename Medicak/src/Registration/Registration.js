import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './Registration.css';

const Registration = () => {
    const [emailId, setEmailId] = useState('');
    const [password, setPassword] = useState('');
    const [addharId, setAddharId] = useState('');
    const [name, setName] = useState('');
    const [dob, setDob] = useState('');
    const [message, setMessage] = useState('');

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Email validation regex
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(emailId)) {
            setMessage('Invalid email format');
            return;
        }

        // Name validation to ensure no numeric values
        const nameRegex = /^[a-zA-Z\s]+$/;
        if (!nameRegex.test(name)) {
            setMessage('Name cannot contain numbers or special characters');
            return;
        }

        // Aadhar ID validation
        if (!/^[1-9]{1}[0-9]{11}$/.test(addharId)) {
            setMessage('Aadhar ID must be exactly 12 digits and should not be zero');
            return;
        }

        // Date of Birth validation
        if (!dob) {
            setMessage('Date of Birth is required');
            return;
        }

        const user = {
            emailId: emailId,
            password: password,
            addharId: addharId,
            name: name,
            dob: dob
        };

        try {
            const response = await axios.post('http://localhost:8080/user/register', user);
            setMessage(response.data);  // Assuming the response is a string message
        } catch (error) {
            setMessage('Registration failed');
        }
    };

    // Function to restrict input to numeric values only
    const handleAadharChange = (e) => {
        const value = e.target.value;
        if (/^\d*$/.test(value)) {
            setAddharId(value);
        }
    };

    const handleLoginRedirect = () => {
        navigate('/login');
    };
    const getCurrentDate = () => {
        const today = new Date();
        return today.toISOString().split('T')[0];
    };
    return (
        <div className="register-container">
            <h2>Register</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Name:</label>
                    <input 
                        type="text" 
                        value={name} 
                        onChange={(e) => setName(e.target.value)} 
                        required 
                    />
                </div>
                <div className="form-group">
                    <label>Email ID:</label>
                    <input 
                        type="email" 
                        value={emailId} 
                        onChange={(e) => setEmailId(e.target.value)} 
                        required 
                    />
                </div>
                <div className="form-group">
                    <label>Password:</label>
                    <input 
                        type="password" 
                        value={password} 
                        onChange={(e) => setPassword(e.target.value)} 
                        required 
                    />
                </div>
                <div className="form-group">
                    <label>Aadhar ID:</label>
                    <input 
                        type="text" 
                        value={addharId} 
                        onChange={handleAadharChange} 
                        required 
                    />
                </div>
                <div className="form-group">
                    <label>Date of Birth:</label>
                    <input 
                        type="date" 
                        value={dob} 
                        onChange={(e) => setDob(e.target.value)}  max={getCurrentDate()} 
                        required 
                    />
                </div>
                <div className="login-link">
                    <button type="submit" className='btn'>Register</button>
                    <button type='button' className="btn" onClick={handleLoginRedirect}>Login</button>
                </div>
            </form>
            {message && <p id="msg">{message}</p>}
        </div>
    );
};

export default Registration;
