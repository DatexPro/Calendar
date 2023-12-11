import React, { useState } from 'react';

function CalculationForm() {
    const [income, setIncome] = useState(0);
    const [expenses, setExpenses] = useState(0);
    const [result, setResult] = useState(0);

    const handleCalculate = (e) => {
        e.preventDefault(); // Предотвращаем отправку формы

        const calculationResult = income - expenses;
        setResult(calculationResult);
    };

    return (
        <div className="form-group">
            <div className="row">
                <div className="col-md-4">
                    <input type="number" value={income} onChange={(e) => setIncome(e.target.value)} className="form-control" placeholder="Income" />
                </div>
                <div className="col-md-4">
                    <input type="number" value={expenses} onChange={(e) => setExpenses(e.target.value)} className="form-control" placeholder="Expenses" />
                </div>
                <div className="col-md-4">
                    <button className="btn btn-primary btn-block" type="submit" onClick={handleCalculate}>Calculate</button>
                </div>
            </div>
            <div className="row">
                <div className="col-md-12">
                    <h4 className="text-center">Result: <span id="result">{result}</span></h4>
                </div>
            </div>
        </div>
    );
}

export default CalculationForm;