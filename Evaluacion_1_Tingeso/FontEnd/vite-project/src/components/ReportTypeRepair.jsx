import {useEffect, useState} from 'react'
import { Link } from 'react-router-dom'
import reportService from '../services/report.service.js'
import Table from "@mui/material/Table"
import TableBody from "@mui/material/TableBody"
import TableContainer from "@mui/material/TableContainer"
import TableHead from "@mui/material/TableHead"
import TableRow from "@mui/material/TableRow"
import TableCell from "@mui/material/TableCell";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import KeyboardReturnIcon from '@mui/icons-material/KeyboardReturn';

const ReportTypeRepair = () => {
    const [reportRow, setReportRow] = useState([]);

    const init = () => {
        reportService
            .getTypeRepairReport()
            .then((response) => {
                console.log("Showing report type repair", response.data);
                setReportRow(response.data);
            })
            .catch((error) => {
                console.log(
                    "An error has occurred trying to display the list of report type repair",
                    error
                );
            });
    };
    useEffect(() => {
        init();
    }, []);
    return (
        <TableContainer component={Paper}>
            <Table sx = {{ minWidth: 650}} size="small" aria-label="a dense table">
                <TableHead>
                    <TableRow>
                        <TableCell align="left" sx={{ fontWeight: "bold" }}>
                            Index
                        </TableCell>
                        <TableCell align="left" sx={{ fontWeight: "bold" }}>
                            Repair Name
                        </TableCell>
                        <TableCell align="left" sx={{ fontWeight: "bold" }}>
                            Type Name
                        </TableCell>
                        <TableCell align="left" sx={{ fontWeight: "bold" }}>
                            Number of Repairs
                        </TableCell>
                        <TableCell align="left" sx={{ fontWeight: "bold" }}>
                            Total Amount
                        </TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {reportRow.map((rep) => (
                        <TableRow
                        key={rep.id}
                        sx={{ "&:last-child td, &:last-child th, &:last-child td}}": {border: 0}} }
                        >
                            <TableCell align="left">{rep.id}</TableCell>
                            <TableCell align="left">{rep.repairName}</TableCell>
                            <TableCell align="left">{rep.typeName}</TableCell>
                            <TableCell align="left">{rep.numberOfRepairs}</TableCell>
                            <TableCell align="left">{rep.totalAmount}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
            <br />
            <Link to="/report/list"
                  style={{ textDecoration: "none", marginLeft: "1rem" }}
            >
                <Button
                    variant="contained"
                    color="primary"
                    startIcon={<KeyboardReturnIcon />}
                >
                    Return to List
                </Button>
            </Link>
        </TableContainer>
    );
};

export default ReportTypeRepair;