DROP TABLE IF EXISTS maintenance_protocols CASCADE;

CREATE TABLE maintenance_protocols (

   protocol UUID PRIMARY KEY,
   property_id_for_maintenance UUID NOT NULL REFERENCES properties(id),
   requesting_tenant_id UUID NOT NULL REFERENCES users(id),
   opening_date DATE NOT NULL,
   date_of_resolution DATE,
   updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   maintenance_details TEXT NOT NULL,
   maintenance_status VARCHAR(20) NOT NULL CHECK (maintenance_status IN ('OPEN', 'IN_PROGRESS', 'COMPLETED', 'CANCELED')),
   reason_for_cancellation TEXT
);