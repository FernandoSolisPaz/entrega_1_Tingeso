import { useState } from "react";
import Box from "@mui/material/Box";
import carService from "../services/car.service.js";
import receiptService from "../services/receipt.service.js";
import repairService from "../services/repair.service.js";
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
    const [realRepairIds, setRealRepairIds] = useState([]);
    const [repairEntityv, setRepairEntity] = useState(null);
    const [carEntity, setCarEntity] = useState(null);
    const [carMotorId, setCarMotorId] = useState(-1);
    const navigate = useNavigate();
    let tittle = "Register a repair for a car";

    const saveReceipt = async (r) =>{
        r.preventDefault();
        setCarEntity(carService.get(carPlate));
        setCarMotorId(carEntity.motor);
        for (let i = 0; i < repairIds.length; i++) {
            const Name = repairIds[i];
            try {
                const repairEntity = await repairService.getByMotorAndName(carMotorId,Name);
                setRepairEntity(repairEntity);
                // eslint-disable-next-line
                setRealRepairIds([...realRepairIds, repairEntityv.repairId])
            } catch (error) {
                console.error('Error trying to bring the Repair:', error);
            }

        }
        const receipt = {carPlate}
        receiptService
            .create(receipt, realRepairIds)
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



    const handleCarPlateChange = (value) => {
        if (value.trim() !== '') {
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
                        onChange={(r) => {
                            setCarPlate(r.target.value);
                            handleCarPlateChange(r.target.value);
                        }}
                        helperText="Ej. CGZA96"
                    />
                </FormControl>
                <FormControl style={{width: '100%'}}>
                    <legend>Options of Repairs:</legend>
                    <FormControlLabel
                        control={<Checkbox onChange={handleCheckboxChange} disabled={!camp2Habilitated}/>}
                        label="Braking System"
                        value="Braking System"
                    />
                    <FormControlLabel
                        control={<Checkbox onChange={handleCheckboxChange} disabled={!camp2Habilitated}/>}
                        label="Refrigeration System"
                        value="Refrigeration System"
                    />
                    <FormControlLabel
                        control={<Checkbox onChange={handleCheckboxChange} disabled={!camp2Habilitated}/>}
                        label="Motor"
                        value="Motor"
                    />
                    <FormControlLabel
                        control={<Checkbox onChange={handleCheckboxChange} disabled={!camp2Habilitated}/>}
                        label="Transmision"
                        value="Transmision"
                    />
                    <FormControlLabel
                        control={<Checkbox onChange={handleCheckboxChange} disabled={!camp2Habilitated}/>}
                        label="Electric System"
                        value="Electric System"
                    />
                    <FormControlLabel
                        control={<Checkbox onChange={handleCheckboxChange} disabled={!camp2Habilitated}/>}
                        label="Escape System"
                        value="Escape System"
                    />
                    <FormControlLabel
                        control={<Checkbox onChange={handleCheckboxChange} disabled={!camp2Habilitated}/>}
                        label="Tyres and Wheels"
                        value="Tyres and Wheels"
                    />
                    <FormControlLabel
                        control={<Checkbox onChange={handleCheckboxChange} disabled={!camp2Habilitated}/>}
                        label="Suspension and steering"
                        value="Suspension and steering"
                    />
                    <FormControlLabel
                        control={<Checkbox onChange={handleCheckboxChange} disabled={!camp2Habilitated}/>}
                        label="AC and Heating System"
                        value="AC and Heating System"
                    />
                    <FormControlLabel
                        control={<Checkbox onChange={handleCheckboxChange} disabled={!camp2Habilitated}/>}
                        label="Fuel System"
                        value="Fuel System"
                    />
                    <FormControlLabel
                        control={<Checkbox onChange={handleCheckboxChange} disabled={!camp2Habilitated}/>}
                        label="Windshield and Glass"
                        value="Windshield and Glass"
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
