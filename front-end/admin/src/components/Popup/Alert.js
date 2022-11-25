import React, { useEffect, useState } from 'react';
import { Alert } from 'react-bootstrap'
export default function AlertPopup({ variant, children, isShow=false }) {
    const [alert, setAlert] = useState(isShow);
    
    useEffect(() => {
        const timer = setTimeout(() => {
            setAlert(false);
        }, 1000);
        
        // To clear or cancel a timer, you call the clearTimeout(); method, 
        // passing in the timer object that you created into clearTimeout().
        
        return () => clearTimeout(timer);
    }, []);   

    return alert && <Alert className='pop-up variant'>{children}</Alert>
}

