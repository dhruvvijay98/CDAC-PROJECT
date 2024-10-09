import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './PrescriptionTable.css';

const PrescriptionTable = ({ userData }) => {
    const [prescriptions, setPrescriptions] = useState([]);
    const [healthCardId, setHealthCardId] = useState('');
    const [showModal, setShowModal] = useState(false);
    const [selectedPrescriptionId, setSelectedPrescriptionId] = useState(null);
    const [cardNumber, setCardNumber] = useState('');
    const [expiryDate, setExpiryDate] = useState('');
    const [cvv, setCvv] = useState('');
    const [amount, setAmount] = useState('');
    const [pharmacist, setPharmacist] = useState(null);
    const [errors, setErrors] = useState({});
    const navigate = useNavigate();

    useEffect(() => {
        fetch(`http://localhost:8080/user/getPharmistDetails?userId=${userData.userId}`)
            .then(response => response.json())
            .then(data => setPharmacist(data))
            .catch(error => console.error('Error fetching pharmacist details:', error));
    }, [userData.userId]);

    const fetchPrescriptions = () => {
        fetch(`http://localhost:8080/healthcard/getPrescriptionByHealthIdPharmasist?health_card_id=${healthCardId}`)
            .then(response => response.json())
            .then(data => setPrescriptions(data))
            .catch(error => console.error('Error fetching prescriptions:', error));
    };

    const handlePay = (prescriptionId) => {
        setSelectedPrescriptionId(prescriptionId);
        setShowModal(true);
    };

    const handleLoginRedirect = () => {
        navigate('/login');
    };

    const handleModalClose = () => {
        setShowModal(false);
        setSelectedPrescriptionId(null);
        setCardNumber('');
        setExpiryDate('');
        setCvv('');
        setAmount('');
        setErrors({});
    };

    const validateInputs = () => {
        const errors = {};

        if (!/^\d{16}$/.test(cardNumber)) {
            errors.cardNumber = 'Card Number must be exactly 16 digits.';
        }

        const today = new Date();
        const [expMonth, expYear] = expiryDate.split('-').reverse();
        const expiryDateObj = new Date(expYear, expMonth - 1);

        if (!expiryDate || expiryDateObj < today) {
            errors.expiryDate = 'Expiry date must be in the future.';
        }

        if (!/^\d{3}$/.test(cvv)) {
            errors.cvv = 'CVV must be exactly 3 digits.';
        }

        if (parseFloat(amount) <= 0) {
            errors.amount = 'Amount must be a positive number.';
        }

        return errors;
    };

    const handleSubmitPayment = () => {
        const validationErrors = validateInputs();
        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            return;
        }
        else{
            setShowModal(false);
        }

        fetch(`http://localhost:8080/user/payment?prescriptionId=${selectedPrescriptionId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                cardNumber,
                expiryDate,
                cvv,
                amount,
            }),
        })
            .then(response => response.json())
            .then(data => {
                console.log('Payment response:', data);

                // Remove the prescription from the list
                setPrescriptions(prevPrescriptions =>
                    prevPrescriptions.filter(prescription =>
                        prescription.prescription.prescriptionId !== selectedPrescriptionId
                    )
                );

                // Close the modal after successful payment
                handleModalClose();
            })
            .catch(error => {
                console.error('Error submitting payment:', error);
            });
    };

    return (
        <>
            <nav>
                <button type='button' className="btn" id='about-btn'>About</button>
                <button type='button' className="btn" id='logout-btn' onClick={handleLoginRedirect}>LOGOUT</button>
            </nav>
            <div className="Prescriptions-table" style={{ padding: '20px' }}>
                <h2>Prescriptions</h2>

                {pharmacist && (
                    <div>
                        <h3>Pharmacist Details</h3>
                        <p>Name: {userData.name}</p>
                        <p>DoB: {userData.dob}</p>
                        <p>Qualification: {pharmacist.qualification}</p>
                        <p>GST Number: {pharmacist.gst_no}</p>
                    </div>
                )}

                <div className='search-box' style={{ marginBottom: '20px' }}>
                    <input
                        type="text"
                        value={healthCardId}
                        onChange={(e) => setHealthCardId(e.target.value)}
                        placeholder="Enter Health Card ID"
                        style={{ marginRight: '10px', padding: '5px' }}
                    />
                    <button onClick={fetchPrescriptions} style={{ padding: '5px 10px' }}>View</button>
                </div>
                <table border="1" cellPadding="10" cellSpacing="0" style={{ width: '100%', textAlign: 'left' }}>
                    <thead>
                        <tr>
                            <th>Prescription ID</th>
                            <th>Status</th>
                            <th>Medicines</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {prescriptions.length === 0 ? (
                            <tr>
                                <td colSpan="4" style={{ textAlign: 'center' }}>No prescriptions found for this Health Card ID.</td>
                            </tr>
                        ) : (
                            prescriptions.map((item) => (
                                <tr key={item.prescription.prescriptionId}>
                                    <td>{item.prescription.prescriptionId}</td>
                                    <td>{item.prescription.status}</td>
                                    <td>
                                        <ul>
                                            {item.medicines.map((medicine) => (
                                                <li key={medicine.medicineId}>
                                                    {medicine.medicineName} : {medicine.qty}
                                                </li>
                                            ))}
                                        </ul>
                                    </td>
                                    <td>
                                        <button onClick={() => handlePay(item.prescription.prescriptionId)}>Pay</button>
                                    </td>
                                </tr>
                            ))
                        )}
                    </tbody>
                </table>

                {/* Modal */}
                {showModal && (
                    <div className="modal-overlay">
                        <div className="modal">
                            <h3>Confirm Payment</h3>
                            <p>Are you sure you want to proceed with the payment for Prescription ID: {selectedPrescriptionId}?</p>
                            <div style={{ marginBottom: '10px' }}>
                                <label>
                                    Card Number:
                                    <input
                                        type="text"
                                        value={cardNumber}
                                        onChange={(e) => setCardNumber(e.target.value)}
                                        placeholder="Card Number"
                                        style={{ marginLeft: '10px', padding: '5px' }}
                                    />
                                    {errors.cardNumber && <p style={{ color: 'red' }}>{errors.cardNumber}</p>}
                                </label>
                            </div>
                            <div style={{ display: 'flex', marginBottom: '10px' }}>
                                <label style={{ marginRight: '10px' }}>
                                    Expiry Date:
                                    <input
                                        type="month"
                                        value={expiryDate}
                                        onChange={(e) => setExpiryDate(e.target.value)}
                                        placeholder="MM/YY"
                                        style={{ marginLeft: '10px', padding: '5px' }}
                                    />
                                    {errors.expiryDate && <p style={{ color: 'red' }}>{errors.expiryDate}</p>}
                                </label>
                                <label>
                                    CVV:
                                    <input
                                        type="text"
                                        value={cvv}
                                        onChange={(e) => setCvv(e.target.value)}
                                        placeholder="CVV"
                                        style={{ marginLeft: '10px', padding: '5px' }}
                                    />
                                    {errors.cvv && <p style={{ color: 'red' }}>{errors.cvv}</p>}
                                </label>
                            </div>
                            <div style={{ marginBottom: '10px' }}>
                                <label>
                                    Amount:
                                    <input
                                        type="number"
                                        value={amount}
                                        onChange={(e) => setAmount(e.target.value)}
                                        placeholder="Amount"
                                        style={{ marginLeft: '10px', padding: '5px' }}
                                    />
                                    {errors.amount && <p style={{ color: 'red' }}>{errors.amount}</p>}
                                </label>
                            </div>
                            <div className='search-box'>
                                <button className="cancel" onClick={handleModalClose}>Cancel</button>
                                <button onClick={handleSubmitPayment}>Submit</button>
                            </div>
                        </div>
                    </div>
                )}
            </div>
        </>
    );
};

export default PrescriptionTable;
