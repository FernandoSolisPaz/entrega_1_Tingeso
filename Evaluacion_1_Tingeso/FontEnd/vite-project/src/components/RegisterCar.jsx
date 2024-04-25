import { useState } from "react";
import React from 'react';
import DatePicker from '@mui/x-date-pickers'
import { Link, useNavigate } from "react-router-dom";
import carService from "../services/car.service.js";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import SaveIcon from "@mui/icons-material/Save";

const RegisterCar = () => {
    const [carPlate, setCarPlate] = useState("");
    const [carBrandId, setCarBrandId] = useState("");
    const [model, setModel] = useState("");
    const [type, setType] = useState("")
    const [yearOfFabrication, setYearOfFabrication] = React.useState<{ dateFrom: Date | null, dateTo: Date | null, selected: 'string' | 'number'}>({ dateFrom: new Date(), datoTo: new Date(), selected: -1});
    const [motor, setMotor] = useState("");
    const [numberOfSeats, setNumberOfSeats] = useState("");
    const [kilometers, setKilometers] = useState("");
    const navigate = useNavigate();
    let titleCreateCarForm = 'New Car';
    const saveCar = (c) => {
        c.preventDefault();

        const car = { carPlate, carBrandId, model, type, yearOfFabrication,motor,numberOfSeats, kilometers};

        carService
            .create(car)
            .then((response) =>{
                console.log("Car has been added", response.data);
                navigate("/home");
            })
            .catch((error) => {
                console.log(
                    "An Error has occurred in the creation of the new car",
                    error
                );
            });
    };

    return(
        <Box
            display="flex"
            flexDirection="column"
            alignItems="center"
            justifyContent="center"
            component="form"
        >
            <h3>{titleCreateCarForm}</h3>
            <hr />
            <form style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                <div style={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between', width: '100%', marginBottom: '1rem' }}>
                    <FormControl style={{ width: '49%' }}>
                        <TextField
                            id="carPlate"
                            label="CarPlate"
                            value={carPlate}
                            variant="standard"
                            onChange={(c) => setCarPlate(c.target.value)}
                            helperText="Ej. CGZA96"
                        />
                    </FormControl>

                    <FormControl style={{ width: '49%' }}>
                        <TextField
                            id="carBrandId"
                            label="CarBrandId"
                            value={carBrandId}
                            variant="standard"
                            onChange={(c) => setCarBrandId(c.target.value)}
                        />
                    </FormControl>
                </div>

                <div style={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between', width: '100%', marginBottom: '1rem' }}>
                    <FormControl style={{ width: '49%' }}>
                        <TextField
                            id="model"
                            label="Model"
                            value={model}
                            variant="standard"
                            onChange={(c) => setModel(c.target.value)}
                        />
                    </FormControl>

                    <FormControl style={{ width: '49%' }}>
                        <TextField
                            id="type"
                            label="Type"
                            value={type}
                            select
                            variant="standard"
                            onChange={(c) => setType(c.target.value)}
                        >
                            <MenuItem value={"0"}>Sedan</MenuItem>
                            <MenuItem value={"1"}>Hatchback</MenuItem>
                            <MenuItem value={"2"}>SUV</MenuItem>
                            <MenuItem value={"3"}>Pickup</MenuItem>
                            <MenuItem value={"4"}>Furgoneta</MenuItem>
                        </TextField>
                    </FormControl>
                </div>

                <div style={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between', width: '100%', marginBottom: '1rem' }}>
                    <FormControl style={{ width: '49%' }}>
                        <TextField
                            id="numberOfSeats"
                            label="NumberOfSeats"
                            type="number"
                            value={numberOfSeats}
                            variant="standard"
                            onChange={(c) => setNumberOfSeats(c.target.value)}
                            helperText="Number of seats of your vehicle"
                        />
                    </FormControl>

                    <FormControl style={{ width: '49%' }}>
                        <TextField
                            id="kilometers"
                            label="Kilometers"
                            type="number"
                            value={kilometers}
                            variant="standard"
                            onChange={(c) => setKilometers(c.target.value)}
                            helperText="Kilometers traveled in your vehicle"
                        />
                    </FormControl>
                </div>

                <div style={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between', width: '100%', marginBottom: '1rem' }}>
                    <FormControl style={{ width: '49%' }}>
                        <TextField
                            id="motor"
                            label="Motor"
                            value={motor}
                            select
                            variant="standard"
                            onChange={(c) => setMotor(c.target.value)}
                        >
                            <MenuItem value={"0"}>Gasoline</MenuItem>
                            <MenuItem value={"1"}>Diesel</MenuItem>
                            <MenuItem value={"2"}>Hibrid</MenuItem>
                            <MenuItem value={"3"}>Electric</MenuItem>
                        </TextField>
                    </FormControl>
                    </div>
                    <div style={{ width: '49%' }}>
                        <FormControl style={{ width: '100%', marginBottom: '1rem' }}>
                            <DatePicker
                                disableFuture
                                label='Responsive'
                                openTo='year'
                                views={['year', 'month', 'day']}
                                value={yearOfFabrication.dateFrom}
                                onChange={(newValue) => {
                                setYearOfFabrication({...yearOfFabrication, dateFrom: newValue})
                            }}
                            renderInput={(params) => <TextField {...params}/>}
                            />
                        </FormControl>
                    </div>


                <div style={{ display: 'flex', justifyContent: 'center', width: '100%' }}>
                    <FormControl>
                        <Button
                            variant="contained"
                            color="info"
                            onClick={(c) => saveCar(c)}
                            style={{ marginTop: '1rem' }}
                            startIcon={<SaveIcon/>}
                        >
                            Save
                        </Button>
                    </FormControl>
                </div>
            </form>
            <hr />
            <Link to="/home">Back to Home</Link>
        </Box>




    );
};

export default RegisterCar;
