DROP TABLE IF EXISTS contracts CASCADE;

CREATE TABLE contracts (
    id UUID PRIMARY KEY NOT NULL,
    property_id UUID NOT NULL REFERENCES properties(id) ON DELETE CASCADE,
    tenant_id UUID NOT NULL REFERENCES users(id) ON DELETE SET NULL,
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
