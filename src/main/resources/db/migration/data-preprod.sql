INSERT INTO donor (email, full_name) VALUES ('mihago@rasoa.mg', 'Mihago Rasoa');

INSERT INTO payment (id, method, status, external_id) VALUES
    ('22222222-2222-2222-2222-222222222222', 'Orange Money', 'VERIFYING', 'ref-002');

INSERT INTO donation (donor_id, date, amount, payment_id) VALUES
    (1, '2027-07-29T00:00:00Z', 1000, 'MP250804.0904.A01637');
