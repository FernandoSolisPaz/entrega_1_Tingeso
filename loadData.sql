-- Población de datos
INSERT INTO Car_brand (brand_name, bond_available, amount)
VALUES
('Toyota', 4, 20000),
('Jeep', 3, 15000),
('Nissan', 5, 12500);


INSERT INTO Car (plate, car_brand_id, model, type, year_of_fabrication, motor, number_of_seats, kilometers)
VALUES 
('CGZA96', 1, '4x4', 0, 2004, 0, 4, 40000),
('PGKL64', 1, 'Corolla', 1, 2008, 1, 4, 30000),
('HOLA11', 2, 'Wrangler', 2, 2006, 2, 2, 32000),
('BYES43', 3, 'Frontier', 3, 2010, 3, 4, 17500);

INSERT INTO Repairs (repair_name, type_of_motor, cost_of_repair)
VALUES
('Braking System', 0, 120000),
('Refrigeration System', 0, 130000),
('Motor', 0, 350000),
('Transmision', 0, 210000),
('Electric System', 0, 150000),
('Escape System', 0, 100000),
('Tyres and Wheels', 0, 100000),
('Suspension and steering', 0, 180000),
('AC and Heating System', 0, 150000),
('Fuel System', 0, 130000),
('Windshield and Glass', 0, 80000),

('Braking System', 1, 120000),
('Refrigeration System', 1, 130000),
('Motor', 1, 450000),
('Transmision', 1, 210000),
('Electric System', 1, 150000),
('Escape System', 1, 120000),
('Tyres and Wheels', 1, 100000),
('Suspension and steering', 1, 180000),
('AC and Heating System', 1, 150000),
('Fuel System', 1, 140000),
('Windshield and Glass', 1, 80000),

('Braking System', 2, 180000),
('Refrigeration System', 2, 190000),
('Motor', 2, 700000),
('Transmision', 2, 300000),
('Electric System', 2, 200000),
('Escape System', 2, 450000),
('Tyres and Wheels', 2, 100000),
('Suspension and steering', 2, 210000),
('AC and Heating System', 2, 180000),
('Fuel System', 2, 220000),
('Windshield and Glass', 2, 80000),

('Braking System', 3, 220000),
('Refrigeration System', 3, 230000),
('Motor', 3, 800000),
('Transmision', 3, 300000),
('Electric System', 3, 250000),
('Escape System', 3, 0),
('Tyres and Wheels', 3, 100000),
('Suspension and steering', 3, 250000),
('AC and Heating System', 3, 180000),
('Fuel System', 3, 0),
('Windshield and Glass', 3, 80000);





