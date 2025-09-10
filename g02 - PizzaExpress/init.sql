-- Crea usuarios, bases y privilegios por microservicio.
-- Se ejecuta una sola vez cuando el volumen de Postgres está vacío.

-- Orders
CREATE USER "order" WITH PASSWORD 'order';
CREATE DATABASE ordersdb OWNER "order";
GRANT ALL PRIVILEGES ON DATABASE ordersdb TO "order";

-- Kitchen
CREATE USER kitchen WITH PASSWORD 'kitchen';
CREATE DATABASE kitchendb OWNER kitchen;
GRANT ALL PRIVILEGES ON DATABASE kitchendb TO kitchen;

-- Inventory
CREATE USER inventory WITH PASSWORD 'inventory';
CREATE DATABASE inventorydb OWNER inventory;
GRANT ALL PRIVILEGES ON DATABASE inventorydb TO inventory;

-- Supplier
CREATE USER supplier WITH PASSWORD 'supplier';
CREATE DATABASE supplierdb OWNER supplier;
GRANT ALL PRIVILEGES ON DATABASE supplierdb TO supplier;

-- Billing & Payments (comparten DB/credenciales)
CREATE USER billingpayments WITH PASSWORD 'billingpayments';
CREATE DATABASE billingpaymentsdb OWNER billingpayments;
GRANT ALL PRIVILEGES ON DATABASE billingpaymentsdb TO billingpayments;

-- Delivery
CREATE USER delivery WITH PASSWORD 'delivery';
CREATE DATABASE deliverydb OWNER delivery;
GRANT ALL PRIVILEGES ON DATABASE deliverydb TO delivery;

-- Customer
CREATE USER customer WITH PASSWORD 'customer';
CREATE DATABASE customerdb OWNER customer;
GRANT ALL PRIVILEGES ON DATABASE customerdb TO customer;

-- Report
CREATE USER report WITH PASSWORD 'report';
CREATE DATABASE reportdb OWNER report;
GRANT ALL PRIVILEGES ON DATABASE reportdb TO report;
