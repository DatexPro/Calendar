ALTER TABLE note
    ADD COLUMN id_client INT,
    ADD CONSTRAINT fk_id_client FOREIGN KEY (id_client) REFERENCES client (id);