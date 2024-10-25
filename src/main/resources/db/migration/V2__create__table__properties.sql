DROP TABLE IF EXISTS properties CASCADE;

CREATE TABLE properties (
    id UUID PRIMARY KEY NOT NULL,
    owner_id UUID NOT NULL REFERENCES users(id) ON DELETE SET NULL,
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

 -- Indexes for optimizing address-based searches
CREATE INDEX idx_city ON properties (city);
CREATE INDEX idx_state ON properties (state);
CREATE INDEX idx_postal_code ON properties (postal_code);

