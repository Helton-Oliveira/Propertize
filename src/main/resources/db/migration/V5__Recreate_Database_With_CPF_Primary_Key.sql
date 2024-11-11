-- Drop and recreate all tables
DROP TABLE IF EXISTS maintenance_protocols CASCADE;
DROP TABLE IF EXISTS contracts CASCADE;
DROP TABLE IF EXISTS properties CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- Create the users table with cpf as primary key
CREATE TABLE users (
    cpf VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL,
    role VARCHAR(255) NOT NULL
);

-- Create the properties table with owner_cpf referencing users.cpf
CREATE TABLE properties (
    id UUID PRIMARY KEY NOT NULL,
    owner_cpf VARCHAR(255) NOT NULL REFERENCES users(cpf) ON DELETE SET NULL,
    property_type VARCHAR(50) NOT NULL,
    size DECIMAL(10, 2) NOT NULL,
    bedroom_count INTEGER NOT NULL,
    bathroom_count INTEGER NOT NULL,
    has_garage BOOLEAN NOT NULL,
    rent_value NUMERIC(15, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    construction_date DATE,
    registration_date DATE DEFAULT CURRENT_DATE,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description TEXT,

    -- Address Fields
    street VARCHAR(255) NOT NULL,
    number VARCHAR(20),
    complement VARCHAR(255),
    neighborhood VARCHAR(100),
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    postal_code VARCHAR(20) NOT NULL,

    -- Other Fields
    maintenance_pending BOOLEAN DEFAULT FALSE,

    -- Constraints and Indexes
    CONSTRAINT status_check CHECK (status IN ('AVAILABLE', 'RENTED', 'UNDER_MAINTENANCE', 'INACTIVE')),
    CONSTRAINT type_check CHECK (property_type IN ('RESIDENTIAL', 'COMMERCIAL', 'INDUSTRIAL'))
);

-- Create indexes for address-based searches
CREATE INDEX idx_city ON properties (city);
CREATE INDEX idx_state ON properties (state);
CREATE INDEX idx_postal_code ON properties (postal_code);

-- Create the contracts table with tenant_cpf referencing users.cpf
CREATE TABLE contracts (
    id UUID PRIMARY KEY NOT NULL,
    property_id UUID NOT NULL REFERENCES properties(id) ON DELETE CASCADE,
    tenant_cpf VARCHAR(255) NOT NULL REFERENCES users(cpf) ON DELETE SET NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    monthly_rent NUMERIC(15, 2) NOT NULL,
    payment_due_day INTEGER NOT NULL CHECK (payment_due_day >= 1 AND payment_due_day <= 31),
    security_deposit NUMERIC(15, 2),
    contract_status VARCHAR(20) NOT NULL CHECK (contract_status IN ('ACTIVE', 'TERMINATED', 'EXPIRED', 'RENEWED')),
    termination_fee NUMERIC(15, 2),
    is_renewable BOOLEAN DEFAULT FALSE,
    auto_renewal BOOLEAN DEFAULT FALSE,
    maintenance_clause TEXT,
    subleasing_allowed BOOLEAN DEFAULT FALSE,
    contract_terms TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    termination_date DATE,
    termination_reason TEXT
);

-- Create the maintenance_protocols table with requesting_tenant_cpf referencing users.cpf
CREATE TABLE maintenance_protocols (
    protocol UUID PRIMARY KEY,
    property_id_for_maintenance UUID NOT NULL REFERENCES properties(id),
    requesting_tenant_cpf VARCHAR(255) NOT NULL REFERENCES users(cpf),
    opening_date DATE NOT NULL,
    date_of_resolution DATE,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    maintenance_details TEXT NOT NULL,
    maintenance_status VARCHAR(20) NOT NULL CHECK (maintenance_status IN ('OPEN', 'IN_PROGRESS', 'COMPLETED', 'CANCELED')),
    reason_for_cancellation TEXT
);

