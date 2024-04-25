import * as React from "react";
import Box from "@mui/material/Box";
import Drawer from "@mui/material/Drawer";
import List from "@mui/material/List";
import Divider from "@mui/material/Divider";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import PeopleAltIcon from "@mui/icons-material/PeopleAlt";
import HomeIcon from "@mui/icons-material/Home";
import { useNavigate } from "react-router-dom";

export default function Sidemenu({ open, toggleDrawer}){
    const navigate = useNavigate();

    const listOptions = () => (
        <Box
            role="presentation"
            onClick={toggleDrawer(false)}
        >
            <List>
                <ListItemButton onClick={() => navigate("/home")}>
                    <ListItemIcon>
                        <HomeIcon />
                    </ListItemIcon>
                    <ListItemText primary="Home" />
                </ListItemButton>

                <Divider />

                <ListItemButton onClick={() => navigate("/cars/add")}>
                    <ListItemIcon>
                        <PeopleAltIcon />
                    </ListItemIcon>
                    <ListItemText primary="RegisterCar" />
                </ListItemButton>
            </List>
        </Box>
    );
   return (
       <div>
           <Drawer anchor={"left"} open={open} onClose={toggleDrawer(false)}>
               {listOptions()}
           </Drawer>
       </div>
   );
}