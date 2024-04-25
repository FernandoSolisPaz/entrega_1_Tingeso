import React, { useState } from "react";
import Box from "@mui/material/Box";
import brandService from "../services/brand.service.js";
import { Link, useNavigate } from "react-router-dom";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import SaveIcon from "@mui/icons-material/Save";



const RegisterBrandBond = () => {
    const [brandName, setBrandName] = useState("");
    const [bondAvailable, setBondAvailable] = useState("");
    const [amount, setAmount] = useState("");
    const navigate = useNavigate();
    let titleRegisterNewBrand = "New Brand Bond";

    const saveBrandBond = (b) => {
        b.preventDefault();

        const brand = { brandName, bondAvailable, amount};
        brandService
            .create(brand)
            .then((response) =>{
                console.log("Brand has been registed", response.data);
                navigate("/home");
            })
            .catch((error) => {
                console.log(
                    "An error has occurred in the register of the brand",
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
            <h3>{titleRegisterNewBrand}</h3>
            <hr />
            <form style={{display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
                <div style={{
                    display: 'flex',
                    flexDirection: 'row',
                    justifyContent: 'space-between',
                    width: '100%',
                    marginBottom: '1rem'
                }}>
                    <FormControl style={{width: '49%'}}>
                        <TextField
                            id="brandName"
                            label="Brand Name"
                            value={brandName}
                            variant="standard"
                            onChange={(b) => setBrandName(b.target.value)}
                            helperText="Ej. Toyota"
                        />
                    </FormControl>

                    <FormControl style={{width: '49%'}}>
                        <TextField
                            id="bondAvailable"
                            label="Bond Available"
                            type="number"
                            value={bondAvailable}
                            variant="standard"
                            onChange={(b) => setBondAvailable(b.target.value)}
                            helperText="Number of benefits to give"
                        />
                    </FormControl>
                </div>
                <div style={{
                    display: 'flex',
                    flexDirection: 'row',
                    justifyContent: 'space-between',
                    width: '100%',
                    marginBottom: '1rem'
                }}>
                    <FormControl style={{width: '49%'}}>
                        <TextField
                            id="amount"
                            label="Amount to give"
                            type="number"
                            value={amount}
                            variant="standard"
                            onChange={(b) => setAmount(b.target.value)}
                            helperText="Bond value to give"
                        />
                    </FormControl>
                </div>
                <div style={{display: 'flex', justifyContent: 'center', width: '100%'}}>
                    <FormControl>
                        <Button
                            variant="contained"
                            color="info"
                            onClick={(b) => saveBrandBond(b)}
                            style={{marginTop: '1rem'}}
                            startIcon={<SaveIcon/>}
                        >
                            Save
                        </Button>
                    </FormControl>
                </div>
            </form>
            <hr/>
            <Link to="/home">Back to Home</Link>
        </Box>
    );
};

export default RegisterBrandBond;