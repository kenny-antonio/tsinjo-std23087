INSERT INTO donor (email, full_name) VALUES ('lou@hei.school', 'Lou Andria');

INSERT INTO payment (id, method, status, external_id) VALUES
    ('11111111-1111-1111-1111-111111111111', 'Orange Money', 'SUCCEEDED', 'ref-001');

INSERT INTO donation (donor_id, date, amount, payment_id) VALUES
    (1, '2025-08-11T00:00:00Z', 50000, 'MP250804.0910.A02057');
