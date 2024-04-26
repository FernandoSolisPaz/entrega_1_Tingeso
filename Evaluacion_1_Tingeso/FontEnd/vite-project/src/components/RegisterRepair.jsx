import { useState } from "react";
import Box from "@mui/material/Box";
import receiptService from "../services/receipt.service.js";
import { useNavigate } from "react-router-dom";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import SaveIcon from "@mui/icons-material/Save";
import {FormControlLabel} from "@mui/material";
import Checkbox from '@mui/material/Checkbox';

const RegisterRepair = () => {
    const [carPlate, setCarPlate] = useState("");
    const [camp2Habilitated, setCamp2] = useState(false);
    const [repairIds, setRepairIds] = useState([]);
    const navigate = useNavigate();
    let tittle = "Register a repair for a car";

    const saveReceipt = (r) =>{
        r.preventDefault();

        const receipt = {carPlate}
        receiptService
            .create(receipt)
            .then(response => {
                console.log("Receipt added successfully", response.data);
                navigate("/home");
            })
            .catch((error) => {
                console.log("An error has occurred in the register of the receipt",
                    error
                );
            });

    }

    const handleCarPlateChange = (event) => {
        const valor = event.target.value;
        setCarPlate(valor);

        if (valor.trim() !== '') {
            setCamp2(true);
        } else {
            setCamp2(false);
            setRepairIds([]);
        }
    };

    const handleCheckboxChange = (event) => {
        const value = parseInt(event.target.value, 10);
        const isChecked = event.target.checked;

        if (isChecked) {
            setRepairIds([...repairIds, value]);
        } else {
            setRepairIds(repairIds.filter(item => item !== value));
        }
    };

    return(
        <Box
            display="flex"
            flexDirection="column"
            alignItems="center"
            justifyContent="center"
            component="form"
        >
            <h3>{tittle}</h3>
            <hr />
            <form style={{display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
                <FormControl style={{width: '100%'}}>
                    <TextField
                        id="carPlate"
                        label="Car Plate"
                        value={carPlate}
                        variant="standard"
                        onChange={handleCarPlateChange}
                        helperText="Ej. CGZA96"
                    />
                </FormControl>
                <FormControl style={{width: '100%'}}>
                    <legend>Options of Repairs:</legend>
                    <FormControlLabel
                        control={<Checkbox onChange={handleCheckboxChange} disabled={!camp2Habilitated}/>}
                        label="Opción 1"
                        value={0}
                    />
                    <FormControlLabel
                        control={<Checkbox onChange={handleCheckboxChange} disabled={!camp2Habilitated}/>}
                        label="Opción 2"
                        value={1}
                    />
                    <FormControlLabel
                        control={<Checkbox onChange={handleCheckboxChange} disabled={!camp2Habilitated}/>}
                        label="Opción 3"
                        value={2}
                    />
                </FormControl>
                <div style={{display: 'flex', justifyContent: 'center', width: '100%'}}>
                    <FormControl>
                        <Button
                            variant="contained"
                            color="info"
                            onClick={(r) => saveReceipt(r)}
                            style={{marginTop: '1rem'}}
                            startIcon={<SaveIcon/>}
                        >
                            Save
                        </Button>
                    </FormControl>
                </div>
            </form>
        </Box>
    );
};

export default RegisterRepair;
